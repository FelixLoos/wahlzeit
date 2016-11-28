package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Test cases for CartesianCoordinate objects.
 */
public class CartesianCoordinateTest {

    /**
     * Tests the getters for the CartesianCoordinate.
     */
    @Test
    public void testGetters() {
        final double x = -3063;
        final double y = 12.121941;
        final double z = 0;
        CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(x, y, z);

        assertEquals(x, cartesianCoordinate.getX(), 0);
        assertEquals(y, cartesianCoordinate.getY(), 0);
        assertEquals(z, cartesianCoordinate.getZ(), 0);
    }

    /**
     * Test the getDistance method. The test is passed if the result matches
     * the expected result in meters. A resolution for cm or mm is not required.
     */
    @Test
    public void testDistance() {
        Coordinate c1 = new CartesianCoordinate(300, 70.5, 800);
        Coordinate c2 = new CartesianCoordinate(100, 30.22176, 70);

        double expectedResult = 757.972517;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.01);
    }

    /**
     * Test the getDistance method with negative coordinates.
     */
    @Test
    public void testDistanceWithNegativeCoordinates() {
        Coordinate c1 = new CartesianCoordinate(-60.1221, -70, -30);
        Coordinate c2 = new CartesianCoordinate(-10, -10, -5);

        double expectedResult = 82.080600;
        double result = c1.getDistance(c2);

        assertEquals(expectedResult, result, 0.01);
    }

    /**
     * Test the getDistance() for one coordinate to the same coordinate.
     */
    @Test
    public void testDistanceWithItself() {
        Coordinate c1 = new CartesianCoordinate(-1290, 10.1211, 140);

        assertEquals(0, c1.getDistance(c1), 0);
    }

    /**
     * Tests the conversion method asCartesian().
     */
    @Test
    public void testAsCartesian() {
        CartesianCoordinate cartesianOld1 = new CartesianCoordinate(-60, -70.112, -30);
        CartesianCoordinate cartesianOld2 = new CartesianCoordinate(20, 90, 1.0294032);

        CartesianCoordinate cartesianConverted1 = cartesianOld1.asCartesianCoordinate();
        CartesianCoordinate cartesianConverted2 = cartesianOld2.asCartesianCoordinate();

        assertEquals(cartesianOld1.getX(), cartesianConverted1.getX(), 0);
        assertEquals(cartesianOld1.getY(), cartesianConverted1.getY(), 0);
        assertEquals(cartesianOld1.getZ(), cartesianConverted1.getZ(), 0);

        assertEquals(cartesianOld2.getX(), cartesianConverted2.getX(), 0);
        assertEquals(cartesianOld2.getY(), cartesianConverted2.getY(), 0);
        assertEquals(cartesianOld2.getZ(), cartesianConverted2.getZ(), 0);
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
