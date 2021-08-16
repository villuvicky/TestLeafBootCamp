package testNg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class ArchitectCertifications extends baseClass{

	@Test
	public void crchitectCertifications() throws IOException{

		WebElement mobilePublisher=driver.findElement(By.
				xpath("//span[text()='Mobile Publisher']//ancestor::div[@class='tileHelp']//div[@class='tileNavButton']/button"
						)); 
		driver.executeScript("arguments[0].click();", mobilePublisher);

		String homeWindow=driver.getWindowHandle();

		Set<String> allWIndows=driver.getWindowHandles();

		for (String string : allWIndows) {

			driver.switchTo().window(string);	
		}

		WebElement resources=driver.findElement(By.xpath("//span[text()='Resources']"));
		resources.click();

		WebElement salesForceCertification=driver.findElement(By.xpath("//span[text()='Salesforce Certification ']"));
		driver.executeScript("arguments[0].click();", salesForceCertification);


		Set<String> windows2=driver.getWindowHandles();

		List<String> currentWindows=new ArrayList<String>(windows2);

		driver.switchTo().window(currentWindows.get(2));

		WebElement salesforceArchitect=driver.findElement(By.xpath("//div[text()='Salesforce Architect']"));
		salesforceArchitect.click();

		String text= driver.findElement(By.xpath("(//h2[normalize-space(text()) = 'Salesforce Architect']//parent::div/child::div)[1]")).getText();
		System.out.println(text);

		WebElement technicalArchitectLogo =   driver.findElement(By.xpath("//img[@src='https://developer.salesforce.com/resources2/certification-site/images/architect-pyramid-1.png']"));

		String logoSRC = technicalArchitectLogo.getAttribute("src");

		URL imageURL = new URL(logoSRC);

		BufferedImage saveImage = ImageIO.read(imageURL);

		ImageIO.write(saveImage, "png", new File("./data/technicalArchitectLogo.png"));


		WebElement solutionArchitectLogo =   driver.findElement(By.xpath("//img[@src='https://developer.salesforce.com/resources2/certification-site/images/architect-pyramid-2.png']"));

		logoSRC = solutionArchitectLogo.getAttribute("src");

		imageURL = new URL(logoSRC);

		saveImage = ImageIO.read(imageURL);

		ImageIO.write(saveImage, "png", new File("./data/solutionArchitectLogo.png"));

		List<WebElement> certificateElements= driver.findElements(By.xpath("//div[text()='Certification']//ancestor::div[@class='cs-card tile']//div[3]/a"));

		List<String> certificates= new ArrayList<String>();

		for (WebElement webElement : certificateElements) {
			certificates.add(webElement.getText());
		}

		System.out.println(certificates);

		driver.switchTo().window(homeWindow);
	}




}
