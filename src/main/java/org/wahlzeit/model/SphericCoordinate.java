package org.wahlzeit.model;

/**
 * The immutable {@code SphericCoordinate} class represents a coordinate in the real world.
 * It has a latitude and longitude value to specify its position.
 */
public class SphericCoordinate implements Coordinate {

    public static final double EARTH_RADIUS_METERS = 6371000.0;

    public static final double MIN_LATITUDE = -90.0;
    public static final double MAX_LATITUDE = 90.0;

    public static final double MIN_LONGITUDE = -180.0;
    public static final double MAX_LONGITUDE = 180.0;


    protected final double latitude;
    protected final double longitude;

    /**
     * Constructs a newly allocated {@code SphericCoordinate} object that represents
     * the specified {@code latitude} and {@code longitude} value.
     * An IllegalArgumentException is thrown for invalid {@code latitude} or
     * {@code longitude} values.
     *
     * @param  latitude   Latitude of the coordinate in degrees
     * @param  longitude  Longitude of the coordinate in degrees
     */
    public SphericCoordinate(double latitude, double longitude) {
        assertLatitudeIsValid(latitude);
        assertLongitudeIsValid(longitude);

        this.latitude = latitude;
        this.longitude = longitude;
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
     * Calculates and returns the distance to another coordinate. The distance is calculated in meters.
     *
     * @param  coordinate  the other coordinate
     * @return             distance in meters
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        if (!(coordinate instanceof SphericCoordinate)) {
            throw new UnsupportedOperationException("Unsupported class type " + coordinate.getClass());
        }

        SphericCoordinate that = (SphericCoordinate) coordinate;

        double dLatitude = Math.toRadians(that.latitude - this.latitude);
        double dLongitude = Math.toRadians(that.longitude - this.longitude);

        double a = Math.pow(Math.sin(dLatitude / 2), 2) + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(that.latitude)) * Math.pow(Math.sin(dLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS_METERS * c;

        return distance;
    }

    /**
     * Checks if latitude is valid. Throws an IllegalArgumentException if not.
     * @param latitude
     */
    private void assertLatitudeIsValid(double latitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }
    }

    /**
     * Checks if longitude is valid. Throws an IllegalArgumentException if not.
     * @param longitude
     */
    private void assertLongitudeIsValid(double longitude) {
        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || !(object instanceof SphericCoordinate)) {
            return false;
        }

        SphericCoordinate that = (SphericCoordinate) object;

        if (Double.compare(that.latitude, latitude) != 0 ||
            Double.compare(that.longitude, longitude) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        long temp = 0;

        temp = Double.doubleToLongBits(latitude);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return "(" + latitude + ", " + longitude + ")";
    }
}
