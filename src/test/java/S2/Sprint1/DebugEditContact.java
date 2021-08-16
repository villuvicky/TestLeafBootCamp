package S2.Sprint1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DebugEditContact {

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

		driver.findElement(By.className("slds-r5")).click();
		
		driver.findElement(By.xpath("(//p[@class='slds-truncate'])[3]")).click();
		
		driver.executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='Contacts']")));
		
		driver.findElement(By.xpath("(//a[contains(@class,'slds-button slds-button--icon-x-small')])[1]")).click();
		
		driver.findElement(By.xpath("(//a[@role='menuitem'])[1]")).click();
		
		driver.executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@name='Birthdate']//following::button[@title='Select a date']")));
	
		driver.findElement(By.xpath("//button[@title='Previous Month']")).click();
		
		driver.findElement(By.xpath("//button[@title='Previous Month']")).click();
		
		driver.findElement(By.xpath("//span[text()='24']")).click();
		
		driver.findElement(By.xpath("//button[@name='SaveEdit']")).click();
		
		Thread.sleep(2000);
		
		String contactnaveenelumalai = driver.findElement(By.xpath("//span[contains(@class,'toastMessage slds-text-heading--small')]")).getText();
		
		System.out.println(contactnaveenelumalai);
		
		driver.close();
	}

}
