package testNg;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class CreateserviceTerritories extends baseClass{


	@BeforeClass
	public void setSheetName() {
		
		sheetName="CreateserviceTerritories";
	}

	@Test(dataProvider = "fecthData")
	public void CreateserviceTerritoriesTest(String newTerritoryName,String city,String address, String country, String postalCode, String expectedMessage) throws InterruptedException {
		// TODO Auto-generated method stub

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

		WebElement newTerritoryNameBox= driver.findElement(By.xpath("//span[text()='Name']//parent::label//following::input[1]"));
		newTerritoryNameBox.sendKeys(newTerritoryName);

		WebElement operatingHours= driver.findElement(By.xpath("(//span[text()='Operating Hours']//following::input[1])[2]"));
		operatingHours.click();

		WebElement firstOperatingHour= driver.findElement(By.xpath("//span[text()='Operating Hours']//following::ul[@class='lookup__list  visible']/li[1]"));
		firstOperatingHour.click();

		WebElement checkBox=driver.findElement(By.xpath("(//span[text()='Active'])[2]//following::input[@type='checkbox']"));

		checkBox.click();

		WebElement cityBox= driver.findElement(By.xpath("//span[text()='City']//following::input[@placeholder='City']"));
		cityBox.sendKeys(city);

		WebElement addressBox= driver.findElement(By.xpath("//span[text()='Address']//following::textarea[1]"));
		addressBox.sendKeys(address);

		WebElement countryBox=driver.findElement(By.xpath("//span[text()='Country']//following::input[@placeholder='Country']"));
		countryBox.sendKeys(country);

		WebElement postalCodeBox= driver.findElement(By.xpath("//span[text()='Zip/Postal Code']//following::input[@placeholder='Zip/Postal Code']"));
		postalCodeBox.sendKeys(postalCode);


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
