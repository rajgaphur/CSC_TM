package pageObjects;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MSISNDLoginPage {

	public WebDriver driver;
	
	public String subscriberId;
	
	public MSISNDLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@id=\'branding-logo\']")
	@CacheLookup
	WebElement tmoLogo;
	
	@FindBy(xpath="//*[@id=\'branding-application\']")
	@CacheLookup
	WebElement CustomerServiceConsoleText;
	
	@FindBy(xpath="//*[@id=\'branding-application-version\']")
	@CacheLookup
	WebElement versionDetails;
	
	@FindBy(xpath="//*[@id=\'appLink_csrname\']")
	@CacheLookup
	WebElement usernameText;
	
	@FindBy(xpath="//*[@id=\'appLink_logout\']/span/a")
	@CacheLookup
	WebElement logOffButton;
	
	@FindBy(xpath="//*[@id=\'ui-dialog-title-subscriberIDDlg\']")
	@CacheLookup
	WebElement SubscriberIdText	;
	
	@FindBy(id="subscriberid")
	@CacheLookup
	WebElement msisdnId;
	
	@FindBy(xpath="//*[@id=\'errorList\']")
	@CacheLookup
	WebElement msisdnIdError;
	
	
	@FindBy(xpath="/html/body/div[3]/div[3]/div/button/span")
	@CacheLookup
	WebElement createSession;
	
	//---------Table List Of SerialNumbers----------
	@FindBy(xpath="//*[@id='listOfCells']/thead/tr/th")
	@CacheLookup
	List<WebElement> tableHeader;
	
	@FindBy(xpath="//*[@id='listOfCells']/tbody/tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(xpath="//*[@id='listOfCells']/tbody/tr/td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void getTMOlogoDetails() {		
		System.out.println("Is TMO Logo Displayed? : "+tmoLogo.isDisplayed());
		System.out.println("TMO Logo's Location : "+tmoLogo.getLocation());
		System.out.println("TMO Logo's size : \n Height - "+tmoLogo.getSize().getHeight() + "  Width - "+tmoLogo.getSize().getWidth());
	}
	
	public String getCSCheaderText() {		
		
		System.out.println("Is CSC Header Text Displayed? : "+CustomerServiceConsoleText.isDisplayed());
		System.out.println("CSC Header Text's Location : "+CustomerServiceConsoleText.getLocation());
		return CustomerServiceConsoleText.getText();
	}
	
	public String getVersionText() {		
		System.out.println("Is Version Text Displayed? : "+versionDetails.isDisplayed());
		System.out.println("Version Text's Location : "+versionDetails.getLocation());
		return versionDetails.getText();
	}
	
	public String getUserNameText() {
		System.out.println("Is User name Text Displayed? : "+usernameText.isDisplayed());
		System.out.println("User name Text's Location : "+usernameText.getLocation());
		return usernameText.getText();
	}
	
	public String getLogOffDetails() {
		System.out.println("Is LogOff button Displayed? : "+logOffButton.isDisplayed());
		System.out.println("LogOff button's Location : "+logOffButton.getLocation());
		return logOffButton.getText();
	}
	
	public void logOff() {
		logOffButton.click();
	}
	
	public void setMSISDN(String msisdn) {
		this.subscriberId = msisdn;
		msisdnId.clear();
		msisdnId.sendKeys(this.subscriberId);
	}
	
	public String getInvalidMSISDNErrMsg() {
		return msisdnIdError.getText();
	}
	
	public void createSession() {
		createSession.click();
	}
		
	public int getRowSize() {
		return tableRows.size();
	}
	
	public int getColumnSize() {
		return tableColumns.size();
	}
	public void listSerialNumbers() {
		////*[@id='listOfCells']/tbody/tr[1]/td[2]
		System.out.println("List of Serial Numbers\n");
		for(int i=2; i<=tableHeader.size(); i++) {
			WebElement header = driver.findElement(By.xpath("//*[@id='listOfCells']/thead/tr/th["+i+"]"));
			System.out.print(header.getText()+"\t");
		}
		
		System.out.println();
		System.out.println("Rows :"+getRowSize()+", Columns:"+getColumnSize());
		for (int i = 1; i<= getRowSize() ; i++) {
			for (int j = 2; j<=6; j++) {
			 WebElement row = driver.findElement(By.xpath("//*[@id='listOfCells']/tbody/tr["+i+"]/td["+j+"]"));
			 if(row.getText().isEmpty()) {
				 System.out.print(" <Empty> \t");
			 }else {
				 System.out.print(row.getText()+"\t");
			 }
			  
			}
			System.out.println();
		}
	}
	
}
