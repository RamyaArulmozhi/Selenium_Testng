package sprint2.campaign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sprint2.ProjectSpecificMethod;

public class CampaignTest extends ProjectSpecificMethod {
	
	public static final String CAMPAIGN_OPPNAME="Opportunity from Ramya A";
	
	public void clickCampaign() throws InterruptedException
	{
		//Click on  Campaigns tab
		
		WebElement campaign = driver.findElement(By.xpath("//span[text()='Campaigns']/parent::a"));
		
		js.executeScript("arguments[0].click();",campaign);
		
		Thread.sleep(3000);
		
		//Click on Bootcamp
		
		driver.findElement(By.xpath("//a[@title='BootCamp'][1]")).click();
		
		Thread.sleep(5000);
	}

}
