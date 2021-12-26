package test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Selenium4Test {

	public static void main(String[] args) throws InterruptedException, IOException {
		//System.setProperty("webdriver.chrome.driver", "./ChromeDriver/chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().scriptTimeout(Duration.ofMinutes(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

		driver.get("https://google.com");
		
		JavascriptExecutor js = (JavascriptExecutor)driver;

		WebElement button =driver.findElement(By.id("vc3jof"));

		js.executeScript("arguments[0].click();", button);
		js.executeScript("console.log('hello world')");
		Thread.sleep(3000);
		driver.navigate().to("https://www.amazon.com/");
		driver.findElement(By.id("nav-hamburger-menu")).click();
		
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());
		
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./image.png"));
		
		WebElement element = driver.findElement(By.cssSelector(".landscape-image"));
		File scrFile1 = element.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile1, new File("./image1.png"));
	
		driver.navigate().back();
		Thread.sleep(2000);
		
		driver.navigate().forward();
		Thread.sleep(2000);
		
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		//switching window.
		String originalWindow = driver.getWindowHandle();
		driver.switchTo().window(originalWindow);
		
		//New Window
		driver.switchTo().newWindow(WindowType.WINDOW);
		
		//new Tab
		driver.switchTo().newWindow(WindowType.TAB);
		
		
		Thread.sleep(1000);
		//driver.close();
		//complete session
		//driver.quit();
	}



}
