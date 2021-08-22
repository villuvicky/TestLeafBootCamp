package testNg;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
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
import pages.LoginPage;

public class BaseClass2 {

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
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"baseUrl","username","password"})
	public void openBrowserAndLogin(String URL, String Userame, String Password) throws InterruptedException {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get(URL);
		
		LoginPage lp =new LoginPage(driver);
		lp.enterUserName(Userame).enterPassword(Password).clickLogin();
		
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() throws InterruptedException {

		driver.quit();

	}

}
