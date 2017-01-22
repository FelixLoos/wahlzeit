package org.wahlzeit.model;

public class Alcohol {
    private final String alcoholName;
    private final AlcoholType alcoholType;

    public Alcohol(String alcoholName, AlcoholType alcoholType) {
        this.alcoholName = alcoholName;
        this.alcoholType = alcoholType;
    }

    public String getAlcoholName() {
        return alcoholName;
    }

    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alcohol alcohol = (Alcohol) o;

        if (alcoholName != null ? !alcoholName.equals(alcohol.alcoholName) : alcohol.alcoholName != null) return false;
        return alcoholType != null ? alcoholType.equals(alcohol.alcoholType) : alcohol.alcoholType == null;
    }

    @Override
    public int hashCode() {
        int result = alcoholName != null ? alcoholName.hashCode() : 0;
        result = 31 * result + (alcoholType != null ? alcoholType.hashCode() : 0);
        return result;
    }
}
