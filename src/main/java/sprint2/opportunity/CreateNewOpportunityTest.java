package sprint2.opportunity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateNewOpportunityTest extends OpportunityTest {
	
	@Test(dataProvider = "loginCredentials")
	public void createOpportunity(String url, String uname, String pwd) throws InterruptedException
	{
		clickOpportunity(driver,js);
		
		Thread.sleep(6000);
		//Click on New button
		
		driver.findElement(By.xpath("//a[@title='New']")).click();
		
		Thread.sleep(4000);
		
		//Enter the opportunity Name
		
		WebElement oppElement=driver.findElement(By.xpath("//input[@name='Name']"));
		
		oppElement.sendKeys(OPPORTUNITY_NAME);
		
		//Get the Opportunity Name Text and store it
		
		String oppText = oppElement.getAttribute("value");
		
		System.out.println("The expected text is " + oppText);
		
		Thread.sleep(4000);
		
		//Select Today's date as close Date
		
		SimpleDateFormat format= new SimpleDateFormat("dd/MM/yyyy");
				
		String todayDate = format.format(Calendar.getInstance().getTime());
				
		driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys(todayDate);	
		
		Thread.sleep(4000);
		
		//Select Stage as "Need Analysis"
		
		WebElement element = driver.findElement(By.xpath("//label[text()='Stage']/following-sibling::div//button"));
				
		element.click();
				
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
		
		Thread.sleep(4000);
		
		//Save the Opportunity created
		
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		//Verify the printed Toast Message
		
		//New Opportunity should be created with name as  'Salesforce Automation by Your Name' 
				
		WebElement toastmsg = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
				
		String toastText = toastmsg.getText();
				
		System.out.println("The created message is as follows " + toastText);
		
		Thread.sleep(4000);
		
		//verifying the Opportunity name entered matches with the Opportunity Name created
		
				int beginIndex= toastText.indexOf('"') +1;
			
				int endIndex= toastText.indexOf('"', beginIndex);
			
				String subStringOppName=toastText.substring(beginIndex, endIndex);
			
				System.out.println(subStringOppName);
			
				Thread.sleep(4000);
			
				Assert.assertEquals(subStringOppName, oppText);
				
				System.out.println("Verified Opportunity Name, Opportunity Name matches with the Toast created");
				
				Thread.sleep(4000);
				
				//Expected Result:New Opportunity should be created with name as  'Salesforce Automation by Your Name'
				
				System.out.println("New Opportunity is created as "+toastText+ " "+"as expected");
			
				Thread.sleep(6000);
				
				
				
	}

}
