package dataFromExcel;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.google.common.base.CharMatcher;

public class OpportunitiesSortByAscending extends baseClass{

	static List<WebElement> actualAscendedDatesElements;
	static int lastElementSize;
	static String intiallyLoadedItems;

	public static int sizeInNumber(String text) {


		String[] allTexts=text.split(" ");

		String intialNumberText=allTexts[0];

		return Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(intialNumberText));


	}
	
	@DataProvider(name = "fecthData")
	public String[][] getData() throws IOException {

		ReadExcel data= new ReadExcel();

		return data.excelData("LoginData");
	}

	@Test(dataProvider = "fecthData")

	public void OpportunitiesSortByAscendingTest(String UserName, String Password) throws InterruptedException, ParseException {

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(UserName);

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);

		driver.findElement(By.id("Login")).submit();
		
		WebElement toggle=driver.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll=driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement sales=driver.findElement(By.xpath("//p[text()='Sales']"));
		sales.click();

		WebElement opportunities = driver.findElement(By.xpath("//a[@title='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", opportunities);

		WebElement displayOption=driver.findElement(By.xpath("//button[@title='Display as Table']"));
		displayOption.click();


		WebElement tableView=driver.findElement(By.xpath("//li[@title='Table']"));
		tableView.click();

		int i=1;

		while(i<3) {

			WebElement ascendingOrder=driver.findElement(By.xpath("//th[@title='Close Date']/div"));
			ascendingOrder.click();
			i++;

		}


		Thread.sleep(2000);

		intiallyLoadedItems=driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();

		int currentItems = 0;

		while(!(sizeInNumber(intiallyLoadedItems)==currentItems)) {

			Thread.sleep(2000);

			actualAscendedDatesElements=driver.findElements(By.
					xpath("//th[@title='Close Date']/div//following::span[@class='uiOutputDate']//parent::span[@class='slds-truncate slds-text-align_right slds-grow  sfaOpportunityDealMotionCloseDate']"
							)); 
			lastElementSize= actualAscendedDatesElements.size()-1;

			actualAscendedDatesElements.get(lastElementSize).click(); 

			currentItems=actualAscendedDatesElements.size();

			Thread.sleep(2000);

			intiallyLoadedItems=driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();

			sizeInNumber(intiallyLoadedItems);

			Thread.sleep(2000);

		}

		List<WebElement> allElementsDate= driver.findElements(By.xpath("//th[@title='Close Date']/div//following::span[@class='slds-truncate slds-text-align_right slds-grow  sfaOpportunityDealMotionCloseDate']//span[@class='uiOutputDate']"));

		List<String> actualDates= new LinkedList<String>();

		for (WebElement webElement : allElementsDate) {

			actualDates.add(webElement.getText());

			System.out.println(webElement.getText());
		}


		List<String> sortedDates= new LinkedList<String>();


		for (String string : actualDates) {


			SimpleDateFormat format = new SimpleDateFormat("M/d/yyyy");
			Date date=format.parse(string);
			sortedDates.add(format.format(date));
			//System.out.println(format.format(date));

		}


		if(sortedDates.equals(actualDates))
			Assert.assertTrue(true);

		else
			Assert.assertTrue(false);

	}

}
