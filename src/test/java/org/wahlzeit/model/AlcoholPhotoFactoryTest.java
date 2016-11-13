package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Test;

public class AlcoholPhotoFactoryTest {

    @Test
    public void createAlcoholPhotoTest() {
        AlcoholPhotoFactory factory = AlcoholPhotoFactory.getInstance();
        Assert.assertEquals(AlcoholPhoto.class, factory.createPhoto().getClass());
        Assert.assertEquals(AlcoholPhoto.class, factory.createPhoto(new PhotoId(1)).getClass());
        Assert.assertEquals(AlcoholPhoto.class, factory.createPhoto(new PhotoId(1), AlcoholType.BEER).getClass());
    }

    @Test
    public void alcoholPhotoIdTest() {
        AlcoholPhoto photo = AlcoholPhotoFactory.getInstance().createPhoto(new PhotoId(123));
        Assert.assertEquals(123, photo.getId().asInt());
    }

    @Test
    public void alcoholPhotoTypeTest() {
        AlcoholPhoto photo = AlcoholPhotoFactory.getInstance().createPhoto(new PhotoId(123), AlcoholType.BEER);
        Assert.assertEquals(AlcoholType.BEER, photo.getAlcoholType());
    }
}
