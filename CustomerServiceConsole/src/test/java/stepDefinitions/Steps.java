package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.MSISNDLoginPage;
import utilities.Utils;

public class Steps extends BaseClass {

	@Before
	public void setUp() throws FileNotFoundException, IOException {

		// Load the config.properties file
		// configProp = new Properties();
		// configProp.load(new FileInputStream(new File("config.properties")));

		configProp = Utils.readPropFile("config.properties");

		logger = Logger.getLogger("CustomerServiceConsole");
		
		PropertyConfigurator.configure("log4j.properties");

		// Initially
		// System.setProperty("webdriver.chrome.driver",
		// System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
		// driver = new ChromeDriver();
		logger.info("********************Config Properties******************");
		Utils.getFileContents("config.properties");

		logger.info("********************Opening Browser********************");
		String browser = configProp.getProperty("browser");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
			driver = new ChromeDriver();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		} else if (browser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", configProp.getProperty("msedgepath"));
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		
	}

	@Given("User Launch Chrome browser")
	public void user_launch_chrome_browser() {

		// Adding Logger - log4j
		// Moving below content to setup()
		/*
		 * logger = Logger.getLogger("CustomerServiceConsole");
		 * PropertyConfigurator.configure("log4j.properties");
		 * 
		 * System.setProperty("webdriver.chrome.driver",
		 * System.getProperty("user.dir")+"//Drivers//chromedriver.exe"); driver = new
		 * ChromeDriver(); driver.manage().window().maximize();
		 */
		loginPage = new LoginPage(driver);
		msisdnLoginPage = new MSISNDLoginPage(driver);
	}

	@When("User Opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("***********Opening URL****************");
		driver.get(url);

	}

	@When("User enters Username as {string} and Password as {string}")
	public void user_enters_username_as_and_password_as(String uname, String pwd) {
		loginPage.setUserName(uname);
		loginPage.setPassword(pwd);

	}

	@When("Click on SIGNIN")
	public void click_on_signin() {
		loginPage.login();

	}

	@Then("Page Title should be {string}")
	public void page_title_should_be(String pageTitle) {

		if (driver.getPageSource().contains("The username or password you entered is incorrect.")) {
			System.out.println("Not landed in expected page");
			driver.close();
			Assert.assertTrue(false);
		} else {
			String title = driver.getTitle();
			System.out.println("landed in expected page");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Assert.assertEquals(pageTitle, title);
		}
	}

	@Then("Close Browser")
	public void close_browser() {

		driver.quit();
	}

	// Adding MSISDN login methods

	@When("Subscriber enters MSISDN {string}")
	public void subscriber_enters_msisdn(String msisdn) {
		msisdnLoginPage.setMSISDN(msisdn);
	}

	@When("Subscriber enters Invalid MSISDN as {string}")
	public void subscriber_enters_invalid_msisdn_as(String msisdnErr) {
		
		msisdnLoginPage.setMSISDN(msisdnErr);
		
	}
	
	@Then("User should see the appropriate error message")
	public void user_should_see_the_appropriate_error_message() {
		String msisdnErrMsgPrefix = "";
		String msisdnErr = msisdnLoginPage.subscriberId;
		System.out.println("Entered msisdn is : "+msisdnErr);
		
		
		if(Utils.hasSplChar(msisdnErr) || msisdnErr.isEmpty()) {
			
			msisdnErrMsgPrefix = "subscriber form submit invalid";
			System.out.println(msisdnErrMsgPrefix);
			
		}else {
			if((msisdnErr.length() > 10) || Utils.isAlphaNumeric(msisdnErr)) {
				msisdnErrMsgPrefix = "No Small Cells Found for MSISDN - "+msisdnErr;
				System.out.println(msisdnErrMsgPrefix);
				Assert.assertEquals("Invalid MSISDN", msisdnErrMsgPrefix, msisdnLoginPage.getInvalidMSISDNErrMsg());
			}			
		}
	}

	@When("click on Create Session button")
	public void click_on_create_session_button() {
		msisdnLoginPage.createSession();
	}

	@Then("List all the Serial Numbers")
	public void list_all_the_serial_numbers() {
		System.out.println("Successful Logged in to SerialNumber list: " + driver.getTitle());
		msisdnLoginPage.listSerialNumbers();
	}

	@Then("User name and Logoff button are visible at top-right corner")
	public void user_name_and_logoff_button_are_visible_at_top_right_corner() {
		String usernameText = msisdnLoginPage.getUserNameText();
		System.out.println("UserName Text : " + usernameText);

		String logOffBtnText = msisdnLoginPage.getLogOffDetails();
		System.out.println("LogOff Button's text : " + logOffBtnText);

		Assert.assertEquals("User Name Text", "consoleuser", usernameText);
		Assert.assertEquals("LogOff Button Text", "  LOG OFF", logOffBtnText);
	}

	@Then("TMO Logo, CSC text and build info are visible at top")
	public void tmo_logo_csc_text_and_build_info_are_visible_at_top() {
		msisdnLoginPage.getTMOlogoDetails();

		String cscHeaderText = msisdnLoginPage.getCSCheaderText();
		System.out.println("CSC Header : " + cscHeaderText);

		String versionDetails = msisdnLoginPage.getVersionText();
		System.out.println("Version Details : " + versionDetails);
		String versionData = "Version: 23.2_SCM-SNAPSHOT, Build: 1107, Timestamp: 24_01_05_06:41";
		// String versionData = "Version: "+getversion(22.3_SCM-SNAPSHOT)+", Build:
		// "+get(Build950)+", Timestamp: "+getTimestramp(22_07_07_15:31)";
		Assert.assertEquals("CSC Hearder", "Customer Service Console", cscHeaderText);
		Assert.assertEquals("Version Details", versionData, versionDetails);
	}
}
