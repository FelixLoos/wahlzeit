package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Test cases for SphericCoordinate objects.
 */
public class SphericCoordinateTest {

    private static final double MIN_LATITUDE = -90.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LONGITUDE = 180.0;

    private static final double BOUNDARY_OFFSET = 0.000001;

    /**
     * Tests the getters for the SphericCoordinate.
     */
    @Test
    public void testGetters() {
        final double latitude = -30;
        final double longitude = 60.523232;
        final double radius = 8002;
        SphericCoordinate sphericCoordinate = SphericCoordinate.getInstance(latitude, longitude, radius);

        assertEquals(latitude, sphericCoordinate.getLatitude(), 0);
        assertEquals(longitude, sphericCoordinate.getLongitude(), 0);
        assertEquals(radius, sphericCoordinate.getRadius(), 0);
    }

    /**
     * Tests the valid maximum and minimum values for latitude and longitude.
     */
    @Test
    public void testValidCoordinateBoundaries() {
        SphericCoordinate latitudeMin = SphericCoordinate.getInstance(MIN_LATITUDE, 0);
        SphericCoordinate latitudeMax = SphericCoordinate.getInstance(MAX_LATITUDE, 0);
        SphericCoordinate longitudeMin = SphericCoordinate.getInstance(0, MIN_LONGITUDE);
        SphericCoordinate longitudeMax = SphericCoordinate.getInstance(0, MAX_LONGITUDE);

        assertEquals(MIN_LATITUDE, latitudeMin.getLatitude(), 0);
        assertEquals(MAX_LATITUDE, latitudeMax.getLatitude(), 0);
        assertEquals(MIN_LONGITUDE, longitudeMin.getLongitude(), 0);
        assertEquals(MAX_LONGITUDE, longitudeMax.getLongitude(), 0);
    }

    /**
     * Tests an invalid latitude (close to the minimum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLatitude() {
        SphericCoordinate.getInstance(MIN_LATITUDE - BOUNDARY_OFFSET, 0);
    }

    /**
     * Tests an invalid latitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLatitude() {
        SphericCoordinate.getInstance(MAX_LATITUDE + BOUNDARY_OFFSET, 0);
    }

    /**
     * Tests an invalid longitude (close to the minimum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLongitude() {
        SphericCoordinate.getInstance(0, MIN_LONGITUDE - BOUNDARY_OFFSET);
    }

    /**
     * Tests an invalid longitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLongitude() {
        SphericCoordinate.getInstance(0, MAX_LONGITUDE + BOUNDARY_OFFSET);
    }

    /**
     * Tests an invalid longitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testZeroRadius() {
        SphericCoordinate.getInstance(0, 0, 0);
    }

    /**
     * Tests an invalid longitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNegativeRaidus() {
        SphericCoordinate.getInstance(0, 0, -1);
    }

    /**
     * Test for the getDistance method. The test is passed if the result matches
     * the expected result in meters. No resolution for cm or mm required in the
     * testing method.
     */
    @Test
    public void testDistance() {
        Coordinate c1 = SphericCoordinate.getInstance(49.453941, 11.077279);
        Coordinate c2 = SphericCoordinate.getInstance(49.573845, 11.027041);

        double expectedResult = 13817.0;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.5);
    }

    /**
     * Tests the getDistance method
     */
    @Test
    public void testDistanceWithNegativeCoordinates() {
        Coordinate c1 = SphericCoordinate.getInstance(-30.2, -60.23123);
        Coordinate c2 = SphericCoordinate.getInstance(-8.131, -5.2342);

        double expectedResult = 5963432.0;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.5);
    }

    /**
     * Test the getDistance() for one coordinate to the same coordinate.
     */
    @Test
    public void testDistanceWithItself() {
        Coordinate c1 = SphericCoordinate.getInstance(20, -10, 0.12121);

        assertEquals(0, c1.getDistance(c1), 0);
    }

    /**
     * Tests the conversion method asCartesian().
     */
    @Test
    public void testAsCartesian() {
        final double validDelta = 0.00001;

        Coordinate spheric1 = SphericCoordinate.getInstance(-30.2, -60.23123);
        Coordinate spheric2 = SphericCoordinate.getInstance(20.16546, 121);

        CartesianCoordinate cartesian1 = spheric1.asCartesianCoordinate();
        CartesianCoordinate cartesian2 = spheric2.asCartesianCoordinate();

        assertEquals(2733880.2918472, cartesian1.getX(), validDelta);
        assertEquals(-4779663.2276355, cartesian1.getY(), validDelta);
        assertEquals(-3204740.0799812, cartesian1.getZ(), validDelta);

        assertEquals(-3080166.7371492, cartesian2.getX(), validDelta);
        assertEquals(5126258.3028560, cartesian2.getY(), validDelta);
        assertEquals(2196289.9817098, cartesian2.getZ(), validDelta);
    }

    /**
     * Tests for the equals method.
     */
    @Test
    public void testEquals() {
        Coordinate coordinate1a = SphericCoordinate.getInstance(49.453941, 11.077279);
        Coordinate coordinate1b = SphericCoordinate.getInstance(49.453941, 11.077279);
        Coordinate coordinate2 = SphericCoordinate.getInstance(35.129421, 80.841915);

        assertTrue(coordinate1a.equals(coordinate1a));
        assertTrue(coordinate1a.equals(coordinate1b));

        assertFalse(coordinate1a.equals(coordinate2));
        assertFalse(coordinate1a.equals(49.453941));
        assertFalse(coordinate1a.equals(7));
        assertFalse(coordinate1a.equals("(49.453941, 11.077279)"));
    }

    /**
     * Tests for the hashCode method.
     */
    @Test
    public void testHashCode() {
        Coordinate coordinate1a = SphericCoordinate.getInstance(49.453941, 11.077279);
        Coordinate coordinate1b = SphericCoordinate.getInstance(49.453941, 11.077279);
        Coordinate coordinate2 = SphericCoordinate.getInstance(35.129421, 80.841915);
        Coordinate coordinate3 = SphericCoordinate.getInstance(49.453941, 11.077278);

        assertTrue(coordinate1a.hashCode() == coordinate1b.hashCode());

        assertFalse(coordinate1a.hashCode() == coordinate2.hashCode());
        assertFalse(coordinate1a.hashCode() == coordinate3.hashCode());
    }
}
