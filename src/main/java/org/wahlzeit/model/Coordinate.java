package org.wahlzeit.model;

/**
 * The {@code Coordinate} class represents a specific coordinate.
 */
public interface Coordinate {

    /**
     * Calculates and returns the distance to another coordinate. The distance is calculated in meters.
     *
     * @param  coordinate  the other coordinate
     * @return             distance in meters
     */
    double getDistance(Coordinate coordinate);

    /**
     * Converts a given Coordinate into the specific CartesianCoordinate implementation.
     *
     * @return  a new CartesianCoordinate object
     */
    CartesianCoordinate asCartesianCoordinate();
}
