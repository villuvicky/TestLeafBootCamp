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


public class CreateserviceTerritories extends baseClass{

	static String expectedMessage="Service Territory \"Vignesh\" was created.";
	static WebDriverWait wait;
	
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
	public void CreateserviceTerritoriesTest(String UserName, String Password) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		
		WebElement Toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		Toggle.click();

		WebElement ViewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		ViewAll.click();

		WebElement contacts = driver.findElement(By.xpath("//a[@data-label='Service Territories']"));
		driver.executeScript("arguments[0].click();", contacts);

		Thread.sleep(2000);

		WebElement newTerritory = driver.findElement(By.xpath("//a[@title='New']"));
		newTerritory.click();

		WebElement newTerritoryName= driver.findElement(By.xpath("//span[text()='Name']//parent::label//following::input[1]"));
		newTerritoryName.sendKeys("Vignesh");

		WebElement operatingHours= driver.findElement(By.xpath("(//span[text()='Operating Hours']//following::input[1])[2]"));
		operatingHours.click();

		WebElement firstOperatingHour= driver.findElement(By.xpath("//span[text()='Operating Hours']//following::ul[@class='lookup__list  visible']/li[1]"));
		firstOperatingHour.click();

		WebElement checkBox=driver.findElement(By.xpath("(//span[text()='Active'])[2]//following::input[@type='checkbox']"));

		checkBox.click();

		WebElement city= driver.findElement(By.xpath("//span[text()='City']//following::input[@placeholder='City']"));
		city.sendKeys("Karur");

		WebElement address= driver.findElement(By.xpath("//span[text()='Address']//following::textarea[1]"));
		address.sendKeys("K A Nagae 4th Cross, Karur, tamilnadu");

		WebElement country=driver.findElement(By.xpath("//span[text()='Country']//following::input[@placeholder='Country']"));
		country.sendKeys("India");

		WebElement postalCode= driver.findElement(By.xpath("//span[text()='Zip/Postal Code']//following::input[@placeholder='Zip/Postal Code']"));
		postalCode.sendKeys("639002");


		WebElement saveButton= driver.findElement(By.xpath("//button[@title='Save']"));
		saveButton.click();

		//Thread.sleep(3000);
		
		WebElement messageElement= driver
				.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));
		
		waitVisibleMethod(driver, messageElement);

		String ActualSuccessMessage = messageElement.getText();

		System.out.println("Message is " + ActualSuccessMessage);
		
		Assert.assertEquals(ActualSuccessMessage, expectedMessage);


	}

}
