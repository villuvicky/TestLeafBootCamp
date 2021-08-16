package S2.Sprint1;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DebugNewLead {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://login.salesforce.com/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

//		Authentication
		driver.findElementById("username").sendKeys("makaia@testleaf.com");
		driver.findElementById("password").sendKeys("SelBootcamp$123");
		driver.findElementById("Login").click();

		Thread.sleep(20000);
		
//		Click SVG Icon
		WebElement svg = driver.findElementByXPath("//div[@class='slds-global-header__item']//li[3]//a");
		svg.sendKeys(Keys.ENTER);
		
//		Click New Event and maximize it
		Actions builder = new Actions(driver);
		builder.click(driver.findElementByXPath("(//span[text()='New Lead'])[1]")).perform();
		
		builder.click(driver.findElement(By.xpath("//button[@title='Maximize']"))).perform();
		
		driver.findElementByXPath("//span[text()='Salutation']//following::a[@class='select']").click();
												
		driver.findElement(By.xpath("//a[@title='Mr.']")).click();
		
		driver.findElementByXPath("//input[@placeholder='Last Name']").sendKeys("K");
		
		driver.findElementByXPath("//span[text()='Company']//following:: input[@class=' input'][1]").sendKeys("Fortune");
			
		driver.findElementByXPath("(//span[text()='Save'])[2]").click();
		
		
		
		driver.close();
	}

}
