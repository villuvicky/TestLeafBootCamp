package testNg;

import java.awt.AWTException;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.awt.Robot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class CreateCampaignAttachDoc extends baseClass{
	

	public static void waitClickMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
	}
	
	public static void waitVisibleMethod(ChromeDriver driver, WebElement locator) {

		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(locator));
	}
	
	@BeforeClass
	public void setSheetName() {
		
		sheetName="CreateCampaignAttachDoc";
	}

	@Test(dataProvider = "fecthData")

	public void createCampain(String campaign, String SuccessMessage) throws InterruptedException, AWTException {
		
		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		//toggle.click();
		
		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();
		
		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();
		

		WebElement campaigns = driver.findElement(By.xpath("//a[@title='Campaigns']"));
		driver.executeScript("arguments[0].click();", campaigns);
		
		WebElement searchcampain=driver.findElement(By.xpath("//input[@name='Campaign-search-input']"));
		searchcampain.sendKeys(campaign);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();

		Thread.sleep(2000);
		
		WebElement campaignRow=driver.findElement(By.xpath(String.format("//tr[1]//a[@title='%s']",campaign)));
		campaignRow.click();
		
		driver.findElement(By.xpath("//div[@title='Upload Files']")).click();
		
		  Thread.sleep(2000);
		
		 Robot rb = new Robot();
		 
		    // copying File path to Clipboard
		    StringSelection str = new StringSelection("C:\\Users\\villu\\TestLeaf\\myProject\\data\\Sample.pdf");
		    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);
		 
		     // press Contol+V for pasting
		     rb.keyPress(KeyEvent.VK_CONTROL);
		     rb.keyPress(KeyEvent.VK_V);
		 
		    // release Contol+V for pasting
		    rb.keyRelease(KeyEvent.VK_CONTROL);
		    rb.keyRelease(KeyEvent.VK_V);
		 
		    // for pressing and releasing Enter
		    rb.keyPress(KeyEvent.VK_ENTER);
		    rb.keyRelease(KeyEvent.VK_ENTER);
		    
		    Thread.sleep(2000);
		    
		    WebElement doneButton=driver.findElement(By.xpath("//span[text()='Done']//parent::button"));
		    waitClickMethod(driver, doneButton);

			WebElement messageElement=driver
					.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']"));

			waitVisibleMethod(driver, messageElement);

			String actualSuccessMessage = messageElement.getText();

			// toastMessage slds-text-heading--small forceActionsText

			System.out.println("Message is " + actualSuccessMessage);

			Assert.assertEquals(actualSuccessMessage, SuccessMessage);
		
	}

}
