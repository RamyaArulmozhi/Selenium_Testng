package sprint2;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class ProjectSpecificMethod {

	public WebDriver driver;

	public ChromeOptions option;
	
	public JavascriptExecutor js;
	
	public WebDriverWait wait;

	@BeforeClass
	public void initialization() {
		// Disable the WebNotifications

		option = new ChromeOptions();

		option.addArguments("--disable-notifications");

		driver = new ChromeDriver(option);
		
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.manage().window().maximize();
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(30));

	}

	@Parameters({ "url", "username", "password" })
	@BeforeMethod
	public void preCondition(String url, String uname, String pwd) throws InterruptedException

	{

		login(url, uname, pwd);
		
		launchSalesApp();

	}

	public void login(String url, String uname, String pwd) throws InterruptedException {
		driver.get(url);

		driver.findElement(By.id("username")).sendKeys(uname);

		driver.findElement(By.id("password")).sendKeys(pwd);

		driver.findElement(By.id("Login")).click();
		
		Thread.sleep(3000);

	}

	public void launchSalesApp() throws InterruptedException {
		// Click on toggle menu button from the left corner

		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		
		Thread.sleep(3000);

		// Click view All and click Sales from App Launcher

		driver.findElement(By.xpath("//button[@aria-label='View All Applications']")).click();

		driver.findElement(By.xpath("//input[@placeholder='Search apps or items...']")).sendKeys("Sales");

		driver.findElement(By.xpath("//p[text()='Manage your sales process with accounts, leads, opportunities, and more']")).click();
		
		Thread.sleep(6000);

	}
	
	public void logout() throws InterruptedException
	{
		driver.findElement(By.xpath("//button[@class='slds-button branding-userProfile-button slds-button slds-global-actions__avatar slds-global-actions__item-action']")).click();
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
		
	}
	

	@AfterMethod
	public void postCondition() throws InterruptedException 
	{
           logout();
	}

	@AfterClass
	public void close() {
		driver.close();
	}

}
