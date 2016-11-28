package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

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
}
