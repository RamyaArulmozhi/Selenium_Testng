package sprint2.campaign;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sprint2.opportunity.OpportunityTest;

public class CreateNewOpportunityCampaignTest extends CampaignTest {
	
	@Test(dataProvider = "loginCredentials")
	public void createNewOpportunity(String url, String uname, String pwd) throws InterruptedException
	{
		clickCampaign();
		
		//Click on New Opportunity Button
		
		WebElement newOpportunity = driver.findElement(By.xpath("//div[@class ='windowViewMode-normal oneContent active lafPageHost']//li[@data-target-selection-name='sfdc:QuickAction.Global.NewOpportunity']//a"));
		
		newOpportunity.click();
		
		//Enter Opportunity Name
		
		WebElement eleOppName = driver.findElement(By.xpath("//span[text()='Opportunity Name']/parent::label/following-sibling::input"));

		eleOppName.sendKeys(CAMPAIGN_OPPNAME);
		
		//CloseDate

		WebElement closeDate = driver.findElement(By.xpath("//span[text()='Close Date']/parent::label/following-sibling::div/input"));

		closeDate.clear();

		Thread.sleep(2000);

		SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");

		Calendar instance = Calendar.getInstance();

		instance.add(Calendar.DATE,1);

		String tomDate=sdf.format(instance.getTime());

		closeDate.sendKeys(tomDate);

		Thread.sleep(2000);

		//Select Stage as Need Analysis

		WebElement eleDropdown = driver.findElement(By.xpath("//span[text()='Stage']/parent::span/following-sibling::div//a"));

		eleDropdown.click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@title='Needs Analysis']")).click();

		Thread.sleep(3000);

		//Click on Save

		driver.findElement(By.xpath("//button[@class='slds-button slds-button_brand cuf-publisherShareButton undefined uiButton']")).click();

		Thread.sleep(3000);
		
		//Verify New Opportunity in campaign

		WebElement toastMsg = driver.findElement(By.xpath("//div[@class='slds-theme--success slds-notify--toast slds-notify slds-notify--toast forceToastMessage']"));

		System.out.println(toastMsg.getText());
				
		Thread.sleep(5000);
		
		//Click on Opportunity Tab
		
		OpportunityTest ot=new OpportunityTest();
		
		ot.clickOpportunity(driver, js);
		
		
		// Verify newly created opportunity in Opportunity

		WebElement searchOpp = driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));

		searchOpp.sendKeys(CAMPAIGN_OPPNAME);
				
		searchOpp.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		
		//Remove tooltip by clicking elsewhere
		
		searchOpp.sendKeys(Keys.chord(Keys.CONTROL,Keys.F6));
				
		Thread.sleep(4000);
		
		
				
		List<WebElement> oppNameCheck = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));
				
		boolean isFound = false;
				
		for(WebElement ele:oppNameCheck)
				{
					WebElement oppNameFound = ele.findElement(By.xpath("./th//a"));
					
					String oppNameText=oppNameFound.getText();
					
					System.out.println(oppNameText);
					
					if(oppNameText.equals(CAMPAIGN_OPPNAME))
					{
						isFound = true;
						break;
					}
				}
				
		//Expected Result 2) New Opportunity should be displayed under Opportunities tabStep 
				
		Assert.assertTrue(isFound, "Newly Created opportunity "+CAMPAIGN_OPPNAME+" is not found");
				
		System.out.println("Opportunities is displayed under Opportunity tab");
				
				
				
				
		

	}

}
