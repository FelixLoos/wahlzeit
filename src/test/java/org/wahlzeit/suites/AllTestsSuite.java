package org.wahlzeit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HandlersTestSuite.class,
        ModelTestSuite.class,
        ServicesTestSuite.class,
        UtilsTestSuite.class
})
public class AllTestsSuite { }
