package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class AlcoholPhotoFactoryTest {

    @ClassRule
    public static TestRule chain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

    @Test
    public void createAlcoholPhotoTest() {
        AlcoholPhotoFactory factory = AlcoholPhotoFactory.getInstance();
        Assert.assertEquals(AlcoholPhoto.class, factory.createPhoto().getClass());
        Assert.assertEquals(AlcoholPhoto.class, factory.createPhoto(new PhotoId(1)).getClass());
    }

    @Test
    public void alcoholPhotoIdTest() {
        AlcoholPhoto photo = AlcoholPhotoFactory.getInstance().createPhoto(new PhotoId(123));
        Assert.assertEquals(123, photo.getId().asInt());
    }
}
