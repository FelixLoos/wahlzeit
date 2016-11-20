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
     * Tests the valid maximum and minimum values for latitude and longitude.
     */
    @Test()
    public void testValidCoordinateBoundaries() {
        SphericCoordinate latitudeMin = new SphericCoordinate(MIN_LATITUDE, 0);
        SphericCoordinate latitudeMax = new SphericCoordinate(MAX_LATITUDE, 0);
        SphericCoordinate longitudeMin = new SphericCoordinate(0, MIN_LONGITUDE);
        SphericCoordinate longitudeMax = new SphericCoordinate(0, MAX_LONGITUDE);

        assertEquals(latitudeMin.getLatitude(), MIN_LATITUDE);
        assertEquals(latitudeMax.getLatitude(), MAX_LATITUDE);
        assertEquals(longitudeMin.getLongitude(), MIN_LONGITUDE);
        assertEquals(longitudeMax.getLongitude(), MAX_LONGITUDE);
    }

    /**
     * Tests an invalid latitude (close to the minimum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLatitude() {
        new SphericCoordinate(MIN_LATITUDE - BOUNDARY_OFFSET, 0);
    }

    /**
     * Tests an invalid latitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLatitude() {
        new SphericCoordinate(MAX_LATITUDE + BOUNDARY_OFFSET, 0);
    }

    /**
     * Tests an invalid longitude (close to the minimum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLongitude() {
        new SphericCoordinate(0, MIN_LONGITUDE - BOUNDARY_OFFSET);
    }

    /**
     * Tests an invalid longitude (close to the maximum value).
     */
    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLongitude() {
        new SphericCoordinate(0, MAX_LONGITUDE + BOUNDARY_OFFSET);
    }

    /**
     * Test for the getDistance method. The test is passed if the result matches
     * the expected result in meters. No resolution for cm or mm required in the
     * testing method.
     */
    @Test
    public void testDistance() {
        Coordinate c1 = new SphericCoordinate(49.453941, 11.077279);
        Coordinate c2 = new SphericCoordinate(49.573845, 11.027041);

        // Distance accuracy in meters is sufficient
        double expectedResult = 13817.0;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.5);
    }

    /**
     * Tests for the equals method.
     */
    @Test
    public void testEquals() {
        Coordinate coordinate1a = new SphericCoordinate(49.453941, 11.077279);
        Coordinate coordinate1b = new SphericCoordinate(49.453941, 11.077279);
        Coordinate coordinate2 = new SphericCoordinate(35.129421, 80.841915);

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
        Coordinate coordinate1a = new SphericCoordinate(49.453941, 11.077279);
        Coordinate coordinate1b = new SphericCoordinate(49.453941, 11.077279);
        Coordinate coordinate2 = new SphericCoordinate(35.129421, 80.841915);
        Coordinate coordinate3 = new SphericCoordinate(49.453941, 11.077278);

        assertTrue(coordinate1a.hashCode() == coordinate1b.hashCode());

        assertFalse(coordinate1a.hashCode() == coordinate2.hashCode());
        assertFalse(coordinate1a.hashCode() == coordinate3.hashCode());
    }
}
