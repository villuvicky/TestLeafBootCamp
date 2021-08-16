package testNg;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CreateNewUserProvisioningRequest extends baseClass{

	public static List<String> getDropDownValues(WebElement element, String type) {

		Select select =  new Select(element);
		List<WebElement> operatorValues=select.getOptions();
		List<String> Dropdown= new ArrayList<String>();

		for (WebElement webElement : operatorValues) {
			Dropdown.add(webElement.getText());
		}

		System.out.println(type+" values are "+ Dropdown);

		System.out.println(Dropdown.size());

		return Dropdown;
	}

	@BeforeClass
	public void setSheetName() {
		
		sheetName="CreateNewUserProvisioningReques";
	}

	@Test(dataProvider = "fecthData")
	public void createNewUserProvisioningRequestTest(String viewName, String viewUniqueName) {

		WebElement toggle = driver
				.findElement(By.xpath("//button[contains(@class,'slds-button slds-icon-waffle_container')]"));
		toggle.click();

		WebElement viewAll = driver.findElement(By.xpath("//button[text()='View All']"));
		viewAll.click();

		WebElement contacts = driver.findElement(By.xpath("//a[@data-label='User Provisioning Requests']"));
		driver.executeScript("arguments[0].click();", contacts);

		WebElement classicView= driver.findElement(By.xpath("//a[text()='Open in Salesforce Classic.']"));
		waitClickMethod(driver, classicView);

		Set<String> allWindows=driver.getWindowHandles();

		List<String> currentWindows=new ArrayList<String>(allWindows);

		driver.switchTo().window(currentWindows.get(1));

		WebElement createNewUser=driver.findElement(By.xpath("//a[text()='Create New View']"));
		waitClickMethod(driver, createNewUser);

		WebElement viewNameBox=driver.findElement(By.xpath("//label[text()='View Name:']//following::input[@id='fname']"));
		viewNameBox.sendKeys(viewName);
		
		System.out.println(viewNameBox.getAttribute("value"));

		WebElement viewUniqueNameBox=driver.findElement(By.xpath("//label[text()='View Unique Name:']//following::input[@id='devname']"));
		System.out.println(viewUniqueNameBox.getAttribute("value"));
		viewUniqueNameBox.clear();
		viewUniqueNameBox.sendKeys(viewUniqueNameBox.getText()+ viewUniqueName);
		System.out.println(viewUniqueNameBox.getAttribute("value"));

		WebElement ownerCheckBox=driver.findElement(By.xpath("//label[text()='My User Provisioning Requests']//preceding::input[@id='fscope1']"));

		if(!(ownerCheckBox.isSelected()))
			ownerCheckBox.click();

		WebElement dropDown1=driver.findElement(By.xpath("//th[text()='Field']//following::td[2]//select[@id='fcol1']"));

		Select select =  new Select(dropDown1);
		select.selectByVisibleText("Name");

		WebElement operatorDropDown=driver.findElement(By.xpath("//th[text()='Operator']//following::td[3]//select[@class='operator']"));
		getDropDownValues(operatorDropDown,"operatorDropDown");

		select =  new Select(dropDown1);
		select.selectByVisibleText("Created Date");

		WebElement availableFieldsDropDown=driver.findElement(By.xpath("//label[text()='Available Fields']//following::select[@id='colselector_select_0']"));
		getDropDownValues(availableFieldsDropDown, "availableFieldsDropDown");

		WebElement SelectedFieldsDropDown=driver.findElement(By.xpath("//label[text()='Selected Fields']//following::select[@id='colselector_select_1']"));
		getDropDownValues(SelectedFieldsDropDown, "SelectedFieldsDropDown");

		select =  new Select(availableFieldsDropDown);
		select.selectByVisibleText("Created Date");

		WebElement add=driver.findElement(By.xpath("//a[@id='colselector_select_0_right']//img[@title='Add']"));
		add.click();

		if(getDropDownValues(SelectedFieldsDropDown, "SelectedFieldsDropDown").contains("Created Date"))

			Assert.assertTrue(true);

		else
			Assert.assertTrue(false);
		
		WebElement  restrictVisibility=driver.findElement(By.xpath("//h3[text()='Step 4. Restrict Visibility']//following::label[@for='fsharefshareall']//preceding::input[@id='fsharefshareall']"));

		if(!(restrictVisibility.isSelected()))
			restrictVisibility.click();

		WebElement save=driver.findElement(By.xpath("//h3[text()='Step 4. Restrict Visibility']//following::input[@name='save']"));
		save.click();
		
		String viewNameText=driver.findElement(By.xpath("//img[@class='pageTitleIcon']//following::h1[@class='noSecondHeader pageType']")).getText();
		Assert.assertEquals(viewNameText, viewName);
		
		String title=driver.getTitle();
		System.out.println(title);
		
		Set<String> allNewWindows=driver.getWindowHandles();
		String currentWindow= driver.getWindowHandle();
		
		for (String string : allNewWindows) {
			if(!(string.equals(currentWindow))) {
				
				driver.close();
			}
				
		}
	}
}
