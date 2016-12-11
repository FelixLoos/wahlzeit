package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsNotNull;

/**
 * The AbstractCoordinate class is a generalization for the specific implementation classes
 * of the Coordinate interface. It provides code-reuse for the getDistance and equals methods.
 */
public abstract class AbstractCoordinate implements Coordinate {

    /**
     * Calculates and returns the shortest distance to another coordinate.
     *
     * @param  coordinate  the other coordinate (may not be null)
     * @return             distance
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        assertArgumentIsNotNull(coordinate, "coordinate");

        final CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
        final CartesianCoordinate coordinateThat = coordinate.asCartesianCoordinate();

        final double distance = coordinateThis.doGetDistance(coordinateThat);

        assertClassInvariants();

        assert distance >= 0 : "Invalid distance value. Must be greater than or equal to 0.";

        return distance;
    }

    /**
     * Compares two Coordinate objects.
     *
     * @param   object  the object that is compared to the given instance
     * @return          returns true if the Coordinate object and the parameter are equal-
     */
    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || !(object instanceof Coordinate)) {
            return false;
        }

        final CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
        final CartesianCoordinate coordinateThat = ((Coordinate)object).asCartesianCoordinate();

        final boolean equal = coordinateThis.doIsEqual(coordinateThat);

        assertClassInvariants();

        return equal;
    }

    /**
     * Computes the hash-code for the given Coordinate.
     *
     * @return
     */
    @Override
    public int hashCode() {
        final CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
        assertArgumentIsNotNull(coordinateThis, "coordinate");

        final int hashCode = coordinateThis.doHashCode();

        assertClassInvariants();

        return hashCode;
    }

    /**
     * Check the class invariants. Can be overridden by subclasses to check class invariants.
     */
    protected void assertClassInvariants() {

    }
}
