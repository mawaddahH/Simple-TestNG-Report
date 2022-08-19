/**
 * @author Mawaddah Hanbali
 */
package ass1W10D2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class ChromWebDriverAss1W10D2 {
	public WebDriver driver;

	/**
	 * Set up browser settings and open the application
	 */

	@BeforeSuite
	public void setUp() {
		// the path for open WebSite
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\lo0ol\\" + "Downloads\\Compressed\\chromedriver_win32_2\\chromedriver.exe");
		driver = new ChromeDriver();

		// Navigate to a WebSite
		driver.navigate().to("https://www.instagram.com/?hl=en");

		// Maximize current window
		driver.manage().window().maximize();
	}

	/**
	 * Test error message
	 * 
	 * @throws InterruptedException
	 */
	@Test
	public void TestNG() throws InterruptedException {
		
		// wait until the login form appears.
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//form[@id='loginForm']")));
		
		// username
		WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
		userName.sendKeys("Mawaddah@gmail.com");
		
		// password
		WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
		password.sendKeys("Mawaddah@gmail.com");
		
		// log-in button
		WebElement loging = driver.findElement(By.xpath("//button[contains(@type,'submit')]"));
		loging.click();
		
		// wait until error message displayed
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(@role,'alert')]")));

		WebElement error = driver.findElement(By.xpath("//p[contains(@role,'alert')]"));
		if(error.isDisplayed()) {
			String text = error.getText();
			Assert.assertEquals(text, "Sorry, your password was incorrect. Please double-check your password.");
		}
		
		Thread.sleep(3000);

	}

	/**
	 * Tear down the setup after test completes
	 */
	@AfterSuite
	public void terminateBrowser() {
		// Close the browser
		driver.quit();
	}
}
