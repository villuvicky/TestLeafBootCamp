package dataFromExcel;

import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class VerifyGooglePlayStoreloadedToDownloadSalesForceA extends baseClass{


	static String expectedText="SalesforceA";
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
	public void VerifyGooglePlayStoreloadedToDownloadSalesForceATest(String UserName, String Password) {
		
		// TODO Auto-generated method stub
		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		WebElement next=driver.findElement(By.xpath("//div[@class='rightScroll']//button"));
		waitClickMethod(driver, next);
		waitClickMethod(driver, next);

		WebElement salesForceGooglePlayStore=driver.findElement(By.
				xpath("//span[text()='Download SalesforceA']//following::button[@title='Google Play']"
						)); 
		driver.executeScript("arguments[0].click();", salesForceGooglePlayStore);

		String CurrentWindow=driver.getWindowHandle();
		
		Set<String> allWIndows=driver.getWindowHandles();

		for (String string : allWIndows) {

			driver.switchTo().window(string);	
		}


		WebElement confirm=driver.findElement(By.xpath("//button[@onClick='goAhead()']"));
		confirm.click();

		String actualText=driver.findElement(By.xpath("//h1[@itemprop='name']//span")).getText();

		Assert.assertEquals(actualText, expectedText);
		
		WebElement install= driver.findElement(By.xpath("//button[@aria-label='Install' and text()='Install']"));
		
		if(install.isDisplayed())
			Assert.assertTrue(true);
		
		else
			Assert.assertFalse(false);
		driver.switchTo().window(CurrentWindow);
	}

}
