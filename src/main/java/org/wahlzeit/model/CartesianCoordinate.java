package org.wahlzeit.model;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsValidDouble;

/**
 * The immutable {@code CartesianCoordinate} class represents a 3D-coordinate. It uses
 * the x/y/z-axis to specify its position.
 */
public class CartesianCoordinate extends AbstractCoordinate {

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
        assertArgumentIsValidDouble(x, "x");
        assertArgumentIsValidDouble(y, "y");
        assertArgumentIsValidDouble(z, "z");
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
     * Converts the Coordinate into a CartesianCoordinate object. Since this is the
     * CartesianCoordinate implementation, it returns this.
     *
     * @return a CartesianCoordinate object (this)
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }

    /**
     * Calculates and returns the shortest distance between two CartesianCoordinate objects.
     *
     * @param  that  another CartesianCoordinate object
     * @return       shortest distance
     */
    protected double doGetDistance(CartesianCoordinate that) {
        double dx = this.x - that.x;
        double dy = this.y - that.y;
        double dz = this.z - that.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    /**
     * Compares this with another CartesianCoordinate object.
     *
     * @param   that  the other CartesianCoordinate object
     * @return        returns true if the two objects are equal
     */
    protected boolean doIsEqual(CartesianCoordinate that) {
        return Double.compare(this.x, that.x) == 0 &
                Double.compare(this.y, that.y) == 0 &&
                Double.compare(this.z, that.z) == 0;
    }

    /**
     * Calculates the hash-code for this CartesianCoordinate.
     *
     * @return  hash-code
     */
    protected int doHashCode() {
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
}
