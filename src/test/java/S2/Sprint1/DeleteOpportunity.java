package S2.Sprint1;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteOpportunity {

	static String OpportunityName="Salesforce Automation by Vignesh Kannan";
	static String deleteMessage="Opportunity \"Salesforce Automation by Vignesh Kannan\" was deleted. Undo";

	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitVisibleMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys("makaia@testleaf.com");

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys("SelBootcamp$123");

		driver.findElement(By.id("Login")).submit();

		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement element = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		WebElement searchOppurtunity=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		searchOppurtunity.sendKeys(OpportunityName);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);

		WebElement dropdown2= driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr//td[8])[1]", OpportunityName)));
		dropdown2.click();

		WebElement deleteOppurtunity=driver.findElement(By.xpath("//a[@title='Delete']"));
		deleteOppurtunity.click();

		WebElement confirmDelete=driver.findElement(By.xpath("//button[@title='Delete']"));
		confirmDelete.click();

		WebElement messageElement =driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));

		waitVisibleMethod(driver, messageElement);

		String actualDeleteMessage= messageElement.getText();

		//toastMessage slds-text-heading--small forceActionsText

		System.out.println(actualDeleteMessage);

		Assert.assertEquals(actualDeleteMessage, deleteMessage);
		
		driver.close();
	}

}
