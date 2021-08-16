package S2.Sprint1;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOpportunity {
	
	static String opportunityName="Salesforce Automation by Vignesh Kannan";
	static String stage="Perception Analysis";
	static String deliveryStatus="In progress";

	public static void main(String[] args) throws InterruptedException  {
		
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver(options);

		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(20));

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
		driver.executeScript("arguments[0].click();", element);

		WebElement searchOppurtunity=driver.findElement(By.xpath("//input[@name='Opportunity-search-input']"));
		searchOppurtunity.sendKeys(opportunityName);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);
		WebElement dropdown= driver.findElement(By.xpath(String.format("//a[@title='%s']//ancestor::tr//td[8]", opportunityName)));
		//ancestor::tr/td[8]/span
		
		System.out.println(String.format("//a[@title='%s']//ancestor::tr//td[8]", opportunityName));

		wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
		
		
		WebElement editOppurtunity=driver.findElement(By.xpath("//a[@title='Edit']"));
		editOppurtunity.click();
		
		WebElement selectDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		selectDate.clear();

		Date date = new Date();
		String dateToStr = DateFormat.getInstance().format(date); 
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y"); //hh is to print 12 hours format
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 1);  // number of days to add
		dateToStr = sdf.format(c.getTime());

		selectDate.sendKeys(dateToStr);

		WebElement stageDropdown=driver.findElement(By.xpath("//label[text()='Stage']/following::input[1]//parent::div"));
		stageDropdown.click();
		
		WebElement selectStage=driver.findElement(By.xpath(String.format("(//span[@title='%s'])",stage)));
		selectStage.click();

		WebElement statusBox=driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[7]//parent::div"));
		driver.executeScript("arguments[0].click();", statusBox);

		WebElement deliverStatus=driver.findElement(By.xpath(String.format("(//span[@title='%s'])",deliveryStatus)));
		deliverStatus.click();

		WebElement description=driver.findElement(By.xpath("//label[text()='Description']//parent::lightning-textarea/div/textarea"));
		description.clear();
		description.sendKeys("SalesForce");
		
		WebElement saveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();


		String actualStage=driver.findElement(By.xpath(String.format("(//a[@title='%s']//ancestor::tr/td[5])[1]", opportunityName))).getText();
		Assert.assertEquals(actualStage, stage);
		
		driver.close();
	}

}
