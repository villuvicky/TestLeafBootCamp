package dataFromExcel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class CreateNewOpportunity extends baseClass{

	static String OpportunityName = "Salesforce Automation by Vignesh Kannan";
	static String SuccessMessage = "Opportunity \"Salesforce Automation by Vignesh Kannan\" was created.";
	static WebDriverWait wait;

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void waitVisibleMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {
		
		ReadExcel data= new ReadExcel();
		
		return data.excelData("LoginData");
	}
	
	@Test(dataProvider = "fecthData")
	public  void CreateNewOpportunityTest(String UserName, String Password)  {

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		// TODO Auto-generated method stub

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", opportunities);

		WebElement newOpportunities = driver.findElement(By.xpath("//a[@title='New']"));
		newOpportunities.click();

		WebElement newOpportunitiesName = driver.findElement(By.xpath("//input[@name='Name']"));
		newOpportunitiesName.sendKeys(OpportunityName);

		System.out.println(newOpportunitiesName.getAttribute("value"));

		WebElement dates = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		dates.click();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");

		WebElement chooseDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		chooseDate.sendKeys(sdf.format(date));

		WebElement stage = driver
				.findElement(By.xpath("(//input[@class='slds-input slds-combobox__input'])[3]//parent::div"));
		executor.executeScript("arguments[0].scrollIntoView();", stage);
		stage.click();

		WebElement selectStage = driver.findElement(By.xpath("(//span[@title='Needs Analysis'])"));
		selectStage.click();

		WebElement saveChanges = driver.findElement(By.xpath("//button[@name='SaveEdit']"));
		saveChanges.click();

		//Thread.sleep(2000);


		WebElement messageElement=driver
				.findElement(By.xpath("(//div[@data-aura-class='forceToastMessage']//div)[1]/div"));

		waitVisibleMethod(driver, messageElement);

		String actualSuccessMessage = messageElement.getText();

		// toastMessage slds-text-heading--small forceActionsText

		System.out.println("Message is " + actualSuccessMessage);

		Assert.assertEquals(actualSuccessMessage, SuccessMessage);


	}
}
