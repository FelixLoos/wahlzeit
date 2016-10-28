package org.wahlzeit.model;

/**
 * The immutable {@code Coordinate} class represents a coordinate in the real world.
 * It has a latitude and longitude value to specify its position.
 */
public class Coordinate {

    public static final double EARTH_RADIUS_METERS = 6371000.0;

    public static final double MIN_LATITUDE = -90.0;
    public static final double MAX_LATITUDE = 90.0;

    public static final double MIN_LONGITUDE = -180.0;
    public static final double MAX_LONGITUDE = 180.0;


    private final double latitude;
    private final double longitude;

    /**
     * Constructs a newly allocated {@code Coordinate} object that represents
     * the specified {@code latitude} and {@code longitude} value.
     * An IllegalArgumentException is thrown for invalid {@code latitude} or
     * {@code longitude} values.
     *
     * @param  latitude   Latitude of the coordinate in degrees
     * @param  longitude  Longitude of the coordinate in degrees
     */
    public Coordinate(double latitude, double longitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE) {
            throw new IllegalArgumentException("Invalid latitude: " + latitude);
        }

        if (longitude < MIN_LONGITUDE || longitude > MAX_LONGITUDE) {
            throw new IllegalArgumentException("Invalid longitude: " + longitude);
        }

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
     * Calculates and returns the distance to another coordinate (parameter). It is calculated in meters.
     *
     * @param  that  the other coordinate
     * @return       distance in meters
     */
    public double getDistance(Coordinate that) {
        double dLatitude = Math.toRadians(that.latitude - this.latitude);
        double dLongitude = Math.toRadians(that.longitude - this.longitude);

        double a = Math.pow(Math.sin(dLatitude / 2), 2) + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(that.latitude)) * Math.pow(Math.sin(dLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = EARTH_RADIUS_METERS * c;

        return dist;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || !(object instanceof Coordinate)) {
            return false;
        }

        Coordinate that = (Coordinate) object;

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
