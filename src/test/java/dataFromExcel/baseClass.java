package dataFromExcel;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	static ChromeDriver driver;
	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	@BeforeMethod
	@Parameters("baseUrl")
	public void openBrowserAndLogin(String url) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(url);

		
	}
		
	@AfterMethod
	public void tearDown() {
		
		WebElement logoutIcon=driver.findElement(By.xpath("(//img[@title='User']//parent::span[@class='uiImage'])[1]"));
		waitClickMethod(driver, logoutIcon);

		WebElement logOut=driver.findElement(By.xpath("//a[text()='Log Out']"));
		waitClickMethod(driver, logOut);
		
		driver.quit();
	}

}
