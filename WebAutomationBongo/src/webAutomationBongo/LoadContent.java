package webAutomationBongo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * script that checks clicking an arbitrary content from bongobd.com and loads the content 
 * @author Shah Alam
 * Version 2019.27.10
 */
public class LoadContent {

	public static void main(String args[]) throws InterruptedException{
		
		// chromedriver.exe must be in the following local directory
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.bongobd.com");
		
		driver.findElement(By.xpath("//*[@id=\"browse-channels\"]/div/div/div[2]/div/div/div/div/div[1]/div/div[1]/div/div/a/img")).click();
		
		String actualUrl = "https://www.bongobd.com/bn/channel/sa-tv";
		String expectedUrl= driver.getCurrentUrl(); 
  		
		// comparing the URLs for content load confirmation
		if(actualUrl.equalsIgnoreCase(expectedUrl)) {
			System.out.println("Content loading successful");
		}
		else
			System.out.println("Problem loading Content");
		
		// this will wait playing the video for 30 second
		Thread.sleep(30000);
		driver.close();
	}
}
