package test.cds.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cds.groovy.controllers.RecRegControllerTests;
import test.cds.groovy.controllers.RestControllerTests;
import test.cds.groovy.controllers.UserControllerTests;
import test.cds.groovy.controllers.WelcomeControllerTests;
import test.cds.groovy.dao.UsersDaoTests;
import test.cds.groovy.service.RecRegLookupServiceTests;

@RunWith(Suite.class)
@SuiteClasses({ RecRegControllerTests.class,
				RestControllerTests.class,
				UserControllerTests.class,
				WelcomeControllerTests.class,
				UsersDaoTests.class,
				RecRegLookupServiceTests.class
			  })
public class ProjectTestSuite {

}
