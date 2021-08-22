package testCases;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pages.SalesForceHomePage;
import testNg.BaseClass2;

public class CreateNewOpportunityTest extends BaseClass2{
	
	@BeforeClass(groups = { "Opportunity"})
	public void setSheetName() {
		
		sheetName="CreateNewOpportunity";
	}
	
	@Test
	public void create() {
		
		SalesForceHomePage pg = new SalesForceHomePage(driver);
		pg.toggleButtonClick().viewAllButtonClick().salesButtonClick();
		
	}

}
