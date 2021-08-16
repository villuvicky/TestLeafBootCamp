package testNg;

import java.io.IOException;
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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {

	public ChromeDriver driver;
	static WebDriverWait wait;
	public String sheetName;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}

	public static void waitVisibleMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	
	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {
		
		ReadExcel data = new ReadExcel();
		
		return data.excelData(sheetName);
	}
	
	@BeforeMethod
	@Parameters({"baseUrl","username","password"})
	public void openBrowserAndLogin(String URL, String Userame, String Password) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(URL);

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(Userame);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {

		Thread.sleep(1000);

		WebElement logoutIcon=driver.findElement(By.xpath(
				"(//img[@title='User']//parent::span[@class='uiImage'])[1]"));
		waitClickMethod(driver, logoutIcon);

		WebElement logOut=driver.findElement(By.xpath("//a[text()='Log Out']"));
		waitClickMethod(driver, logOut);


		driver.quit();

	}

}
