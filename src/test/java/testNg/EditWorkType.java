package testNg;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditWorkType extends baseClass {

	@BeforeClass
	public void setSheetName() {
		
		sheetName="EditWorkType";
	}

	@Test(dataProvider = "fecthData")
	public void EditWorkTypeTest(String startTime, String endTime) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement workTypes = driver.findElement(By.xpath("//a[@data-label='Work Types']"));
		driver.executeScript("arguments[0].click();", workTypes);
		
		String workTypeName = driver.findElement(By.xpath("//tbody/tr[1]/th[1]/span/a")).getText();
		
		System.out.println(workTypeName);
		
		String expectedMessage=String.format("Work Type \"%s\" was saved.",workTypeName);
		
		WebElement dropDown = driver.findElement(By.xpath("//tbody/tr[1]/td[5]/span[1]"));
		dropDown.click();
		
		WebElement editButton = driver.findElement(By.xpath("//a[@title='Edit']"));
		editButton.click();
		
		WebElement startTimeBox = driver.findElement(By.xpath("(//span[text()='Timeframe Start']//following::input)[1]"));
		startTimeBox.clear();
		startTimeBox.sendKeys(startTime);
		
		WebElement endTimeBox = driver.findElement(By.xpath("(//span[text()='Timeframe End']//following::input)[1]"));
		endTimeBox.clear();
		endTimeBox.sendKeys(endTime);
		
		WebElement saveButton = driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		saveButton.click();
		
		WebElement messageElement=driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));
		
		waitVisibleMethod(driver, messageElement);
		
		String actualMessage = messageElement.getText();
		
		Assert.assertEquals(actualMessage, expectedMessage);
		
		
	}

}
