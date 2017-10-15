package test.cds.groovy.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RecRegControllerTests.class, 
				RestControllerTests.class,
				UserControllerTests.class, 
				WelcomeControllerTests.class })

public class ControllerTests {

}
