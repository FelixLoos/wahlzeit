package org.wahlzeit.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Alcohol {
    private static final AtomicInteger counter = new AtomicInteger();

    private final int id;
    private final String alcoholName;
    private final AlcoholType alcoholType;

    public Alcohol(AlcoholType alcoholType, String alcoholName) {
        this.alcoholType = alcoholType;
        this.alcoholName = alcoholName;

        this.id = counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getAlcoholName() {
        return alcoholName;
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }
}
