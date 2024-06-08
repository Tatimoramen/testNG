import org.testng.annotations.Test;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestNGExample {

	WebDriver driver;
	String driverPath = "./src/test/resources/chomedriver/chromedriver.exe";
	private String baseUrl = "https://clonesyperifericos.com/";

	@BeforeClass
	public void beforeMethod() {
		System.setProperty("webdriver.chome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void userCreate() {
		driver.get(baseUrl);
		WebElement loginLink = driver.findElement(By.xpath("//div[contains(@class, 'et_b_header-account')]"));
		loginLink.click();

		WebElement emailField = driver.findElement(By.id("reg_email"));
		WebElement passwordField = driver.findElement(By.id("reg_password"));
		emailField.sendKeys("shaaat@gmail.com");
		passwordField.sendKeys("tu_contraseña");

		WebElement registerButton = driver.findElement(By.name("register"));
		registerButton.click();
	}

	 @Test(dependsOnMethods = "userCreate")
	public void logOut() {
		WebElement logOut = driver.findElement(By.xpath("//a[normalize-space()='Salir']"));
		logOut.click();
	}

	 @Test(dependsOnMethods = "logOut")
	public void logIn() {
		WebElement email = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		email.sendKeys("shaaat@gmail.com");
		password.sendKeys("tu_contraseña");
		WebElement logInButton = driver.findElement(By.name("login"));
		logInButton.click();
	}
	 
	 @Test(dependsOnMethods = "logIn")
	public void searchCardAndSelect() {
		 driver.get(baseUrl);
		WebElement tiendaTab = driver.findElement(By.xpath("//a[normalize-space()='Tienda']"));
		tiendaTab.click();

		WebElement selectItem = driver.findElement(By.xpath("//h2[contains(text(),'Combo Teclado Trust Azor RGB + Mouse Trust Felox R')]"));
		selectItem.click();
		
		WebElement addToCar = driver.findElement(By.xpath("//button[normalize-space()='Añadir al carrito']"));
		addToCar.click();
		
	}
	 
	 @Test(dependsOnMethods = "searchCardAndSelect")
	public void checkItemICar() {
		WebElement carShopping = driver.findElement(By.xpath("//div[@class='header-wrapper']//a[1]//span[1]//span[2]//span[1]//*[name()='svg']"));
		carShopping.click();
		
		WebElement validatedSHop = driver.findElement(By.xpath("//a[@class='product-title']"));
		validatedSHop.isEnabled();
		
	}
	  @AfterSuite
	  public void tearDown() {
	    driver.quit();
	  }

}
