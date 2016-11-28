package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for the interaction between the different Coordinate implementation classes.
 */
public class DifferentCoordinatesTest {

    /**
     * Test the distance between a Spherical- and CartesianCoordinate.
     */
    @Test
    public void testDistanceWithCartesianAndSpherical() {
        Coordinate cartesian1 = new CartesianCoordinate(120, -20.121, 1293);
        Coordinate spheric1 = new SphericCoordinate(12.4822, -0.233);

        double expected = 6370603.413286;
        double result = cartesian1.getDistance(spheric1);

        assertEquals(expected, result, 0.01);
    }

    /**
     * Test the equals method for two different Coordinates.
     */
    @Test
    public void testEqualsWithCartesianAndSpherical() {
        Coordinate cartesian = new CartesianCoordinate(1725.3483606172506, 956.3762131993382, 435.1667104522825);
        Coordinate spherical = new SphericCoordinate(12.44, 29, 2020.112);

        assertTrue(cartesian.equals(spherical));
    }

    /**
     * Test the hashCode method for two different Coordinates.
     */
    @Test
    public void testHashCodeWithCartesianAndSpherical() {
        Coordinate cartesian = new CartesianCoordinate(1725.3483606172506, 956.3762131993382, 435.1667104522825);
        Coordinate spherical = new SphericCoordinate(12.44, 29, 2020.112);

        assertTrue(cartesian.hashCode() == spherical.hashCode());
    }
}
