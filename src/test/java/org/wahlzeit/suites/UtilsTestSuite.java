package org.wahlzeit.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.utils.AssertUtilTest;
import org.wahlzeit.utils.StringUtilTest;
import org.wahlzeit.utils.VersionTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AssertUtilTest.class,
        StringUtilTest.class,
        VersionTest.class
})
public class UtilsTestSuite { }
