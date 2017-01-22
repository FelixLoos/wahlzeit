package org.wahlzeit.model;

import org.wahlzeit.model.cache.Cache;
import org.wahlzeit.model.cache.ObjectCache;

public class AlcoholManager {

    private static AlcoholManager instance;

    private final Cache<Alcohol, Alcohol> alcoholCache;
    private final Cache<AlcoholType, AlcoholType> alcoholTypeCache;

    public AlcoholManager() {
        alcoholCache = new ObjectCache<>();
        alcoholTypeCache = new ObjectCache<>();
    }

    public static synchronized AlcoholManager getInstance() {
        if (instance == null) {
            instance = new AlcoholManager();
        }
        return instance;
    }

    public synchronized AlcoholType getAlcoholTypeInstance(String typeName, String[] ingredients) {
        final AlcoholType alcoholType = createAlcoholType(typeName, ingredients);
        return alcoholTypeCache.getOrInsert(alcoholType, alcoholType);
    }

    public synchronized Alcohol getAlcoholInstance(String name, AlcoholType alcoholType) {
        final Alcohol alcohol = createAlcohol(name, alcoholType);
        return alcoholCache.getOrInsert(alcohol, alcohol);
    }

    private Alcohol createAlcohol(String name, AlcoholType alcoholType) {
        return new Alcohol(name, alcoholType);
    }

    private AlcoholType createAlcoholType(String typeName, String[] ingredients) {
        return new AlcoholType(typeName, ingredients);
    }
}
