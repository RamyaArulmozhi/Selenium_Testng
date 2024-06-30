package sprint2.opportunity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OpportunitiesSortOrderByCloseDateTest extends OpportunityTest {

	@Test(dataProvider = "loginCredentials")
	public void sortOrderByCloseDate(String url, String uname, String pwd) throws InterruptedException, ParseException {
		clickOpportunity(driver, js);

		// Select the table view

		driver.findElement(By.xpath("//button[@title='Select list display']")).click();

		Thread.sleep(3000);

		driver.findElement(By.xpath("//a[@role='menuitemcheckbox']//span[text()='Table']")).click();

		Thread.sleep(5000);

		// Closed Date Sorting

		WebElement closeDate = driver.findElement(By.xpath("//span[text()='Close Date']/parent::a"));

		closeDate.click();

		Thread.sleep(3000);

		closeDate.click();

		Thread.sleep(3000);

		// Verify the Opportunities displayed in ascending order by Close date

		Date previousParseDate = null;

		List<WebElement> oppTable = driver.findElements(By.xpath("//table[@aria-label='Recently Viewed']/tbody/tr"));

		for (WebElement ele : oppTable) {
			List<WebElement> allCol = ele.findElements(By.xpath("td"));

			WebElement closeDateCol = allCol.get(allCol.size() - 3);

			WebElement closeDateValue = closeDateCol.findElement(By.xpath(".//span[@data-aura-class='uiOutputDate']"));

			String dateVal = closeDateValue.getText();

			System.out.println(dateVal);

			Thread.sleep(3000);

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date parseDate = sdf.parse(dateVal);

			// System.out.println(parseDate);

			Assert.assertTrue(previousParseDate == null || parseDate.after(previousParseDate)
					|| parseDate.equals(previousParseDate));

			previousParseDate = parseDate;

		}

		// Expected Result:Opportunities should be displayed in ascending order by Close
		// dateStep

		System.out.println("The Opportunities are displayed in ascending order by Close date");

	}

}
