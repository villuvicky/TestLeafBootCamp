package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import testNg.BaseClass2;

public class SalesHomePage extends BaseClass2{

	public SalesHomePage(ChromeDriver driver) {
		
		this.driver=driver;
	}

	public SalesHomePage opportunityTab() {
		
		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		driver.executeScript("arguments[0].click();", opportunities);
		return this;
	}
	
	
	public NewOpportunityPage newOpportunityButton() {
		
		WebElement newOpportunities = driver.findElement(By.xpath("//a[@title='New']"));
		newOpportunities.click();
		return new NewOpportunityPage(driver);
		
	}
}
