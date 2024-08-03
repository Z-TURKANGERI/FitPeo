package automationScript;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Script {

	public static void main(String[] args) {
		testScript();

	}

	@Test
	public static void testScript() {
		WebDriver driver = new ChromeDriver(); // Invoking chrome browser
		driver.manage().window().maximize(); // Maximizing window
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // Waiting machinisam if some element take
																			// time to load
		driver.get("https://www.fitpeo.com/"); // Navigating to URL

		// 2nd Task
		driver.findElement(By.linkText("Revenue Calculator")).click(); // Clicking on the revenue calculator

		// 3rd Task
		WebElement MedicareEligiblePatientsText = driver
				.findElement(By.xpath("//h4[text()='Medicare Eligible Patients']")); // Scrolling up to Medicare
																						// Eligible Patients text
		JavascriptExecutor jse = (JavascriptExecutor) driver; // Creating object of JavascriptExecutor
		jse.executeScript("arguments[0].scrollIntoView()", MedicareEligiblePatientsText);

		// 4th Task
		Actions action = new Actions(driver); // Creating object of Actions class
		WebElement sliderPoint = driver.findElement(By.xpath("//input[@type='range']"));

		WebElement slidingTextFiled = driver.findElement(By.xpath("//div[contains(@class,'MuiInputBase-root')]")); // locator sliding text filed
																													 
																													
																													
		action.moveToElement(slidingTextFiled).click(); // Clicking on sliding test filed

		for (int i = 0; i < 3; i++) {
			action.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE); // Clearing filed with the help of back space
		}
		action.dragAndDropBy(sliderPoint, 94, 0).build().perform(); // To get approximate value given to set

		
		
		
		// 5th + 6th Task
		action.moveToElement(slidingTextFiled).click(); // Clicking on sliding text filed
		for (int i = 0; i < 3; i++) {
			action.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE); // Clearing filed with the help of back space
		}
		action.sendKeys("560").build().perform(); // Sending value by using action class

		String SliderTextFiledValue = driver.findElement(By.xpath("//span[@class='MuiSlider-track css-10opxo5']"))
				.getAttribute("style"); // For assertion find how much slider moved
		String[] styles = SliderTextFiledValue.split(";"); // Split the style string by semicolon
		String widthValue = ""; // Initialize width variable

		for (String style : styles) {
			style = style.trim(); // Trim any leading or trailing whitespace from each style attribute
			if (style.startsWith("width:")) {
				widthValue = style.split(":")[1].trim(); // Extract the width value by splitting by colon and trimming
															// whitespace
				break;
			}
		}
		String percentage = widthValue.replaceAll("[^0-9%]", ""); // extracting only the percentage part
		Assert.assertEquals(percentage, "28%"); // assertion for slider moved

		action.moveToElement(slidingTextFiled).click(); // Clicking on sliding text filed
		for (int i = 0; i < 3; i++) {
			action.keyDown(Keys.BACK_SPACE).keyUp(Keys.BACK_SPACE); // Clearing filed with the help of back space
		}
		action.sendKeys("820").build().perform(); // Sending value by using action class

		
		// 7th Task
		List<WebElement> groups = driver.findElements(By.xpath("//div[@class='MuiBox-root css-1p19z09']//p[@class='MuiTypography-root MuiTypography-body1 inter css-1s3unkt']"));

		int var = 1; // Start from 1 if XPath index starts from 1
		for (WebElement group : groups) {
		    String name = group.getText();
		    
		    // Check if the group name is one of the specific values
		    if (name.equals("CPT-99091") || name.equals("CPT-99453") || name.equals("CPT-99454") || name.equals("CPT-99474")) {
		        WebElement checkbox = driver.findElement(By.xpath("(//input[@type='checkbox'])[" + var + "]"));
		        if (!checkbox.isSelected()) {
		            checkbox.click();
		        }
		    }
		    
		    var++;
		}
	
		WebElement cpt = driver.findElement(By.xpath("//p[text()='CPT-99091']"));
		jse.executeScript("arguments[0].scrollIntoView()", cpt);
		
		
		//8th and 9th Task
		String val = driver.findElement(By.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[3]")).getText();
		System.out.println(val);
		Assert.assertEquals(driver.findElement(By.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[3]")).getText(), "$110700");

		String total_patients = driver
				.findElement(By
						.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[2]"))
				.getText();
		System.out.println(total_patients);
		Assert.assertEquals(driver
				.findElement(By
						.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[2]"))
				.getText(), "820");

		String OneTimeReimbursementforAllPatientsAnnually = driver
				.findElement(By
						.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[1]"))
				.getText();
		System.out.println(OneTimeReimbursementforAllPatientsAnnually);
		Assert.assertEquals(driver
				.findElement(By
						.xpath("(//p[contains(@class,'MuiTypography-root MuiTypography-body1 inter css-12bch19')])[1]"))
				.getText(), "$15735.80");

		driver.close();

	}

}