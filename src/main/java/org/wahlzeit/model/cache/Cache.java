package org.wahlzeit.model.cache;

public interface Cache<Key, Value> {

    boolean contains(Key key);

    Value get(Key key);

    Value insert(Key key, Value value);

    Value getOrInsert(Key key, Value value);

    void delete(Key key);
}
