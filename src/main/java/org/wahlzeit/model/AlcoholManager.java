package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;

public class AlcoholManager {

    private static final Map<String, AlcoholType> alcoholTypes = new HashMap<>();

    public static Alcohol createAlcohol(String alcoholName, String typeName, String[] ingredients) {
        final AlcoholType alcoholType = getAlcoholType(typeName, ingredients);
        final Alcohol alcohol = alcoholType.createInstance(alcoholName);
        return alcohol;
    }

    public static Alcohol createAlcohol(String alcoholName, AlcoholType alcoholType) {
        return createAlcohol(alcoholName, alcoholType.getTypeName(), alcoholType.getIngredients());
    }

    public static synchronized AlcoholType getAlcoholType(String typeName, String[] ingredients) {
        if (alcoholTypes.containsKey(typeName)) {
            return alcoholTypes.get(typeName);
        }

        final AlcoholType alcoholType = new AlcoholType(typeName, ingredients);

        alcoholTypes.put(typeName, alcoholType);

        return alcoholType;
    }
}
