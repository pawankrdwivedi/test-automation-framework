package com.iris.automation.test;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("feature") // Folder in src/test/resources
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.iris.automation.test")
public class RunCucumberTest {
    // empty — JUnit 5 + Cucumber will auto-discover tests
}
