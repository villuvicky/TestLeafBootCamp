package S2.Sprint1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOpportunityWithoutMandatoryFields {

	static String OpportunityName="Salesforce Automation by Vignesh Kannan";
	
	public static void main(String[] args) {
		
		List<String> expectedMessages= new ArrayList<String>();
		expectedMessages.add("We hit a snag.");
		expectedMessages.add("Opportunity Name");
		expectedMessages.add("Stage");
		
		
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver= new ChromeDriver(options);
		
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
		
		WebElement Sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		Sales.click();
		
		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", opportunities);
		
		WebElement newOpportunities=driver.findElement(By.xpath("//a[@title='New']"));
		newOpportunities.click();
		
		WebElement dates=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		dates.click();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");
				
		WebElement chooseDate=driver.findElement(By.xpath("//input[@name='CloseDate']"));
		chooseDate.sendKeys(sdf.format(date));
		
		WebElement saveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();
		
		
		String opportunityText=driver.findElement(By.xpath("(//input[@name='Name']/following::div)[1]")).getText();
		
		
		String stageText=driver.findElement(By.xpath("(//label[text()='Stage']//following::div)[1]")).getText();
		
		System.out.println(opportunityText);
		
		System.out.println(stageText);
		
		
		String warningText=driver.findElement(By.xpath("//h2[@title='We hit a snag.']")).getText();
		
		
		List<WebElement> allMessagesPath=driver.findElements(By.xpath("//li[@force-recordediterror_recordediterror]/a"));
		
		//input[@name='Name']/following::div[@id='help-message-300']
		
		//label[text()='Stage']//following::div[@id='help-text-312']
		
		List<String> actualMessages= new ArrayList<String>();
		actualMessages.add(warningText);
		
		for (WebElement MessagePath : allMessagesPath) {
			
			actualMessages.add(MessagePath.getText());
		}
		
		
		Assert.assertEquals(actualMessages, expectedMessages);
		
		driver.close();
	}

}
