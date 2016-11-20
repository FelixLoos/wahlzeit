package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;

public class AlcoholPhotoManagerTest {

    @ClassRule
    public static TestRule chain = RuleChain.outerRule(new LocalDatastoreServiceTestConfigProvider());

    private AlcoholPhotoManager manager;

    @Before
    public void setUp() {
        manager = new AlcoholPhotoManager();
    }

    @Test
    public void initializeTest() {
        Assert.assertNotNull(manager);
    }

    @Test
    public void classTest() {
        Assert.assertEquals(AlcoholPhotoManager.class, manager.getClass());
    }
}
