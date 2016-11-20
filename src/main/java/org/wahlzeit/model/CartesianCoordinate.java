package org.wahlzeit.model;

/**
 * The immutable {@code CartesianCoordinate} class represents a 3D-coordinate. It uses
 * the x/y/z-axis to specify its position.
 */
public class CartesianCoordinate implements Coordinate {

    protected final double x;
    protected final double y;
    protected final double z;

    /**
     * Constructs a newly allocated {@code CartesianCoordinate} object that represents
     * a specified 3D-coordinate (position).
     *
     * @param x  the value on the x-axis
     * @param y  the value on the y-axis
     * @param z  the value on the z-axis
     */
    public CartesianCoordinate(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * @return  the x coordinate value
     */
    public double getX() {
        return x;
    }

    /**
     * @return  the y coordinate value
     */
    public double getY() {
        return y;
    }

    /**
     * @return  the z coordinate value
     */
    public double getZ() {
        return z;
    }

    /**
     * Calculates and returns the distance to another coordinate. The distance is calculated in meters.
     *
     * @param  coordinate  the other coordinate
     * @return             distance in meters
     */
    @Override
    public double getDistance(Coordinate coordinate) {
        if (!(coordinate instanceof CartesianCoordinate)) {
            throw new UnsupportedOperationException("Unsupported class type " + coordinate.getClass());
        }

        CartesianCoordinate that = (CartesianCoordinate) coordinate;

        double dx = Math.pow(this.x - that.x, 2);
        double dy = Math.pow(this.y - that.y, 2);
        double dz = Math.pow(this.z - that.z, 2);
        double distance = Math.sqrt(dx + dy + dz);

        return distance;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || !(object instanceof CartesianCoordinate)) {
            return false;
        }

        CartesianCoordinate that = (CartesianCoordinate) object;

        if (Double.compare(that.x, x) != 0 ||
            Double.compare(that.y, y) != 0 ||
            Double.compare(that.z, z) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;

        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
}
