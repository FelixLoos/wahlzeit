package org.wahlzeit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.model.*;
import org.wahlzeit.model.persistence.DatastoreAdapterTest;
import org.wahlzeit.model.persistence.GcsAdapterTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        DatastoreAdapterTest.class,
        GcsAdapterTest.class,
        AccessRightsTest.class,
        AlcoholPhotoFactoryTest.class,
        AlcoholPhotoManagerTest.class,
        AlcoholPhotoTest.class,
        CartesianCoordinateTest.class,
        SphericCoordinateTest.class,
        FlagReasonTest.class,
        GenderTest.class,
        GuestTest.class,
        LocationTest.class,
        PhotoFilterTest.class,
        TagsTest.class,
        UserStatusTest.class,
        ValueTest.class
})
public class ModelTestSuite { }
