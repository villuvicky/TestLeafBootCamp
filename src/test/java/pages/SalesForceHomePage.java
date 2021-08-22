package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import testNg.BaseClass2;

public class SalesForceHomePage extends BaseClass2{

	public SalesForceHomePage(ChromeDriver driver) {

		this.driver=driver;
	}

	public SalesForceHomePage toggleButtonClick() {

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();
		
		return this;

	}
	
	public SalesForceHomePage viewAllButtonClick() {

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();
		
		return this;
	}
	
	public SalesHomePage salesButtonClick() {
		

		WebElement sales = driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();
		return new SalesHomePage(driver);
		
	}
}
