package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlcoholPhotoTest {

    private AlcoholPhoto photo;

    @Before
    public void setUp() {
        photo = new AlcoholPhoto();
    }

    @Test
    public void alcoholTypeTest() {
        photo.setAlcoholType(AlcoholType.BEER);

        Assert.assertEquals(AlcoholType.BEER, photo.getAlcoholType());
    }

    @Test
    public void brandTest() {
        String brand = "Jack Daniels";
        photo.setBrand(brand);

        Assert.assertEquals(brand, photo.getBrand());
    }

    @Test
    public void percentageTest() {
        photo.setPercentage(50.7f);

        Assert.assertEquals(50.7f, photo.getPercentage(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPercentageTest() {
        photo.setPercentage(100.1f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidPercentageTest2() {
        photo.setPercentage(-0.01f);
    }
}
