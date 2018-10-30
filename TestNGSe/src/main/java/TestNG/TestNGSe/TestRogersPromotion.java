package TestNG.TestNGSe;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestRogersPromotion {
	final static Logger logger = LogManager.getLogger(TestRogersPromotion.class.getName());
	public static WebDriver driver;

	public static void SetupFirefox(String URL) {
		System.setProperty("webdriver.gecko.driver", "/Users/yulli/Documents/browserdriver/geckodriver23");
		driver = new FirefoxDriver();

		driver.get(URL);
	}

//	@BeforeClass
	public static void SetupChrome(String URL) {

		System.setProperty("webdriver.chrome.driver", "/Users/yulli/Documents/browserdriver/chromedriver");
		driver = new ChromeDriver();

		driver.get(URL);
	}

	@AfterClass
	public void afterClass() {
		// Ideal place to perform some cleanup of setup which is shared among all tests.
		System.out.println("@AfterClass: I run only once, after all tests have been done.\n");
		driver.close();
	}

	@Test
	public void testHyperText(){
		SetupChrome("https://rogers.com");

		/*
		 * test By.LinkText --> Success
		 */
		WebDriverWait wait3 = new WebDriverWait(driver, 30);
		wait3.until(ExpectedConditions.elementToBeClickable(By.linkText("Learn more")));

		WebElement linkItem = driver.findElement(By.linkText("Learn more"));
		linkItem.click();// ("main-nav-item-1"));
		String title = driver.getTitle();

		logger.info("Title is {}}", title);

		/* Extract Hypertext information in Learn more page */

		List<WebElement> aTags = driver.findElements(By.tagName("a"));

		logger.info("# of Link in this page : {}", aTags.size());
		for (int i = 0; i < aTags.size(); i++) {
			logger.info("{} : {} \n {}", i + 1, aTags.get(i).getAttribute("href"), aTags.get(i).getText());
			// System.out.println((i + 1) + ": " + aTags.get(i).getAttribute("href") + "\n"
			// + aTags.get(i).getText());
		}

		WebElement xPathCont = driver
				.findElement(By.xpath("/html/body/div[1]/section/div/div[1]/div/div[4]/div/div[4]/div[1]/h2"));
		logger.info("xPath - {}", xPathCont.getText());
		
		Assert.assertEquals(aTags.size(), 261);

	}
}
