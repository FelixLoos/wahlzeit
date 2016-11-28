package org.wahlzeit.model;

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
        assertCoordinateIsNotNull(coordinate);

        final CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
        final CartesianCoordinate coordinateThat = coordinate.asCartesianCoordinate();

        return coordinateThis.doGetDistance(coordinateThat);
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

        return coordinateThis.doIsEqual(coordinateThat);
    }

    /**
     * Computes the hash-code for the given Coordinate.
     *
     * @return
     */
    @Override
    public int hashCode() {
        final CartesianCoordinate coordinateThis = this.asCartesianCoordinate();
        return coordinateThis.doHashCode();
    }

    /**
     * Asserts that the given parameter (Coordinate) ist not null. If it is null, an exception is thrown.
     *
     * @param coordinate
     */
    protected void assertCoordinateIsNotNull(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate must not be empty");
        }
    }
}
