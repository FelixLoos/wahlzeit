package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test cases for CartesianCoordinate objects.
 */
public class CartesianCoordinateTest {

    /**
     * Test the getDistance method. The test is passed if the result matches
     * the expected result in meters. A resolution for cm or mm is not required.
     */
    @Test
    public void testDistance() {
        Coordinate c1 = new CartesianCoordinate(300, 70.5, 800);
        Coordinate c2 = new CartesianCoordinate(100, 30, 70);

        double expectedResult = 757.984334;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.01);
    }

    /**
     * Test the getDistance method with negative coordinates.
     */
    @Test
    public void testDistanceWithNegativeCoordinates() {
        Coordinate c1 = new CartesianCoordinate(-60, -70, -30);
        Coordinate c2 = new CartesianCoordinate(-10, -10, -5);

        double expectedResult = 82.006097;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.01);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testInvalidGetDistance() {
        Coordinate c1 = new CartesianCoordinate(-10, -10, -5);
        Coordinate c2 = new SphericCoordinate(20, 30);

        c1.getDistance(c2);
    }

    /**
     * Tests for the equals method.
     */
    @Test
    public void testEquals() {
        Coordinate coordinate1a = new CartesianCoordinate(1, 2, 3);
        Coordinate coordinate1b = new CartesianCoordinate(1, 2, 3);
        Coordinate coordinate2 = new CartesianCoordinate(1, 2, 3.0000001);

        assertTrue(coordinate1a.equals(coordinate1a));
        assertTrue(coordinate1a.equals(coordinate1b));

        assertFalse(coordinate1a.equals(coordinate2));
        assertFalse(coordinate1a.equals(1));
        assertFalse(coordinate1a.equals("(1, 2, 3)"));
    }

    /**
     * Tests for the hashCode method.
     */
    @Test
    public void testHashCode() {
        Coordinate coordinate1a = new CartesianCoordinate(50, 60, 70);
        Coordinate coordinate1b = new CartesianCoordinate(50, 60, 70);
        Coordinate coordinate2 = new CartesianCoordinate(100, 100, 100);
        Coordinate coordinate3 = new CartesianCoordinate(50, 60, 70.0000001);

        assertTrue(coordinate1a.hashCode() == coordinate1b.hashCode());

        assertFalse(coordinate1a.hashCode() == coordinate2.hashCode());
        assertFalse(coordinate1a.hashCode() == coordinate3.hashCode());
    }
}
