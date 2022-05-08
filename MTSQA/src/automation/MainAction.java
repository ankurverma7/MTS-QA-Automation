package automation;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainAction {
	static int respCode;

	public static int responsecode(WebDriver driver) throws MalformedURLException, IOException {
		// method to get the response code
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='games-item'] a"));
		for (WebElement link : links) {
			String url = link.getAttribute("href");
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			respCode = conn.getResponseCode();
		}

		return respCode;
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException, IOException {
		
		// get the home directory
		String userdirectory = System.getProperty("user.dir");
		
		//invoking web browser and web-site
		System.setProperty("webdriver.chrome.driver",userdirectory+"\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.game.tv/");

		// scroll down till the area where we can see all the game lists
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,5500)");

		// limiting the scope of webdriver to the games list
		WebElement gamelist = driver.findElement(By.className("games-list"));
		int size = gamelist.findElements(By.tagName("a")).size();

		//open the links in new tab
		for (int i = 0; i <= size - 1; i++) {
			String clickonnewtab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			gamelist.findElements(By.tagName("a")).get(i).sendKeys(clickonnewtab);
		}

		// handling multiple windows
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		int k = 1;
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			System.out.print(k + "\t\t");
			System.out.print(driver.getTitle() + "\t\t");
			System.out.print(driver.getCurrentUrl() + "\t\t");
			System.out.print(responsecode(driver) + "\t\t");
			System.out.println("");
			k++;
		}

	}

}
