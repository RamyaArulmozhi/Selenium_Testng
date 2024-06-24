package sprint2.opportunity;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import sprint2.ProjectSpecificMethod;

public class OpportunityTest extends ProjectSpecificMethod {
	
	public static final String OPPORTUNITY_NAME ="Salesforce Automation by Ramya A";
	
	public void clickOpportunity(WebDriver driver,JavascriptExecutor js) throws InterruptedException
	{
		WebElement opportunity = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		
		js.executeScript("arguments[0].click();",opportunity);
		
		Thread.sleep(3000);
	}

}
