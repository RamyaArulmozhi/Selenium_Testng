package sprint2.opportunity;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteOpportunityTest extends OpportunityTest {

	@Test(dataProvider = "loginCredentials")
	public void deleteOpp(String url, String uname, String pwd) throws InterruptedException {
		clickOpportunity(driver, js);

		// Searching the Opportunity Name
		WebElement searchOpp = driver.findElement(By.xpath("//input[@aria-label='Search Recently Viewed list view.']"));

		searchOpp.sendKeys(OPPORTUNITY_NAME);

		Thread.sleep(3000);

		searchOpp.sendKeys(Keys.ENTER);

		Thread.sleep(3000);
		searchOpp.sendKeys(Keys.ENTER);

		// Delete Icon

		List<WebElement> ddSearch = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));

		for (WebElement ele : ddSearch) {
			String text = ele.findElement(By.xpath("th//a")).getText();
			if (OPPORTUNITY_NAME.equals(text)) {
				List<WebElement> ddCols = ele.findElements(By.xpath("td"));
				System.out.println(ddCols.size());

				WebElement eleIcon = ddCols.get(ddCols.size() - 1);

				Thread.sleep(3000);

				// Remove tooltip by clicking elsewhere

				// searchOpp.sendKeys(Keys.chord(Keys.CONTROL,Keys.F6));

				// Thread.sleep(4000);

				// System.out.println("Tool Tip clicked");

				// Thread.sleep(3000);

				WebElement eleClick = eleIcon.findElement(By.xpath(".//a"));

				eleClick.click();

				Thread.sleep(5000);

				driver.findElement(
						By.xpath("//a[@data-target-selection-name='sfdc:StandardButton.Opportunity.Delete']")).click();

				Thread.sleep(5000);

				break;
			}
		}

		// DialogBox

		WebElement alertDel = driver.findElement(By.xpath("//span[text()='Delete']/parent::button"));

		alertDel.click();

		Thread.sleep(3000);

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

		Assert.assertEquals(message, OPPORTUNITY_NAME);

		// Expected Result: Oppurtunity should be Successfully deleted

		System.out.println("Opportunity is Deleted Successfully");

	}

}
