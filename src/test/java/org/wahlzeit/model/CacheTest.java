package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.cache.Cache;
import org.wahlzeit.model.cache.ObjectCache;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CacheTest {

    private static final int INVALID_MAX_SIZE = 0;
    private static final int VALID_MAX_SIZE = 1000;

    private Cache<Integer, String> cache;

    @Before
    public void setup() {
        cache = new ObjectCache<>();
    }

    @Test
    public void testConstructor() {
        cache = new ObjectCache<>();
    }

    @Test
    public void testContains() {
        assertFalse(cache.contains(1));

        cache.insert(1, "1");
        assertTrue(cache.contains(1));

        assertFalse(cache.contains(2));

        cache.insert(2, "2");
        assertTrue(cache.contains(2));
    }

    @Test
    public void testGet() {
        cache.insert(1, "1");

        assertEquals("1", cache.get(1));
    }

    @Test(expected = NullPointerException.class)
    public void testInvalidGet() {
        cache.get(5);
    }

    @Test
    public void testInsert() {
        assertFalse(cache.contains(1));

        cache.insert(1, "1");

        assertTrue(cache.contains(1));
        assertEquals("1", cache.get(1));
    }

    @Test(expected = IllegalStateException.class)
    public void testInsertTwice() {
        assertFalse(cache.contains(1));

        cache.insert(1, "1");
        cache.insert(1, "1");
    }

    @Test(expected = IllegalStateException.class)
    public void testInvalidInsert() {
        cache.insert(1, "1");
        cache.insert(1, "2");
    }
}
