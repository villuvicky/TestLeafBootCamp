package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import testNg.baseClass;

public class LoginPage extends baseClass{

	public LoginPage(ChromeDriver driver){

		this.driver=driver;
	}


	public LoginPage enterUserName(String Username) {

		WebElement userName=driver.findElement(By.id("username"));
		userName.sendKeys(Username);
		return this;
	}

	public LoginPage enterPassword(String Password) {

		WebElement password=driver.findElement(By.id("password"));
		password.sendKeys(Password);
		return this;
	}

	public SalesForceHomePage clickLogin() {

		driver.findElement(By.id("Login")).submit();
		return new SalesForceHomePage(driver);
	}
}
