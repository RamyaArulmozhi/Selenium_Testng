package sprint2.campaign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateLeadCampaignTest extends CampaignTest {

	@Test(dataProvider = "loginCredentials")
	public void createLeadCampaign(String url, String uname, String pwd) throws InterruptedException {
		clickCampaign();

		// Click on Add Leads

		WebElement btnAddLead = driver.findElement(By.xpath("//div[text()='Add Leads']/parent::a"));

		js.executeScript("arguments[0].scrollIntoView(true);", btnAddLead);

		Thread.sleep(4000);

		btnAddLead.click();

		Thread.sleep(2000);

		// New Lead

		driver.findElement(By.xpath("//input[@placeholder='Search Leads...']/parent::div")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[@title='New Lead']/parent::div")).click();

		Thread.sleep(3000);

		WebElement salutationField = driver.findElement(By.xpath(
				"//div[@class='salutation compoundTLRadius compoundTRRadius compoundBorderBottom form-element__row uiMenu']//a"));

		salutationField.click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[text()='Mr.']")).click();

		Thread.sleep(4000);

		// First Name

		String fn = "VIRAT";

		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(fn);

		Thread.sleep(4000);

		// Last Name

		String ln = "KOHLI";

		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(ln);

		Thread.sleep(4000);

		// Company

		driver.findElement(By.xpath("//span[text()='Company']/parent::label/following-sibling::input"))
				.sendKeys("TestLeaf");

		Thread.sleep(4000);

		// Click Save

		driver.findElement(By.xpath(
				"//button[@data-aura-class='uiButton--default uiButton--brand uiButton forceActionButton']/span"))
				.click();

		Thread.sleep(4000);

		// Click Next

		driver.findElement(By.xpath("//button[text()='Next']")).click();

		Thread.sleep(3000);

		// Click Submit

		driver.findElement(By.xpath("//button[text()='Submit']")).click();

		Thread.sleep(5000);

		// Verify Created lead under Campaign

		driver.findElement(By.xpath("//span[text()='View All']/parent::a")).click();

		Thread.sleep(3000);

		List<WebElement> leadRow = driver.findElements(By.xpath("//table[@aria-label='Campaign Members']/tbody/tr"));

		boolean message = false;

		for (WebElement ele : leadRow)

		{
			WebElement colName = ele.findElement(By.xpath("td[4]//a"));

			String name = colName.getText();

			System.out.println(name);

			if (name.equals(fn + " " + ln)) {
				message = true;
				System.out.println("Lead Created under campaign is displayed");
			}

		}

		Assert.assertTrue(message, "The Lead is not displayed under campaign");

		// Navigate to Leads Tab

		WebElement leads = driver.findElement(By.xpath("//a[@title='Leads']"));

		js.executeScript("arguments[0].click();", leads);

		Thread.sleep(3000);

		// Verify createdLead under Leads

		List<WebElement> rowSearch = driver.findElements(By.xpath(
				"//table[@class='slds-table slds-table_header-fixed slds-table_bordered slds-table_edit slds-table_resizable-cols']/tbody/tr/th//a"));

		boolean leadMessage = false;

		for (WebElement row : rowSearch)

		{
			String leadTableName = row.getText();

			if (leadTableName.equals(fn + " " + ln)) {
				leadMessage = true;
				System.out.println("Lead is created in Leads Tab");
			}

		}

		Assert.assertTrue(leadMessage, "Lead s not Displayed in Leads Tab");

		Thread.sleep(6000);

		System.out.println("Lead is created in Leads tab and associated to CampaignStep");

	}

}
