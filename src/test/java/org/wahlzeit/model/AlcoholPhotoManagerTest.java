package org.wahlzeit.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AlcoholPhotoManagerTest {

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
