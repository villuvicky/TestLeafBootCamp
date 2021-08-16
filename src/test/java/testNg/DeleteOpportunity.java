package testNg;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteOpportunity extends baseClass{

	/*
	 * static String OpportunityName="Salesforce Automation by Vignesh Kannan";
	 * static String
	 * deleteMessage="Opportunity \"Salesforce Automation by Vignesh Kannan\" was deleted. Undo"
	 * ;
	 */

	
	@BeforeClass
	public void setSheetName() {
		
		sheetName="DeleteOpportunity";
	}

	@Test(dataProvider = "fecthData", dependsOnMethods = {"testNg.EditOpportunity.EditOpportunityTest"})
	public void DeleteOpportunityTest(String opportunityName,String deleteMessage ) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement element = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", element);

		WebElement searchOppurtunity=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		searchOppurtunity.sendKeys(opportunityName);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		WebElement dropdown2= driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr//td[8])[1]", opportunityName)));
		dropdown2.click();

		WebElement deleteOppurtunity=driver.findElement(By.xpath("//a[@title='Delete']"));
		deleteOppurtunity.click();

		WebElement confirmDelete=driver.findElement(By.xpath("//button[@title='Delete']"));
		confirmDelete.click();

		WebElement messageElement =driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));

		waitVisibleMethod(driver, messageElement);

		String actualDeleteMessage= messageElement.getText();

		//toastMessage slds-text-heading--small forceActionsText

		System.out.println(actualDeleteMessage);

		Assert.assertEquals(actualDeleteMessage, deleteMessage);
		
	}

}
