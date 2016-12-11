package org.wahlzeit.utils;

public class AssertUtil {

    public static void assertArgumentIsNotNull(Object object, String name) {
        if (object == null) {
            throw new IllegalArgumentException("Argument " + name + " may not be null.");
        }
    }

    public static void assertArgumentIsValidDouble(double d, String name) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            throw new IllegalArgumentException("Double " + name + " is not valid.");
        }
    }
}
