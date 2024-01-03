package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		//features = {".//Features//LoginCSC.feature",".//Features//MSISDN_login.feature"},
		features = {".//Features/"},
		glue = {"stepDefinitions"},
		dryRun=false,
		monochrome = true,
		plugin= {"pretty","html:report/html-test-outputReport.html"},
		tags = "@CSC"
		)

public class TestRunner {

}
