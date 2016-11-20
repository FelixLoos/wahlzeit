package org.wahlzeit.model;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Test cases for location objects.
 */
public class LocationTest {

    /**
     * Tests the valid maximum and minimum values for latitude and longitude.
     */
    @Test()
    public void testSimpleLocation() {
        Coordinate coordinate = new SphericCoordinate(49.573845, 11.027041);
        Location location = new Location(coordinate);

        assertEquals(location.getCoordinate(), coordinate);
    }
}
