package week1.day1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOppurtunity {

	static String OpportunityName="Salesforce Automation by Vignesh Kannan";
	static String Stage="Perception Analysis";
	static String DeliveryStatus="In progress";

	public static void main(String[] args) throws InterruptedException  {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");

		WebElement UserName=driver.findElement(By.id("username"));
		UserName.sendKeys("makaia@testleaf.com");
		
		WebElement Password=driver.findElement(By.id("password"));
		Password.sendKeys("SelBootcamp$123");
		
		driver.findElement(By.id("Login")).submit();
		
		WebElement Toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		Toggle.click();
		
		WebElement ViewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		ViewAll.click();
		
		WebElement Sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();

		WebElement element = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);

		WebElement SearchOppurtunity=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		SearchOppurtunity.sendKeys(OpportunityName);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER);
		
		//Dropdown to click edit
		Thread.sleep(2000);
		WebElement Dropdown= driver.findElement(By.xpath(String.format("//a[@title='%s']//ancestor::tr/td[8]", OpportunityName)));
		wait.until(ExpectedConditions.visibilityOf(Dropdown)).click();;
		//wait.until(ExpectedConditions.elementToBeClickable(Dropdown)).click();

		WebElement EditOppurtunity=driver.findElement(By.xpath("//a[@title='Edit']"));
		EditOppurtunity.click();
		
		WebElement SelectDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		SelectDate.clear();

		Date date = new Date();
		String dateToStr = DateFormat.getInstance().format(date); 
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y"); //hh is to print 12 hours format
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);  // number of days to add
		dateToStr = sdf.format(c.getTime());

		SelectDate.sendKeys(dateToStr);

		WebElement StageDropdown=driver.findElement(By.xpath("//label[text()='Stage']/following::input[1]//parent::div"));
		StageDropdown.click();
		
		WebElement SelectStage=driver.findElement(By.xpath(String.format("(//span[@title='%s'])",Stage)));
		SelectStage.click();

		WebElement stageDropdown=driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[7]//parent::div"));
		executor.executeScript("arguments[0].scrollIntoView();", stageDropdown);
		stageDropdown.click();

		WebElement DeliverStatus=driver.findElement(By.xpath(String.format("(//span[@title='%s'])",DeliveryStatus)));
		DeliverStatus.click();

		WebElement Description=driver.findElement(By.xpath("//label[text()='Description']//parent::lightning-textarea/div/textarea"));
		Description.clear();
		Description.sendKeys("SalesForce");
		
		WebElement SaveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		SaveChanges.click();


		String ActualStage=driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr/td[5])[1]", OpportunityName))).getText();
		Assert.assertEquals(ActualStage, Stage);
		
		driver.close();
	}

}
