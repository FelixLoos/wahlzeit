package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CoordinateTest {

    @Test
    public void testDistance() {
        Coordinate c1 = new Coordinate(49.453941, 11.077279);
        Coordinate c2 = new Coordinate(49.573845, 11.027041);

        // Distance accuracy in meters is sufficient
        double expectedResult = 13817.0;
        double result = Math.round(c1.getDistance(c2));

        assertEquals(expectedResult, result);
    }
}
