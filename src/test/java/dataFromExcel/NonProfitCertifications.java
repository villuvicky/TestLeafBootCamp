package dataFromExcel;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NonProfitCertifications extends baseClass{

	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {

		ReadExcel data= new ReadExcel();

		return data.excelData("LoginData");
	}

	@Test(dataProvider = "fecthData")

	public void NonProfitCertificationsTest(String UserName, String Password) throws InterruptedException {
		// TODO Auto-generated method stub

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		WebElement next=driver.findElement(By.xpath("//div[@class='rightScroll']//button"));

		waitClickMethod(driver, next);
		waitClickMethod(driver, next);
		waitClickMethod(driver, next);

		WebElement systemStatus=driver.findElement(By.
				xpath("//span[text()='See System Status']//following::button[@title='Get Started']"
						)); 
		driver.executeScript("arguments[0].click();", systemStatus);

		String CurrentWindow=driver.getWindowHandle();

		Set<String> allWIndows=driver.getWindowHandles();

		for (String string : allWIndows) {

			driver.switchTo().window(string);	
		}

		WebElement dropDown=driver.findElement(By.xpath("//div[@id='dropdown-list']"));
		dropDown.click();

		WebElement complianceOption=driver.findElement(By.xpath("//li[@id='Compliance']"));
		complianceOption.click();

		WebElement showFilter = driver.findElement(By.xpath("//button[normalize-space()='Show filters']"));
		showFilter.click();

		WebElement nonProfit= driver.findElement(By.xpath("//h2[text()='Industries']//following::label[@for='Nonprofit']"));
		nonProfit.click();

		List<WebElement> totalElements=driver.findElements(By.xpath("//div[@class='slds-col slds-size_1-of-1 slds-medium-size_1-of-3 slds-large-size_1-of-4']"));
		int totalCertificates=totalElements.size();
		System.out.println("The total non profit certificates are "+totalCertificates);

		WebElement clearFilter= driver.findElement(By.xpath("//button[normalize-space()='Clear filters']"));

		if(clearFilter.isDisplayed())
			Assert.assertTrue(true);

		else
			Assert.assertFalse(false);

		driver.switchTo().window(CurrentWindow);

	}

}


