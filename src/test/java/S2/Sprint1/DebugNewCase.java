package S2.Sprint1;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class DebugNewCase {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		Authentication
		driver.findElementById("username").sendKeys("makaia@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();

		Thread.sleep(2000);
		
//		Click SVG Icon
		WebElement svg = driver.findElementByXPath("//div[@class='slds-global-header__item']//li[3]//a");
		svg.sendKeys(Keys.ENTER);
		
//		Click New Event and maximize it
		Actions builder = new Actions(driver);
		builder.click(driver.findElementByXPath("(//span[text()='New Case'])[1]")).perform();
		
		Thread.sleep(2000);
		
		builder.click(driver.findElement(By.xpath("//button[@title='Maximize']"))).perform();
			
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		Thread.sleep(5000);
		
		driver.close();
	}

}
