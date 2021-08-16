package week1.day1;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOpportunity {
	
	static String OpportunityName="Salesforce Automation by Vignesh Kannan";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);
		
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
		
		WebElement Opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Opportunities);
		
		WebElement NewOpportunities=driver.findElement(By.xpath("//a[@title='New']"));
		NewOpportunities.click();
		
		WebElement NewOpportunitiesName=driver.findElement(By.xpath("//input[@name='Name']"));
		NewOpportunitiesName.sendKeys(OpportunityName);
		
		WebElement Dates=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		Dates.click();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
				
		WebElement ChooseDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		ChooseDate.sendKeys(sdf.format(date));
		
		WebElement Stage=driver.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[3]//parent::div"));
		executor.executeScript("arguments[0].scrollIntoView();", Stage);
		Stage.click();
		
		WebElement SelectStage=driver.findElement(By.xpath("(//span[@title='Needs Analysis'])"));
		SelectStage.click();
		
		WebElement SaveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		SaveChanges.click();
		
		String actualName=driver.findElement(By.xpath("//slot[@slot='primaryField']//lightning-formatted-text")).getText();
		Assert.assertEquals(actualName, OpportunityName);
		
		
		driver.close();
	
	}

}
