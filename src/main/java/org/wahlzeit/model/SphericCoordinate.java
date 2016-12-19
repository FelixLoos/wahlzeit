package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsValidDouble;

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


    private static final Cache<String, SphericCoordinate> cache = new Cache<>();

    /**
     * Returns a SphericCoordinate with the given parameters. Ensures that a SphericCoordinate object
     * with the same values is not created twice.
     *
     * @param latitude  Latitude of the coordinate in degrees
     * @param longitude Longitude of the coordinate in degrees
     * @param radius    Radius of the coordinate
     * @return          The new SphericCoordinate object
     */
    public static SphericCoordinate getInstance(double latitude, double longitude, double radius) {
        String key = getKey(latitude, longitude, radius);

        if (cache.contains(key)) {
            return cache.get(key);
        }
        else {
            return cache.insert(key, new SphericCoordinate(latitude, longitude, radius));
        }
    }

    /**
     * Overloading method. Offers default value for radius.
     *
     * @param latitude  Latitude of the coordinate in degrees
     * @param longitude Longitude of the coordinate in degrees
     * @return          The new SphericCoordinate object
     */
    public static SphericCoordinate getInstance(double latitude, double longitude) {
        return getInstance(latitude, longitude, EARTH_RADIUS_METERS);
    }

    /**
     * Creates a unique key that is necessary for storing the SphericCoordinate object in the cache.
     *
     * @param latitude  Latitude of the coordinate in degrees
     * @param longitude Longitude of the coordinate in degrees
     * @param radius    Radius of the coordinate
     * @return          The unique (for the given parameters) key
     */
    private static String getKey(double latitude, double longitude, double radius) {
        return String.valueOf(latitude) + " " + String.valueOf(longitude) + " " + String.valueOf(radius);
    }


    private final double latitude;
    private final double longitude;
    private final double radius;

    private final CartesianCoordinate asCartesianCoordinate;

    /**
     * Overloading constructor for SphericCoordinate. Uses the earth radius in
     * meters as default parameter for the radius.
     *
     * @param  latitude   Latitude of the coordinate in degrees
     * @param  longitude  Longitude of the coordinate in degrees
     */
    private SphericCoordinate(double latitude, double longitude) {
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
    private SphericCoordinate(double latitude, double longitude, double radius) {
        assertLatitudeIsValid(latitude);
        assertLongitudeIsValid(longitude);
        assertRadiusIsValid(radius);

        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;

        this.asCartesianCoordinate = calculateCartesianCoordinate();

        assertClassInvariants();
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
    private CartesianCoordinate calculateCartesianCoordinate() {
        double latRadian = Math.toRadians(latitude);
        double longRadian = Math.toRadians(longitude);
        double x = radius * Math.cos(latRadian) * Math.cos(longRadian);
        double y = radius * Math.cos(latRadian) * Math.sin(longRadian);
        double z = radius * Math.sin(latRadian);
        return CartesianCoordinate.getInstance(x, y, z);
    }

    /**
     * Check the class invariants.
     */
    @Override
    protected void assertClassInvariants() {
        assertLatitudeIsValid(latitude);
        assertLongitudeIsValid(longitude);
        assertRadiusIsValid(radius);
    }

    /**
     * Checks if the latitude is valid. Throws an IllegalArgumentException if not.
     *
     * @param latitude
     */
    private void assertLatitudeIsValid(double latitude) {
        assertArgumentIsValidDouble(latitude, "latitude");

        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }
    }

    /**
     * Checks if the longitude is valid. Throws an IllegalArgumentException if not.
     *
     * @param longitude
     */
    private void assertLongitudeIsValid(double longitude) {
        assertArgumentIsValidDouble(longitude, "longitude");

        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }
    }

    /**
     * Checks if the radius is valid. Throws an IllegalArgumentException if not.
     *
     * @param radius
     */
    private void assertRadiusIsValid(double radius) {
        assertArgumentIsValidDouble(radius, "radius");

        if (radius <= 0) {
            throw new IllegalArgumentException("Invalid radius: " + radius);
        }
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}
