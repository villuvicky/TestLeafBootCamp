package testNg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateNewOpportunityWithoutMandatoryFields extends baseClass{

	
	@BeforeClass(groups = { "Opportunity"})
	public void setSheetName() {
		
		sheetName="CreateNewOpportunityWithoutMand";
	}

	@Test(dataProvider = "fecthData")
	public  void CreateNewOpportunityWithoutMandatoryFieldsTest(String message1,String message2,String message3 ) {
		
		
		List<String> expectedMessages= new ArrayList<String>();
		expectedMessages.add(message1);
		expectedMessages.add(message2);
		expectedMessages.add(message3);
		
		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();
		
		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();
		
		WebElement Sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();
		
		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", opportunities);
		
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
		
		String stageText=driver.findElement(By.xpath("(//label[text()='Stage']/following::div)[6]")).getText();
		
		System.out.println(opportunityText);
		
		System.out.println(stageText);
		
		String warningText=driver.findElement(By.xpath("//h2[@title='We hit a snag.']")).getText();
		
		List<WebElement> allMessagesPath=driver.findElements(By.xpath("//li[@force-recordediterror_recordediterror]/a"));
		
		List<String> actualMessages= new ArrayList<String>();
		
		actualMessages.add(warningText);
		
		for (WebElement MessagePath : allMessagesPath) {
			
			actualMessages.add(MessagePath.getText());
		}
		
		System.out.println(actualMessages);
		
		Assert.assertEquals(actualMessages, expectedMessages);
		
		WebElement cancel=driver.findElement(By.xpath("//button[@name='CancelEdit']"));
		cancel.click();
		
	}

}
