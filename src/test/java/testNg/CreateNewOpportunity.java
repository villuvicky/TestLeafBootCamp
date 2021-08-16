package testNg;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateNewOpportunity extends baseClass{

	/*
	 * static String opportunityName = "Salesforce Automation by Vignesh Kannan";
	 * static String successMessage =
	 * "Opportunity \"Salesforce Automation by Vignesh Kannan\" was created.";
	 */

	@BeforeClass
	public void setSheetName() {
		
		sheetName="CreateNewOpportunity";
	}

	@Test(dataProvider = "fecthData")
	public  void CreateNewOpportunityTest(String opportunityName, String stageType, String successMessage)  {
		// TODO Auto-generated method stub

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", opportunities);

		WebElement newOpportunities = driver.findElement(By.xpath("//a[@title='New']"));
		newOpportunities.click();

		WebElement newOpportunitiesName = driver.findElement(By.xpath("//input[@name='Name']"));
		newOpportunitiesName.sendKeys(opportunityName);

		System.out.println(newOpportunitiesName.getAttribute("value"));

		WebElement dates = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		dates.click();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");

		WebElement chooseDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		chooseDate.sendKeys(sdf.format(date));

		WebElement stageDropdown=driver.findElement(By.xpath("//label[text()='Stage']/following::input[1]//parent::div"));
		driver.executeScript("arguments[0].click();", stageDropdown);

		WebElement selectStage = driver.findElement(By.xpath(String.format("(//span[@title='%s'])",stageType)));
		selectStage.click();

		WebElement saveChanges = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();


		WebElement messageElement=driver
				.findElement(By.xpath("(//div[@data-aura-class='forceToastMessage']//div)[1]/div"));

		waitVisibleMethod(driver, messageElement);

		String actualSuccessMessage = messageElement.getText();

		// toastMessage slds-text-heading--small forceActionsText

		System.out.println("Message is " + actualSuccessMessage);

		Assert.assertEquals(actualSuccessMessage, successMessage);

	}
}
