package dataFromExcel;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class EditWorkType extends baseClass {

	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitVisibleMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}


	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {

		ReadExcel data= new ReadExcel();

		return data.excelData("LoginData");
	}

	@Test(dataProvider = "fecthData")
	public void EditWorkTypeTest(String UserName, String Password) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
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

		WebElement startTime = driver.findElement(By.xpath("(//span[text()='Timeframe Start']//following::input)[1]"));
		startTime.clear();
		startTime.sendKeys("9");

		WebElement endTime = driver.findElement(By.xpath("(//span[text()='Timeframe End']//following::input)[1]"));
		endTime.clear();
		endTime.sendKeys("18");

		WebElement saveButton = driver.findElement(By.xpath("(//span[text()='Save']//parent::button)[2]"));
		saveButton.click();

		WebElement messageElement=driver.findElement(By.xpath("//span[@data-aura-class='forceActionsText']"));

		waitVisibleMethod(driver, messageElement);

		String actualMessage = messageElement.getText();

		Assert.assertEquals(actualMessage, expectedMessage);


	}

}
