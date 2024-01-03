package stepDefinitions;

import java.time.Duration;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.LoginPage;
import pageObjects.MSISNDLoginPage;

public class BaseClass {

	public WebDriver driver;
	public LoginPage loginPage;
	public MSISNDLoginPage 	msisdnLoginPage;
	public static Logger logger;
	public Properties configProp;
	
	public void waitForElement(WebElement ele, long timeUnits) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeUnits));
		
		wait.until(ExpectedConditions.visibilityOf(ele));
		
	}
}
