package test.cds.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cds.groovy.controllers.ControllerTests;
import test.cds.groovy.dao.UsersDaoTests;

@RunWith(Suite.class)
@SuiteClasses({ ControllerTests.class,
				UsersDaoTests.class})

public class AllTests {

}
