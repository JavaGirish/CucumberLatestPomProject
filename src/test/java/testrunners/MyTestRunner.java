package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		glue = {"com.stepdefinitions", "com.hooks"},
		plugin = {"pretty"}, monochrome = true
		
		)

public class MyTestRunner {

}
