package sprint2.opportunity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateOpportunityWithoutMandatoryFieldsTest extends OpportunityTest {

	@Test(dataProvider = "loginCredentials")
	public void createWithoutMandatoryFields(String url, String uname, String pwd) throws InterruptedException {
		clickOpportunity(driver, js);

		// Click on New Button

		driver.findElement(By.xpath("//a[@title='New']")).click();

		// Choose Close Date as Tomorrow's date

		WebElement closedDate = driver.findElement(By.xpath("//div//input[@name='CloseDate']"));

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		Calendar instance = Calendar.getInstance();

		instance.add(Calendar.DATE, 1);

		String tomDate = format.format(instance.getTime());

		closedDate.sendKeys(tomDate);

		Thread.sleep(2000);

		// Save

		driver.findElement(By.xpath("//lightning-button//button[@name='SaveEdit']")).click();

		Thread.sleep(2000);

		// Alert message

		WebElement text = driver.findElement(By.xpath("//h2[text()='We hit a snag.']/parent::div"));

		System.out.println(text.getText());

		// Review Alert message

		WebElement alert1 = driver.findElement(By.xpath("//div[@class='fieldLevelErrors']/div"));

		String reviewText = alert1.getText();

		System.out.println(reviewText);

		WebElement alert2 = driver.findElement(By.xpath("//div[@class='fieldLevelErrors']/ul"));

		String missedField = alert2.getText();

		System.out.println(missedField);

		// Expected Result:Complete this field message should be displayed for Name and
		// Stage fields

		Assert.assertTrue(alert2.isDisplayed());

		System.out.println("Complete this field message is displayed for Name and Stage");

		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Close this window']/parent::button")).click();

		Thread.sleep(2000);

	}

}
