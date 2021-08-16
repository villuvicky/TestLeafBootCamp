package testNg;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteLeadCampaign extends baseClass {



	@BeforeClass
	public void setSheetName() {
		
		sheetName="DeleteLeadCampaign";
	}

	@Test(dataProvider = "fecthData")
	public void deleteLeadCampaign(String leadName, String campaignName) throws InterruptedException {

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement leads = driver.findElement(By.xpath("//p[text()='Leads']"));
		driver.executeScript("arguments[0].click();", leads);

		WebElement searchLead=driver.findElement(By.xpath("//input[@name='Lead-search-input']"));

		waitClickMethod(driver, searchLead);

		searchLead.sendKeys(leadName); 
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		WebElement dropdown2= driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr//td[10])[1]", leadName)));
		dropdown2.click();

		WebElement deleteOppurtunity=driver.findElement(By.xpath("//a[@title='Delete']"));
		deleteOppurtunity.click();

		WebElement confirmDelete=driver.findElement(By.xpath("//button[@title='Delete']"));
		confirmDelete.click();


		WebElement campaigns = driver.findElement(By.xpath("//a[@title='Campaigns']"));
		driver.executeScript("arguments[0].click();", campaigns);

		WebElement searchcampain = driver.findElement(By.xpath("//input[@name='Campaign-search-input']"));
		searchcampain.sendKeys(campaignName);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		driver.findElement(By.xpath(String.format("//tr[1]//a[@title='%s']",campaignName))).click();

		WebElement campaignMembers = driver.findElement(By.xpath("//span[@title='Campaign Members']"));
		waitClickMethod(driver, campaignMembers);

		int leadSize = driver.findElements(By.xpath(String.format("//span[@title='Name']/following::a[@title='%s']",leadName))).size();

		if (leadSize == 0)
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}
}
