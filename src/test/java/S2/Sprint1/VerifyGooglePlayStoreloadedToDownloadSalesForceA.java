package S2.Sprint1;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class VerifyGooglePlayStoreloadedToDownloadSalesForceA {


	static ChromeDriver driver;
	static String expectedText="SalesforceA";
	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("makaia@testleaf.com");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SelBootcamp$123");

		driver.findElement(By.id("Login")).submit();

		WebElement next=driver.findElement(By.xpath("//div[@class='rightScroll']//button"));
		waitClickMethod(driver, next);
		waitClickMethod(driver, next);

		WebElement salesForceGooglePlayStore=driver.findElement(By.
				xpath("//span[text()='Download SalesforceA']//following::button[@title='Google Play']"
						)); 
		driver.executeScript("arguments[0].click();", salesForceGooglePlayStore);


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
		
		driver.quit();
	}

}
