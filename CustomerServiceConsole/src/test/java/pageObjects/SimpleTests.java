package pageObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleTests {

	public static void main(String[] args) throws InterruptedException {
		
		SimpleTests st = new SimpleTests();
		//System.out.println(System.getProperty("user.dir"));
		WebDriver driver = null;
		
	    
	    switch ("firefox") {
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			break;
			
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			break;

		case "HtmlUnit":
			//driver = new HtmlUnitDriver(true);
			break;
			
		default:
			break;
		}
	    
	    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	    
	    st.displaySelectItems(driver);
	    //st.clickSubMenu(driver);
	    //st.keys(driver);
	    //st.getScreenshot(driver);
	    //st.displaySelect(driver);
	    //st.scrollDownTillElementView(driver);
	    //st.tooltip(driver);
	    //st.modalBox(driver);
	    //st.alertExamples(driver);
	    //st.listDemo(driver);
	    //st.explicitOrWebdriverWait(driver);
	    //st.setAttribute(driver);
	    //st.checkUnderline(driver);
	    //st.clikOnMultipleElement(driver);
	   // st.scrollDownUP(driver);
	    Thread.sleep(3000);
		//driver.quit();
	}
	
	public void clickSubMenu(WebDriver driver) throws InterruptedException {
		driver.get("https://jqueryui.com/menu/");
	    System.out.println(driver.getTitle());
	    
	    String mainPageHandle = driver.getWindowHandle();
	    
	    driver.switchTo().frame(0);
	    
		WebElement menuElectronics, carHifi, item3;
		menuElectronics = driver.findElement(By.xpath("//*[@id='ui-id-4']"));
		
		//Collecting the actions
		Actions actions = new Actions(driver);
		actions.moveToElement(menuElectronics);
		
		//Building the actions
		Action action = actions.build();
		
		//Performing the built action
		action.perform();
		
		Thread.sleep(3000);
		carHifi = driver.findElement(By.xpath("//*[@id='ui-id-6']"));
		actions.moveToElement(carHifi).build().perform();
		
		Thread.sleep(3000);
		carHifi.click();
		
		
	}
	
	public void displaySelectItems(WebDriver driver) throws InterruptedException {
		driver.get("https://jqueryui.com/");
	    System.out.println(driver.getTitle());
	    
		List<WebElement> menuElectronics= driver.findElements(By.xpath("//*[@id='sidebar']/aside[2]/ul/li"));
		
		Thread.sleep(2000);
		for (WebElement webElement : menuElectronics) {
			System.out.println(webElement.getText());
		}
		
		
		
		
	}
	
	public void keys(WebDriver driver) throws InterruptedException {

		driver.get("https://www.google.co.in/");
		
		WebElement ele = driver.findElement(By.xpath("//*[@id='APjFqb']"));

		String str = Keys.chord(Keys.SHIFT,Keys.TAB);
		ele.sendKeys(str);
	}
	
	public void htmlDriver(WebDriver driver) throws InterruptedException {
		//HtmlUnitDriver driver = new HtmlUnitDriver(true);

		//driver.setJavascriptEnabled(false);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("https://www.google.co.in/");

		System.out.println(driver.getTitle());
	}
	
	public void getScreenshot(WebDriver driver) throws InterruptedException {
		driver.get("https://www.srsbooking.com/index.html");
		//EventFiringWebDriver efw = new EventFiringWebDriver(driver);
		
		//File srcFile = efw.getScreenshotAs(OutputType.FILE);
		TakesScreenshot tsc = (TakesScreenshot) driver;
		
		File srcFile = tsc.getScreenshotAs(OutputType.FILE);

		File DestFile=new File("C:\\D\\DriveD\\Learning\\Selenium related\\scr1.JPG");
		try {
			FileUtils.copyFile(srcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void displaySelect(WebDriver driver) throws InterruptedException {
		driver.get("https://www.srsbooking.com/index.html");
		WebElement ele = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div/a/img"));
		ele.click();
		Select s =  new Select(ele);

		for (WebElement iterable_element : s.getOptions()) {
			System.out.println(iterable_element.getText());
		}
	}
	
	public void scrollDownTillElementView(WebDriver driver) throws InterruptedException {
		
		driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");
		WebElement scroll = driver.findElement(By.xpath("//*[@id='__next']/section[2]/div/div/h1"));

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		Thread.sleep(3000);
		jse.executeScript("arguments[0].scrollIntoView()",scroll );
		
		
	}
	public void tooltip(WebDriver driver) throws InterruptedException {
		
		
		driver.get("https://demo.guru99.com/test/social-icon.html");
		WebElement toolTip = driver.findElement(By.xpath("//*[@id='page']/div[2]/div/a[3]"));

		System.out.println(toolTip.getAttribute("title"));
		
	}
	
	public void modalBox(WebDriver driver) throws InterruptedException {
		
		driver.get("https://www.lambdatest.com/selenium-playground/bootstrap-modal-demo");
		WebElement alertOk = driver.findElement(By.xpath("//*[@id='__next']/section[3]/div/div/div/div[1]/button"));
		alertOk.click();
		
		WebElement save = driver.findElement(By.xpath("//*[@id='myModal']/div/div/div[3]/button[2]"));
		
		System.out.println(save.getText());
		save.click();

		
	}
	public void alertExamples(WebDriver driver) throws InterruptedException {
		driver.get("https://demo.automationtesting.in/Alerts.html");
		
		/*
		WebElement alertOk = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[1]/a"));
		alertOk.click();
		
		WebElement aOk = driver.findElement(By.xpath("//*[@id='OKTab']/button"));
		aOk.click();
		*/
		/*
		WebElement alertOkCancel = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[2]/a"));
		alertOkCancel.click();
		
		WebElement aOkCancel = driver.findElement(By.xpath("//*[@id='CancelTab']/button"));
		aOkCancel.click();
		*/
		WebElement alertTextbox = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div[1]/ul/li[3]/a"));
		alertTextbox.click();
		
		WebElement aTextbox = driver.findElement(By.xpath("//*[@id='Textbox']/button"));
		aTextbox.click();
		
		Alert a =  driver.switchTo().alert();
		
		System.out.println(a.getText());
		Thread.sleep(2000);
		a.sendKeys("Abdullah");
		a.accept();
		//a.dismiss();
		System.out.println(driver.findElement(By.xpath("//*[@id='demo1']")).getText());
	
	}

	public void listDemo(WebDriver driver) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		
		/*
		driver.get("https://jqueryui.com/selectmenu/");
		driver.switchTo().frame(0);
		WebElement dropDownList = driver.findElement(By.xpath("//select[@name='speed']"));
		dropDownList.click();
		Select select = new Select(dropDownList);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofMillis(10000));
		w.until(ExpectedConditions.elementSelectionStateToBe(dropDownList, true));
		*/
		
		driver.get("https://chercher.tech/practice/practice-dropdowns-selenium-webdriver");
		WebElement dropDownList = driver.findElement(By.xpath("//*[@id='first' and @class='col-lg-3']"));
		Select select = new Select(dropDownList);
		
		System.out.println("TagName "+dropDownList.getTagName());
		select.selectByIndex(1);
		
		System.out.println("selected Option");
		
		//Thread.sleep(2000);
		//select.deselectByIndex(1);
		
		Thread.sleep(2000);
		select.selectByIndex(3);
		
		System.out.println("Available Lists");
		
		for (WebElement iterable_element : select.getOptions()) {
			System.out.println(iterable_element.getText());
		}
		
		System.out.println("Seleccted Lists");
		//select.selectByValue("search-alias=stripbooks");
		for (WebElement iterable_element : select.getAllSelectedOptions()) {
			System.out.println(iterable_element.getText());
		}
		//select.selectByVisibleText("Slow");
		
	}
	public void explicitOrWebdriverWait(WebDriver driver) {
		driver.get("https://www.amazon.in/");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		WebElement ele = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='glow-ingress-block']/span[1]")));
		
		System.out.println("ele.getText() : "+ele.getText());
		
	}
	
	@SuppressWarnings("unchecked")
	public void fluentWait(WebDriver driver) {
		driver.get("https://www.amazon.in/");
		
		Wait w = new FluentWait(driver).withTimeout(Duration.ofMillis(10000)).pollingEvery(Duration.ofMillis(2000)).ignoring(NoSuchElementException.class);
		
		
	}
	
	public void implicitWait(WebDriver driver) {
		driver.get("https://www.amazon.in/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(10000));
		
		driver.manage().timeouts().setScriptTimeout(100, TimeUnit.SECONDS);
		
	}


	public void setAttribute(WebDriver driver) {
		driver.navigate().to("https://www.google.co.in/");
		
		WebElement textArea = driver.findElement(By.xpath("//textarea[@id='APjFqb']"));
		
		textArea.sendKeys("Selenium");
		
	}
	
	public void clikOnMultipleElement(WebDriver driver) {
		driver.get("https://jqueryui.com/selectable/");
	    System.out.println(driver.getTitle());
	    
	    String mainPageHandle = driver.getWindowHandle();
	    
	    driver.switchTo().frame(0);
	    
	    
	    
		WebElement item1, item2, item3;
		item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
		item2 = driver.findElement(By.xpath("//ol[@id='selectable']/li[2]"));
		item3 = driver.findElement(By.xpath("//ol[@id='selectable']/li[3]"));
		
		//Collecting the actions
		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).click(item1).click(item2).click(item3).keyUp(Keys.CONTROL);
		
		//Building the actions
		Action action = actions.build();
		
		//Performing the built action
		action.perform();
		
	}
	
	private void scrollDownUP(WebDriver driver) throws InterruptedException {

	    WebElement jquery = driver.findElement(By.xpath("//h2[@class='logo']/a"));
	    System.out.println("MainPage Text : "+jquery.getText());
		System.out.println("REfreshing the page");
		driver.navigate().refresh();
		Thread.sleep(5000);
		
		driver.switchTo().defaultContent();
		System.out.println("Calling again MainPage Text : "+jquery.getText());
		System.out.println(driver.getTitle());
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		
		Thread.sleep(5000);
		jse.executeScript("window.scrollBy(0,1500)", "");
		
		//Thread.sleep(5000);
		//jse.executeScript("window.scrollBy(500,0)", "");
		//Thread.sleep(5000);
	}

	private void checkUnderline(WebDriver driver) {
		driver.get("https://www.google.co.in/?gfe_rd=ctrl&ei=bXAwU8jYN4W6iAf8zIDgDA&gws_rd=cr");
		
		WebElement kannadaEle = driver.findElement(By.xpath("//*[@id='SIvCob']/a[7]"));
		
		System.out.println("getText :"+kannadaEle.getText());
		//System.out.println("getAccessibleName :"+kannadaEle.getAccessibleName());
		//System.out.println("getAriaRole :"+kannadaEle.getAriaRole());
		System.out.println("getTagName :"+kannadaEle.getTagName());
		System.out.println("getAttribute(\"href\") :"+kannadaEle.getAttribute("href"));
		
		System.out.println("getLocation() :"+kannadaEle.getLocation());
		System.out.println("getRect() :"+kannadaEle.getRect());
		//System.out.println("getShadowRoot() :"+kannadaEle.getShadowRoot());
		System.out.println("getSize() :"+kannadaEle.getSize());
		System.out.println("isDisplayed() :"+kannadaEle.isDisplayed());
		System.out.println("isEnabled() :"+kannadaEle.isEnabled());
		System.out.println("isSelected() :"+kannadaEle.isSelected());
		System.out.println("CssValues => "+"[color:"+kannadaEle.getCssValue("color")+"]"+"[text-decoration:"+kannadaEle.getCssValue("text-decoration")+
				"]"+"[font-size:"+kannadaEle.getCssValue("font-size")+"]"+"[text-align:"+kannadaEle.getCssValue("text-align")+"]"+
				"[font-family:"+kannadaEle.getCssValue("font-family")+"]");
		
		Actions actions = new Actions(driver);
		actions.moveToElement(kannadaEle);
		
		Action action = actions.build();
		
		action.perform();
		
		System.out.println("CssValues => "+"[color:"+kannadaEle.getCssValue("color")+"]"+"[text-decoration:"+kannadaEle.getCssValue("text-decoration")+
				"]"+"[font-size:"+kannadaEle.getCssValue("font-size")+"]"+"[text-align:"+kannadaEle.getCssValue("text-align")+"]"+
				"[font-family:"+kannadaEle.getCssValue("font-family")+"]");
	}

	public void readExcel() {
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//InputXml.csv");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
}
