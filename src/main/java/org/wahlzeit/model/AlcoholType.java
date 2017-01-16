package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;
import org.wahlzeit.utils.AssertUtil;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AlcoholType extends DataObject {

    private AlcoholType superType;
    private Set<AlcoholType> subTypes;

    private String typeName;

    private String[] ingredients;

    public AlcoholType(String typeName, String[] ingredients) {
        this.typeName = typeName;
        this.ingredients = ingredients;
        this.subTypes = new HashSet<>();
    }

    public Alcohol createInstance(String alcoholName) {
        return new Alcohol(this, alcoholName);
    }

    public String getTypeName() {
        return typeName;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public AlcoholType getSuperType() {
        return superType;
    }

    public Iterator<AlcoholType> getSubTypesIterator() {
        return subTypes.iterator();
    }

    public void addSubType(AlcoholType alcoholType) {
        AssertUtil.assertArgumentIsNotNull(alcoholType, "alcoholType");
        assertAlcoholTypeIsNotParent(alcoholType);
        assertAlcoholTypeIsNotSelf(alcoholType);

        // super type exists already -> remove it
        if (alcoholType.superType != null) {
            alcoholType.superType.subTypes.remove(alcoholType);
        }

        alcoholType.superType = this;
        this.subTypes.add(alcoholType);
    }

    /**
     * Checks whether the local element is a subType of the given parameter.
     *
     * @param other
     * @return
     */
    public boolean isSubtypeOf(AlcoholType other) {
        AlcoholType parent = this.superType;

        while (parent != null) {
            if (parent.equals(other)) {
                return true;
            }

            parent = parent.superType;
        }
        return false;
    }

    private void assertAlcoholTypeIsNotParent(AlcoholType alcoholType) {
        if (this.isSubtypeOf(alcoholType)) {
            throw new IllegalArgumentException("The given alcoholType is already a subtype");
        }
    }

    private void assertAlcoholTypeIsNotSelf(AlcoholType alcoholType) {
        if (this.equals(alcoholType)) {
            throw new IllegalArgumentException("Can't use itself as parameter");
        }
    }
}