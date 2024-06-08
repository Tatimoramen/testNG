import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

public class softAssertions {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		SoftAssert softAssert = new SoftAssert();

		try {
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
			List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));

			for (WebElement link : links) {
				String url = link.getAttribute("href");
				int responseCode = getResponseCode(url);
				System.out.println(responseCode);
				softAssert.assertTrue(responseCode < 400, "El enlace con texto '" + link.getText()
						+ "' está roto con código de respuesta " + responseCode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
			softAssert.assertAll();
		}
	}

	public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(urlString).openConnection();
		connection.setRequestMethod("HEAD");
		connection.connect();
		return connection.getResponseCode();
	}
}
