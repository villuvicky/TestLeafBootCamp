package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import testNg.BaseClass2;

public class NewOpportunityPage extends BaseClass2{

	public NewOpportunityPage(ChromeDriver driver) {

		this.driver=driver;
	}

	public NewOpportunityPage opportunityName(String opportunityName) {

		WebElement newOpportunitiesName = driver.findElement(By.xpath("//input[@name='Name']"));
		newOpportunitiesName.sendKeys(opportunityName);
		System.out.println(newOpportunitiesName.getAttribute("value"));
		return this;
	}
	public NewOpportunityPage selectDate() {

		WebElement dates = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		dates.click();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/y");

		WebElement chooseDate = driver.findElement(By.xpath("//input[@name='CloseDate']"));
		chooseDate.sendKeys(sdf.format(date));

		return this;
	}
	public NewOpportunityPage stage(String opportunityName) {

		WebElement newOpportunitiesName = driver.findElement(By.xpath("//input[@name='Name']"));
		newOpportunitiesName.sendKeys(opportunityName);
		return this;
	}

}
