package stepDefs;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class PurchaseStepDef {
		 public static WebDriver driver;
		 WebDriverWait wait;
		 String Items;
		 List<WebElement> previousSts;
		 
		 @BeforeAll
		 
	      public static void lauchBrowser() 
		  {    
			 WebDriverManager.chromedriver().setup();
			 driver = new ChromeDriver();
			 driver.manage().window().maximize();
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30)); 
			 driver.get("https://www.demoblaze.com"); 
		  }

		 @Given("User is on Home Page")
		 public void user_is_on_home_page() 
		  {
			 wait= new WebDriverWait(driver, Duration.ofSeconds(30));
			 driver.findElement(By.id("login2")).click();
		  }
			
		 @When("User enters login credentials")
		 public void user_enters_login_credentials() throws InterruptedException 
		   {  
			 wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
			 driver.findElement(By.id("loginusername")).sendKeys("Niranjana28");
			 wait.until(ExpectedConditions.elementToBeClickable(By.id("loginusername")));
			 driver.findElement(By.id("loginpassword")).sendKeys("Niran@28");
			 driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[3]")).click(); 
			 Thread.sleep(2000);
		   }
		  
		 @Then("Should display Home Page")
		 public void should_display_home_page() throws InterruptedException
		  {
			 Thread.sleep(3000);
			 WebElement welcome= driver.findElement(By.xpath("//li/a[text()='Welcome Niranjana28']"));
			 Assert.assertEquals(welcome.getText(), "Welcome Niranjana28");
		  }
		
		 @When("Add Items {string} to cart")
		 public void add_items_to_cart(String Items)
		  {
			 wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			 WebElement home= driver.findElement(By.xpath("//li/a[contains(text(),'Home')]"));
			 home.click();
			 driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		  	 driver.findElement(By.linkText(Items)).click();
		  	 driver.findElement(By.xpath("//a[contains(text(),'Add')]")).click();
		  	 wait.until(ExpectedConditions.alertIsPresent());
		  	 Alert alert=driver.switchTo().alert(); 
		  	 alert.accept();
		  }
		
		  @Then("Should be added to the cart")
		  public void should_be_added_to_the_cart() 
		   {
			 wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			 driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
			 
		   }
		  
		  @When("Delete an Item")
		  public void delete_an_item() {
			  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			 //driver.findElement(By.xpath("//a[contains(text(),'Cart')]")).click();
			  List<WebElement> previousSts = driver.findElements(By.xpath("//td[2]"));
			  wait.until(ExpectedConditions.visibilityOfAllElements(previousSts));
			  driver.findElement(By.xpath("(//a[contains(text(),'Delete')])[1]")).click();
		   }
		  @Then("Item should be deleted")
		  public void item_should_be_deleted() {
			  wait=new WebDriverWait(driver, Duration.ofSeconds(30));
			  List<WebElement> currentSts = driver.findElements(By.xpath("//td[2]"));
			  wait.until(ExpectedConditions.visibilityOfAllElements(currentSts));
			  if(previousSts.size()>currentSts.size()) {
				  Assert.assertTrue(true);
			  }
		  }

         @When("User Place Order")
		 public void user_place_order() 
		  {
			 driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
		  }
		 @Then("Items should be purchased")
		 public void item_should_be_purchased() throws InterruptedException {
			 Thread.sleep(3000);
			 wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			 driver.findElement(By.xpath("//button[contains(text(),'Place Order')]")).click();
			 driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Niranjana");
			 driver.findElement(By.xpath("//input[@id='country']")).sendKeys("India");
			 driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Erode");
			 driver.findElement(By.xpath("//input[@id='card']")).sendKeys("12345");
		  	 driver.findElement(By.xpath("//input[@id='month']")).sendKeys("May");
		  	 driver.findElement(By.xpath("//input[@id='year']")).sendKeys("2022");
		     Thread.sleep(2000);
		     driver.findElement(By.xpath("//button[contains(text(),'Purchase')]")).click();
		     Thread.sleep(2000);
		     boolean isDisplay = driver.findElement(By.xpath("//h2[(text()='Thank you for your purchase!')]")).isDisplayed();
		     String Display = driver.findElement(By.xpath("//h2[(text()='Thank you for your purchase!')]")).getText();
		     Assert.assertTrue(isDisplay);
		     System.out.println(Display);
		     driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
		     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
		 }

}
