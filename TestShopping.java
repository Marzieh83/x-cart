
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;


public class TestShopping {
	WebElement username,password,signup,login,checkout,txtFirstName,txtLastName,btnSubmit;
	public WebDriver driver;
	String total;
	
	 @BeforeTest
  public void setup() throws InterruptedException {
		 
	  System.setProperty("webdriver.chrome.driver",  "C:/Users/Administrator/Documents/chromedriver.exe");

    	driver = new ChromeDriver();
    	 driver.navigate().to( "https://demostore.x-cart.com");
		String webTitle = driver.getTitle();
		Assert.assertEquals(webTitle,"X-Cart Demo store company > Catalog","There is something wrong!!");
		System.out.println("X-Cart website is opened Successfully");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//login
		driver.findElement(By.xpath("//div[@class='header_bar-sign_in']//button[@type='button']")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("login-email")).sendKeys("ggg@gmail.com");
		driver.findElement(By.id("login-password")).sendKeys("123456");
		driver.findElement(By.xpath("//table[@class='login-form']//button[@type='submit']")).click();
		
		 
  }
	

  @Test(priority=1)
  public void SelectApparel() throws InterruptedException {
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//ul[@class='nav navbar-nav top-main-menu']//span[@class='primary-title'][contains(text(),'Catalog')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//li[@class='leaf has-sub']//ul//li[@class='leaf']//a[@href='apparel']//span[contains(text(),'Apparel')]")).click();
	  Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'items-list items-list-products category-products filtered-products')]//ul[contains(@class,'products-grid grid-list')]//li[1]//div[1]//div[1]//a[1]//img[1]"))).build().perform();

			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[contains(@class,'ui-draggable compare')]//div[contains(@class,'add-to-cart-button')]//button[contains(@type,'button')]")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//div[@id='ui-id-3']//a[@class='regular-main-button checkout']")).click();

  }

  @Test(priority=2)
  void shippingAddress() throws InterruptedException {
  driver.findElement(By.name("shippingAddress[firstname]")).sendKeys("Jean");
  	 driver.findElement(By.name("shippingAddress[lastname]")).sendKeys("Doe");
  	
  	
  	driver.findElement(By.id("shippingaddress-street")).sendKeys("700 sherbrooke");
  	
  	driver.findElement(By.id("shippingaddress-city")).clear();
  	driver.findElement(By.id("shippingaddress-city")).sendKeys("montreal");
  	
	Select selectCountry = new Select(driver.findElement(By.id("shippingaddress-country-code")));
  	selectCountry.selectByVisibleText("Canada");
  	
  	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
  	
  	Select selectState = new Select(driver.findElement(By.id("shippingaddress-state-id")));
  	selectState.selectByVisibleText("Quebec");
  	
  	driver.findElement(By.id("shippingaddress-zipcode")).clear();
  	driver.findElement(By.id("shippingaddress-zipcode")).sendKeys("h4g1r5");
  	driver.findElement(By.id("email")).clear();
  	driver.findElement(By.id("email")).sendKeys("p.michael86@gmail.com" + "\n");
   total =driver.findElement(By.className("order-total")).getText();
   System.out.println("total amount : ");
  	System.out.println(total);
	Thread.sleep(3000);
	
  }
  
  
  @Test(priority=3)
  	void payment() throws InterruptedException {
	
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");

  
	  Thread.sleep(3000);
	  Actions action = new Actions(driver);
		Thread.sleep(3000);
		 action.moveToElement(driver.findElement(By.xpath("//button[contains(text(),'Choose shipping')]"))).click().perform();
	  Thread.sleep(3000);
	  String newTotal = driver.findElement(By.className("order-total")).getText();
	  System.out.println("payment total amount:");
	  System.out.println(newTotal);
	  Assert.assertEquals(newTotal,total,"total amount is validated!");
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//button[contains(text(),'Proceed to payment')]")).click();
	  Thread.sleep(3000);
	  driver.findElement(By.xpath("//input[@id='pmethod25']")).click();
	  Thread.sleep(8000);
	  driver.findElement(By.xpath("//button[@title='Click to finish your order']")).click();
	  
  		
  }
  @AfterTest
	void logout() throws InterruptedException {
	  Thread.sleep(8000);
	  Actions action = new Actions(driver);
		Thread.sleep(3000);
		 action.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'My account')]"))).click().perform();
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//*[@id='header-bar']/div[3]/ul/li[7]/a")).click();
		 System.out.println("Logout Successfull!!");
		 
  }
 
 
}
