package org.wahlzeit.model;

public class Coordinate {

    public static final float EARTH_RADIUS_METERS = 6371000;

    private final double latitude;
    private final double longitude;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getDistance(Coordinate that) {
        double dLatitude = Math.toRadians(that.latitude - this.latitude);
        double dLongitude = Math.toRadians(that.longitude - this.longitude);

        double a = Math.pow(Math.sin(dLatitude / 2), 2) + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(that.latitude)) * Math.pow(Math.sin(dLongitude / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = EARTH_RADIUS_METERS * c;

        return dist;
    }
}
