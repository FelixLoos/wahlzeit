package org.wahlzeit.model.cache;

import org.wahlzeit.utils.AssertUtil;

import java.util.HashMap;
import java.util.Map;

public class ObjectCache<Key, Value> implements Cache<Key, Value> {

    private final Map<Key, Value> values;

    public ObjectCache() {
        values = new HashMap<>();
    }

    @Override
    public boolean contains(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        return values.containsKey(key);
    }

    @Override
    public synchronized Value get(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (!values.containsKey(key)) {
            throw new NullPointerException("Key not found");
        }

        return values.get(key);
    }

    @Override
    public synchronized Value insert(Key key, Value value) {
        if (!values.containsKey(key)) {
            values.put(key, value);
            return value;
        }
        else {
            throw new IllegalStateException("Key already exists");
        }
    }

    @Override
    public synchronized Value getOrInsert(Key key, Value value) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (values.containsKey(key)) {
            final Value val = values.get(key);

            if (!value.equals(val)) {
                throw new IllegalArgumentException("Parameter and stored value are different");
            }

            return val;
        }
        else {
            values.put(key, value);
            return value;
        }
    }

    @Override
    public synchronized void delete(Key key) {
        AssertUtil.assertArgumentIsNotNull(key, "key");

        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("The key " + key + " does not exist");
        }

        values.remove(key);
    }
}
