package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CoordinateTest {

    private static final double MIN_LATITUDE = -90.0;
    private static final double MAX_LATITUDE = 90.0;
    private static final double MIN_LONGITUDE = -180.0;
    private static final double MAX_LONGITUDE = 180.0;

    private static final double BOUNDARY_OFFSET = 0.000001;


    @Test()
    public void testValidCoordinateBoundaries() {
        Coordinate latitudeMin = new Coordinate(MIN_LATITUDE, 0);
        Coordinate latitudeMax = new Coordinate(MAX_LATITUDE, 0);
        Coordinate longitudeMin = new Coordinate(0, MIN_LONGITUDE);
        Coordinate longitudeMax = new Coordinate(0, MAX_LONGITUDE);

        assertEquals(latitudeMin.getLatitude(), MIN_LATITUDE);
        assertEquals(latitudeMax.getLatitude(), MAX_LATITUDE);
        assertEquals(longitudeMin.getLongitude(), MIN_LONGITUDE);
        assertEquals(longitudeMax.getLongitude(), MAX_LONGITUDE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLatitude() {
        new Coordinate(MIN_LATITUDE - BOUNDARY_OFFSET, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLatitude() {
        new Coordinate(MAX_LATITUDE + BOUNDARY_OFFSET, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooSmallLongitude() {
        new Coordinate(0, MIN_LONGITUDE - BOUNDARY_OFFSET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooBigLongitude() {
        new Coordinate(0, MAX_LONGITUDE + BOUNDARY_OFFSET);
    }

    @Test
    public void testDistance() {
        Coordinate c1 = new Coordinate(49.453941, 11.077279);
        Coordinate c2 = new Coordinate(49.573845, 11.027041);

        // Distance accuracy in meters is sufficient
        double expectedResult = 13817.0;
        double result = Math.round(c1.getDistance(c2));

        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        Coordinate coordinate1a = new Coordinate(49.453941, 11.077279);
        Coordinate coordinate1b = new Coordinate(49.453941, 11.077279);
        Coordinate coordinate2 = new Coordinate(35.129421, 80.841915);

        assertTrue(coordinate1a.equals(coordinate1a));
        assertTrue(coordinate1a.equals(coordinate1b));

        assertFalse(coordinate1a.equals(coordinate2));
        assertFalse(coordinate1a.equals(49.453941));
        assertFalse(coordinate1a.equals(7));
        assertFalse(coordinate1a.equals("(49.453941, 11.077279)"));
    }
}
