package org.wahlzeit.model;

import org.wahlzeit.utils.AssertUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Cache<Key, Value> {

    public static final int DEFAULT_MAX_SIZE = 50;

    private final Map<Key, Value> values;
    private final int maxSize;

    /**
     * Creates a new cache with the default maxSize.
     */
    public Cache() {
        this(DEFAULT_MAX_SIZE);
    }

    /**
     * Creates a new cache with the desired maxSize. Once the cache is full, the element that was inserted
     * last will be removed.
     *
     * @param maxSize
     */
    public Cache(final int maxSize) {
        this.maxSize = maxSize;

        assertMaxSizeIsValid(maxSize);

        values = new LinkedHashMap<Key, Value>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
                return size() > maxSize;
            }
        };
    }

    /**
     * Returns the maximum size of the cache.
     *
     * @return maximum size
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Checks whether the specified key exists.
     *
     * @param key specified key
     * @return    true or false
     */
    public boolean contains(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");
        return values.containsKey(key);
    }

    /**
     * Returns the value for a specified key
     *
     * @param key key
     * @return    value
     */
    public Value get(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (!values.containsKey(key)) {
            throw new NullPointerException("Key not found");
        }

        return values.get(key);
    }

    /**
     * Inserts a new value with a specified key. If the key exists already and the values are equal,
     * the value entry will be refreshed. That means the cache refreshes the timestamp of the value,
     * marking it as a new element.
     *
     * @param key   key
     * @param value value
     * @return      value
     */
    public Value insert(Key key, Value value) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (values.containsKey(key)) {
            final Value val = values.get(key);

            if (!value.equals(val)) {
                throw new IllegalArgumentException("Parameter and stored value are different");
            }

            return refresh(key);
        }
        else {
            values.put(key, value);
            return value;
        }
    }

    /**
     * Refreshs the timestamp of a key entry. That means that the cache will consider they key-value pair
     * as a new entry.
     *
     * @param key specified key
     * @return    value
     */
    public Value refresh(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (!values.containsKey(key)) {
            throw new NullPointerException("Key not found");
        }

        final Value val = values.get(key);

        values.remove(key);
        values.put(key, val);

        return val;
    }

    /**
     * Asserts that the maxSize is valid. If not, an exception is thrown.
     */
    protected void assertMaxSizeIsValid(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Invalid maxSize");
        }
    }
}
