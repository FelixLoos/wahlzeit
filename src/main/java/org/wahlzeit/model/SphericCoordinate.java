package org.wahlzeit.model;

/**
 * The immutable {@code SphericCoordinate} class represents a coordinate in the real world.
 * It has a latitude and longitude value to specify its position.
 */
public class SphericCoordinate extends AbstractCoordinate {

    public static final double EARTH_RADIUS_METERS = 6371000.0;

    public static final double MIN_LATITUDE = -90.0;
    public static final double MAX_LATITUDE = 90.0;

    public static final double MIN_LONGITUDE = -180.0;
    public static final double MAX_LONGITUDE = 180.0;


    protected final double latitude;
    protected final double longitude;
    protected final double radius;

    protected final CartesianCoordinate asCartesianCoordinate;

    /**
     * Overloading constructor for SphericCoordinate. Uses the earth radius in
     * meters as default parameter for the radius.
     *
     * @param  latitude   Latitude of the coordinate in degrees
     * @param  longitude  Longitude of the coordinate in degrees
     */
    public SphericCoordinate(double latitude, double longitude) {
        this(latitude, longitude, EARTH_RADIUS_METERS);
    }

    /**
     * Constructs a newly allocated {@code SphericCoordinate} object that represents
     * the specified {@code latitude} and {@code longitude} value.
     * An IllegalArgumentException is thrown for invalid {@code latitude} or
     * {@code longitude} values.
     *
     * @param  latitude   Latitude of the coordinate in degrees
     * @param  longitude  Longitude of the coordinate in degrees
     * @param  radius     radius
     */
    public SphericCoordinate(double latitude, double longitude, double radius) {
        assertLatitudeIsValid(latitude);
        assertLongitudeIsValid(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        this.asCartesianCoordinate = calculateCartesianCoordinate();
    }

    /**
     * @return  the latitude value of the coordinate in degrees
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * @return  the longitude value of the coordinate in degrees
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return  the radius value of the coordinate
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Returns the specific CartesianCoordinate implementation for the SphericCoordinate object.
     *
     * @return CartesianCoordinate object
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return asCartesianCoordinate;
    }

    /**
     * Calculates the CartesianCoordinate representation for the SphericCoordinate.
     *
     * @return CartesianCoordinate object
     */
    protected CartesianCoordinate calculateCartesianCoordinate() {
        double latRadian = Math.toRadians(latitude);
        double longRadian = Math.toRadians(longitude);
        double x = radius * Math.cos(latRadian) * Math.cos(longRadian);
        double y = radius * Math.cos(latRadian) * Math.sin(longRadian);
        double z = radius * Math.sin(latRadian);
        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Checks if latitude is valid. Throws an IllegalArgumentException if not.
     *
     * @param latitude
     */
    protected void assertLatitudeIsValid(double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }
    }

    /**
     * Checks if longitude is valid. Throws an IllegalArgumentException if not.
     *
     * @param longitude
     */
    protected void assertLongitudeIsValid(double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}
