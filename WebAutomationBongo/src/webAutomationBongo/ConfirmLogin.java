package webAutomationBongo;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * script that checks the login process in bongobd.com
 * @author Shah Alam
 * Version 2019.27.10
 */
public class ConfirmLogin {

	public static void main(String args[])throws InterruptedException {
		
		// chromedriver.exe must be in the following local directory
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Java\\chromedriver_win32\\chromedriver.exe");
		
		WebDriver driver  = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.get("https://www.bongobd.com");
		driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/nav/div/div/div[2]/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.id("regNext")).click();
		
		// declaring parent widow
		String parentWindowHandler = driver.getWindowHandle(); 
		
		// declaring windowHandler for new pop up window
		String newWindowHandler = null;

		// getting widow handles
		Set<String> handles = driver.getWindowHandles(); 
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()){
			newWindowHandler = iterator.next();
		}
		// switched to the current pop up window
		driver.switchTo().window(newWindowHandler); 
		
		// inserting valid phone number
		driver.findElement(By.id("u_0_6q")).sendKeys("1731652213"); 
		driver.findElement(By.id("u_0_6r")).click();
		
		// wait for 30 seconds to input the OTP
		Thread.sleep(30000); 
		
		// manual action needed for collecting OTP from the phone and insert it in the text box 
		//driver.findElement(By.id("u_0_2")).sendKeys();
		driver.findElement(By.id("u_0_3")).click();
		
		
		// verifying successful login 
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.switchTo().window(parentWindowHandler); 
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id=\'content\']/div[1]/nav/div/div/div[2]/div[4]/ul/li[2]/a/span[1]/img")).click();
		driver.findElement(By.xpath("//*[@id=\'content\']/div[1]/nav/div/div/div[2]/div[4]/ul/li[2]/ul/li[1]/a")).click();

		// comparing the URL of user account page
		String actualUrl = "https://www.bongobd.com/bn/account";
		String expectedUrl= driver.getCurrentUrl(); 
		
		if(actualUrl.equalsIgnoreCase(expectedUrl)) {
			System.out.println("login successful");
		}
		else
			System.out.println("login failed");
		    
		// wait for 30 seconds and close the browser
		Thread.sleep(30000);
		driver.close();
	}
}
