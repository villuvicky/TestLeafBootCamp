package testNg;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateContactForCampaign extends baseClass{
	
	@BeforeClass
	public void setSheetName() {
		
		sheetName="CreateContactForCampaign";
	}

	@Test(dataProvider = "fecthData")
	public void createContactForCampaign(String campaign,String salutationValue, String contactFirstName, String contactLastName) throws InterruptedException {

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement campaigns = driver.findElement(By.xpath("//p[text()='Campaigns']"));
		driver.executeScript("arguments[0].click();", campaigns);

		WebElement searchcampain = driver.findElement(By.xpath("//input[@name='Campaign-search-input']"));
		searchcampain.sendKeys("Bootcamp");
		Actions act= new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		WebElement campaignRow=driver.findElement(By.xpath(String.format("//tr[1]//a[@title='%s']",campaign)));
		campaignRow.click();

		WebElement newContact=driver.findElement(By.xpath("//a[@title='New Contact']"));
		newContact.click();

		WebElement salutationDropdown=driver.findElement(By.xpath("//span[text()='Salutation']//following::a[@class='select']"));
		salutationDropdown.click();

		WebElement salutation=driver.findElement(By.xpath(String.format("//span[text()='Salutation']//following::a[@title='%s']",salutationValue)));
		salutation.click();

		WebElement firstName=driver.findElement(By.xpath("//span[text()='First Name']//following::input[@placeholder='First Name']"));
		firstName.sendKeys(contactFirstName);

		WebElement lastName=driver.findElement(By.xpath("//span[text()='First Name']//following::input[@placeholder='Last Name']"));
		lastName.sendKeys(contactLastName);

		WebElement saveButon= driver.findElement(By.xpath("//h2[text()='New Contact']//following::span[text()='Save'][2]"));
		saveButon.click();

		act.moveToElement(driver.findElement(By.xpath("//span[@title='Campaign Members']"))).perform();

		//span[@title='Campaign Members']

		WebElement addContact=driver.findElement(By.xpath("//span[@title='Campaign Members']//following::div[text()='Add Contacts'][1]"));
		driver.executeScript("arguments[0].click();", addContact);
		//addContact.click();

		String fullName=contactFirstName+" "+contactLastName;
		
		WebElement searchContact=driver.findElement(By.xpath("//input[@title='Search Contacts']"));
		searchContact.sendKeys(fullName);
		act.sendKeys(Keys.ENTER).perform();
		/*
		 * WebElement search=driver.findElement(By.
		 * xpath("//input[@title='Search Contacts']//following::*[name()='svg' and @data-key='search'][2]"
		 * )); search.click();
		 */

		Thread.sleep(2000);

		WebElement add=driver.findElement(By.xpath(String.format("//tr[1]//th//span//a[@title='%s']//preceding::td[1]/span/span/label[@class='slds-checkbox']",fullName)));
		add.click();

		WebElement nextButton= driver.findElement(By.xpath("//span[text()='Next']"));
		nextButton.click();

		WebElement status= driver.findElement(By.xpath("//span[text()='Member Status']//following::a[text()='Sent']"));
		waitVisibleMethod(driver, status);

		WebElement submit= driver.findElement(By.xpath("//span[text()='Submit']"));
		submit.click();

		WebElement viewAllContacts= driver.findElement(By.xpath("(//div[@class='runtime_sales_campaignMemberRelatedList']//div//a//span[@class='view-all-label'])[1]"));
		waitClickMethod(driver, viewAllContacts);
		
		int size= driver.findElements(By.xpath(String.format("//a[@title='%s']//following::tr[1]/td[8]/span[1]",fullName))).size();
		
		if (size>=1) {
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
	}

}
