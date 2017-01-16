package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class AlcoholPhotoTest {

    @ClassRule
    public static TestRule chain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

    private AlcoholPhoto photo;
    private Alcohol alcohol;

    @Before
    public void setUp() {
        alcohol = AlcoholManager.createAlcohol("Hersbrucker Lager", "lager", new String[] { "water", "hops" });
        photo = new AlcoholPhoto(alcohol);
    }

    @Test
    public void alcoholTypeTest() {
        Assert.assertEquals(alcohol, photo.getAlcohol());
    }
}
