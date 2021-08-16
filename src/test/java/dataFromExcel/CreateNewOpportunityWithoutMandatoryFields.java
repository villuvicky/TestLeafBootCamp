package dataFromExcel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateNewOpportunityWithoutMandatoryFields extends baseClass{

	static String OpportunityName="Salesforce Automation by Vignesh Kannan";
	
	
	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {
		
		ReadExcel data= new ReadExcel();
		
		return data.excelData("LoginData");
	}
	
	@Test(dataProvider = "fecthData")
	public  void CreateNewOpportunityWithoutMandatoryFieldsTest(String UserName, String Password) {
		
		List<String> expectedMessages= new ArrayList<String>();
		expectedMessages.add("We hit a snag.");
		expectedMessages.add("Opportunity Name");
		expectedMessages.add("Stage");
		
		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		
		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();
		
		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();
		
		WebElement Sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();
		
		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", opportunities);
		
		WebElement newOpportunities=driver.findElement(By.xpath("//a[@title='New']"));
		newOpportunities.click();
		
		WebElement dates=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		dates.click();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
				
		WebElement chooseDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		chooseDate.sendKeys(sdf.format(date));
		
		WebElement saveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();
		

		
		String opportunityText=driver.findElement(By.xpath("(//input[@name='Name']/following::div)[1]")).getText();
		
		
		String stageText=driver.findElement(By.xpath("(//label[text()='Stage']//following::div)[1]")).getText();
		
		System.out.println(opportunityText);
		
		System.out.println(stageText);
		
		String warningText=driver.findElement(By.xpath("//h2[@title='We hit a snag.']")).getText();
		
		List<WebElement> allMessagesPath=driver.findElements(By.xpath("//li[@force-recordediterror_recordediterror]/a"));
		
		List<String> actualMessages= new ArrayList<String>();
		actualMessages.add(warningText);
		
		for (WebElement MessagePath : allMessagesPath) {
			
			actualMessages.add(MessagePath.getText());
		}
		
		
		Assert.assertEquals(actualMessages, expectedMessages);
		
		WebElement cancel=driver.findElement(By.xpath("//button[@name='CancelEdit']"));
		cancel.click();
		
	}

}
