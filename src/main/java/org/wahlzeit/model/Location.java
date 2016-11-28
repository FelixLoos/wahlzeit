package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Ignore;

/**
 * The {@code Location} class stores a {@code Coordinate} object.
 */
public class Location {

    @Ignore
    private Coordinate coordinate;

    /**
     * @param  coordinate  the coordinate of the location
     */
    public Location(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return  the coordinate of the location
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param  coordinate  the new value for the coordinate of the location
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
