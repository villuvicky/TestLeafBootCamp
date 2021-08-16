package testNg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditOpportunity extends baseClass{
	
	/*
	 * static String opportunityName="Salesforce Automation by Vignesh Kannan here";
	 * static String stage="Perception Analysis"; static String
	 * deliveryStatus="In progress";
	 */
	@BeforeClass
	public void setSheetName() {
		
		sheetName="EditOpportunity";
	}
	
	@Test(dataProvider = "fecthData", dependsOnMethods = { "testNg.CreateNewOpportunity.CreateNewOpportunityTest"})
	public  void EditOpportunityTest(String opportunityName,String stageType, String deliveryStatus, String descriptionValue) throws InterruptedException  {
		
		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();
		
		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();
		
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement element = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		WebElement searchOppurtunity=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		searchOppurtunity.sendKeys(opportunityName);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);
		
		WebElement dropdown= driver.findElement(By.xpath(String.format("//a[@title='%s']//ancestor::tr//td[8]", opportunityName)));
		
		System.out.println(String.format("//a[@title='%s']//ancestor::tr//td[8]", opportunityName));

		waitClickMethod(driver, dropdown);
		
		WebElement editOppurtunity=driver.findElement(By.xpath("//a[@title='Edit']"));
		editOppurtunity.click();
		
		WebElement selectDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		selectDate.clear();

		Date date = new Date();
		String dateToStr = DateFormat.getInstance().format(date); 
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y"); //hh is to print 12 hours format
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);  // number of days to add
		dateToStr = sdf.format(c.getTime());

		selectDate.sendKeys(dateToStr);

		WebElement stageDropdown=driver.findElement(By.xpath("//label[text()='Stage']/following::input[1]//parent::div"));
		driver.executeScript("arguments[0].click();", stageDropdown);
		
		WebElement selectStage = driver.findElement(By.xpath(String.format("(//span[@title='%s'])",stageType)));
		selectStage.click();
		
		WebElement statusBox=driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[7]//parent::div"));
		driver.executeScript("arguments[0].click();", statusBox);

		WebElement deliverStatus=driver.findElement(By.xpath(String.format("(//span[@title='%s'])",deliveryStatus)));
		deliverStatus.click();

		WebElement description=driver.findElement(By.xpath("//label[text()='Description']//parent::lightning-textarea/div/textarea"));
		description.clear();
		description.sendKeys(descriptionValue);
		
		WebElement saveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();

		WebElement actualStage=driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr/td[5])[1]/span/span[text()='%s']", opportunityName,stageType)));
		
		waitVisibleMethod(driver, actualStage);
		
		Assert.assertEquals(actualStage.getText(), stageType);

	}

}
