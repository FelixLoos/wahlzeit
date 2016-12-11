package org.wahlzeit.utils;

import org.junit.Test;

public class AssertUtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsNull() {
       AssertUtil.assertArgumentIsNotNull(null, "name");
    }

    @Test
    public void testArgumentIsNotNull() {
        AssertUtil.assertArgumentIsNotNull(new Object(), "object");
    }

    @Test
    public void testArgumentIsValidDouble() {
        AssertUtil.assertArgumentIsValidDouble(0.0, "double");
        AssertUtil.assertArgumentIsValidDouble(1.0, "double");
        AssertUtil.assertArgumentIsValidDouble(-1.0, "double");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsDoubleNaN() {
        AssertUtil.assertArgumentIsValidDouble(Double.NaN, "double");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsDoublePositiveInifinity() {
        AssertUtil.assertArgumentIsValidDouble(Double.POSITIVE_INFINITY, "double");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArgumentIsDoubleNegativeInifinity() {
        AssertUtil.assertArgumentIsValidDouble(Double.NEGATIVE_INFINITY, "double");
    }
}
