package S2.Sprint1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import com.google.common.base.CharMatcher;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditContact {



	static ChromeDriver driver;
	static String unqiueContactName ;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();
		EditContact.driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com");

		WebElement userName = driver.findElement(By.id("username"));
		userName.sendKeys("makaia@testleaf.com");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("SelBootcamp$123");

		driver.findElement(By.id("Login")).submit();

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement contacts = driver.findElement(By.xpath("//a[@data-label='Contacts']"));
		driver.executeScript("arguments[0].click();", contacts);

		Thread.sleep(2000);

		String intiallyLoadedItems=driver.findElement(By.xpath("//div/span[contains(text(),'items')]")).getText();

		String finalValue=intiallyLoadedItems.replaceAll(" ", "");


		int intialItems=Integer.parseInt(CharMatcher.inRange('0',
				'9').retainFrom(finalValue));


		int currentItems = 0;

		while(!(intialItems==currentItems)) 

		{


			List<WebElement> currentList=driver.findElements(By.xpath("//tr/td[7]"));

			int lastSize=currentList.size()-1;

			currentList.get(lastSize).click();

			Thread.sleep(2000);

			currentItems=currentList.size();

			intiallyLoadedItems=driver.findElement(By.xpath("//span[@class='countSortedByFilteredBy']")).getText();

			intialItems=Integer.parseInt(CharMatcher.inRange('0','9').retainFrom( intiallyLoadedItems));

			Thread.sleep(2000);

		}

		System.out.println("Total Contacts are "+currentItems);

		List<String> contactsName= new ArrayList<String>();

		Set<String> sets= new LinkedHashSet<String>();

		List<WebElement>allNames=driver.findElements(By.xpath("//table/tbody/tr/th/span/a"));

		for (WebElement webElement : allNames) {

			contactsName.add(webElement.getText());
			sets.add(webElement.getText());
		}


		System.out.println(contactsName);

		System.out.println(sets);



		for (String name : sets) {


			int searchSize=driver.findElements(By.xpath("//table/tbody/tr/th/span/a[text()='"+name+"']")).size();

			System.out.println(searchSize);

			if(searchSize==1) {

				unqiueContactName=name;
				
				System.out.println(unqiueContactName);

				driver.findElement(By.xpath(String.format("//a[@title='%s']//ancestor::tr/td[8]/span",unqiueContactName))).click();

				break;

			}
		}
			
			WebElement editContact=driver.findElement(By.xpath("//a[@title='Edit']"));
			editContact.click();

			WebElement title= driver.findElement(By.xpath("//label[text()='Title']//following::input[@name='Title']"));
			title.clear();
			title.sendKeys("Test");

			WebElement birthDate= driver.findElement(By.xpath("//label[text()='Birthdate']//following::input[@name='Birthdate']"));
			birthDate.clear();
			birthDate.sendKeys("02/15/1997");

			WebElement leadSourceDropDown= driver.findElement(By.xpath("//label[text()='Lead Source']//following::input[1]"));
			driver.executeScript("arguments[0].click();", leadSourceDropDown);
			//leadSourceDropDown.click();

			WebElement leadSource=driver.findElement(By.xpath("//label[text()='Lead Source']//following::span[@title='Purchased List']"));
			leadSource.click();

			WebElement phoneNumber=driver.findElement(By.xpath("//label[text()='Phone']//following::input[@name='Phone']"));
			phoneNumber.clear();
			phoneNumber.sendKeys("985621144");

			String enteredNumber=phoneNumber.getAttribute("value");
			System.out.println("Entered phone number "+enteredNumber);

			WebElement SaveChanges=driver.findElement(By.xpath("//button[@name='SaveEdit']"));
			SaveChanges.click();

			Thread.sleep(2000);

			WebElement actualNumber= driver.findElement(By.xpath(String.format("(//th[@title='Phone']//following::a[@title='%s']//ancestor::tr//td[5]/span/span)[1]", unqiueContactName)));
			
			System.out.println(String.format("(//th[@title='Phone']//following::a[@title='%s']//ancestor::tr//td[5]/span/span)[1]", unqiueContactName));
			
			System.out.println("Actual phone number "+actualNumber.getText());

			Assert.assertEquals(actualNumber.getText(), enteredNumber);

			driver.close();

		}

	}

