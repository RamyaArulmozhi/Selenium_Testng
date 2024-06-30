package sprint2.campaign;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import sprint2.opportunity.OpportunityTest;

public class DeleteNewOpportunityForCampaignTest extends CampaignTest {

	@Test(dataProvider = "loginCredentials")
	public void deleteOpportunityCampaign(String url, String uname, String pwd) throws InterruptedException {
		clickCampaign();

		// Click on Opportunity Tab

		OpportunityTest ot = new OpportunityTest();

		ot.clickOpportunity(driver, js);

		WebElement searchOpp = driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));

		searchOpp.sendKeys(CAMPAIGN_OPPNAME);

		searchOpp.sendKeys(Keys.ENTER);

		Thread.sleep(3000);

		// Remove tooltip by clicking elsewhere

		searchOpp.sendKeys(Keys.chord(Keys.CONTROL, Keys.F6));

		Thread.sleep(4000);

		// Delete Icon

		List<WebElement> ddDel = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));

		for (WebElement ele : ddDel)

		{
			String text = ele.findElement(By.xpath("./th//a")).getText();

			if (CAMPAIGN_OPPNAME.equals(text)) {
				List<WebElement> col = ele.findElements(By.xpath("td"));

				WebElement delIcon = col.get(col.size() - 1);

				Thread.sleep(3000);

				WebElement eleDelClick = delIcon.findElement(By.xpath(".//a"));

				eleDelClick.click();

				Thread.sleep(4000);

				driver.findElement(
						By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Delete']")).click();

				Thread.sleep(3000);

				driver.findElement(By.xpath("//span[text()='Delete']/parent::button")).click();

				Thread.sleep(3000);

				break;

			}
		}

		// Verify the Toast Message

		WebElement toastmsg = driver
				.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));

		String toastText = toastmsg.getText();

		System.out.println("The Deleted message is as follows " + toastText);

		Thread.sleep(5000);

		// Verify Whether Oppurtunity is Deleted using Oppurtunity Name

		int beginIndex = toastText.indexOf('"') + 1;

		int endIndex = toastText.indexOf('"', beginIndex);

		String message = toastText.substring(beginIndex, endIndex);

		System.out.println(message);

		Thread.sleep(2000);

		Assert.assertEquals(message, CAMPAIGN_OPPNAME);

		// Expected Result: Oppurtunity should be Successfully deleted

		System.out.println("Opportunity is Deleted Successfully");

	}

}
