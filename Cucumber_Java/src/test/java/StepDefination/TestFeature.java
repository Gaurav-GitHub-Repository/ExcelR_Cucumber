package StepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestFeature extends Utility{

	WebDriver driver;
	
	@Given("User is on login page")
	public void user_is_on_login_page() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://elearning.excelr.com/");
		
		Thread.sleep(1000);
		WebElement SignIn = driver.findElement(By.linkText("Sign In"));
		SignIn.click();  	
	}
	
	@When("User enters username and password")
	public void user_enters_username_and_password() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		WebElement EmailAddress = driver.findElement(By.xpath("//*[@id='login-email']"));
		WebElement Pwd = driver.findElement(By.xpath("//*[@id='login-password']"));
		
		Thread.sleep(1000);
		EmailAddress.sendKeys("gaurav.pathangej@gmail.com");
		
		Thread.sleep(1000);
		Pwd.sendKeys("GauravExcelr");
	}
	
	@When("Clicks on login button")
	public void clicks_on_login_button() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		WebElement Login_Button = driver.findElement(By.xpath("//button[text()='Sign in']"));
		Thread.sleep(1000);
		Login_Button.click();
	}
	
	@When("User is navigated to dashboard")
	public void user_is_navigated_to_dashboard() {
	    // Write code here that turns the phrase above into concrete actions
		String actualtitle = driver.getTitle();
		String expectedtitle = "Sign in or Register | ExcelR Solutions";
		Assert.assertEquals(actualtitle, expectedtitle);
	}
	
	@When("User clicks on update profile button in dashboard")
	public void user_clicks_on_update_profile_button_in_dashboard() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		By button = By.xpath("//div[@class='setting_section']/ul/li/a/i[@class='fa fa-pencil-square-o fa-2x']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.presenceOfElementLocated(button));
		driver.findElement(button).click();   
    
		WebElement upload = driver.findElement(By.xpath("//form[@id='profile_form']/div/div/div/div/div/div/div/div/div[@class='file-default-preview clickable']"));
    try {
		Thread.sleep(1000);
		upload.click();
	} catch (NoSuchElementException e) {
		// TODO Auto-generated catch block
		throw e;
	}
    
	}

	@When("User uploads picture")
	public void user_uploads_picture() {
	    // Write code here that turns the phrase above into concrete actions
		
		try {
			uploadfile();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@When("User enters details")
	public void user_enters_details() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	
	 int flag=0;	
	 
	 if(flag==0)
	 {
	 WebElement Phone_Number = driver.findElement(By.xpath("//*[@id='phone_number']"));
	 Phone_Number.clear();
     Phone_Number.sendKeys("8919876100");
     WebElement Headline = driver.findElement(By.xpath("//*[@id='headline']"));
     Headline.clear();
	 Headline.sendKeys("Hi. My name is Gaurav!");
	 }
	 else
	 {
		 System.out.println("The phone number you entered is not valid. Please enter the correct number along with your area code.");
	 }
	 
	  WebElement UpdateProfile = driver.findElement(By.xpath("//div[@class='modal-footer']/button[@id='user_profile_save']"));
        try {
			Thread.sleep(1000);
			UpdateProfile.click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
     }

	@Then("Verify login page")
	public void verify_login_page() throws InterruptedException, IOException {
	    // Write code here that turns the phrase above into concrete actions
		
	   try {
	   Thread.sleep(5000);  
	   TakesScreenshot srceenshot = ((TakesScreenshot)driver);
	   File srcfile = srceenshot.getScreenshotAs(OutputType.FILE);
       File Destination = new File("C:\\Users\\Gaurav\\Documents\\Srceenshot\\srceenshot.png");
       FileHandler.copy(srcfile, Destination);
	   }
	   catch(InterruptedException e)
	   {
		   e.printStackTrace();  
	   }
	   
	   WebElement logout = driver.findElement(By.xpath("//div[@id='contain-all']/header/div/div[3]/nav/a[3]"));
       Thread.sleep(1000);
	   logout.click();
	}
		
		@Then("Take Screenshot of dashboard")
		public void take_screenshot_of_dashboard() throws InterruptedException, IOException {
		// TODO Auto-generated method stub
			WebElement SignIn = driver.findElement(By.linkText("Sign In"));
			Thread.sleep(1000);
			SignIn.click();
			  
			WebElement Email = driver.findElement(By.xpath("//input[@id='login-email']"));
			Thread.sleep(1000);
			Email.sendKeys("gaurav.pathangej@gmail.com");
			  
			WebElement Password = driver.findElement(By.xpath("//input[@id='login-password']"));
			Thread.sleep(1000);
		    Password.sendKeys("GauravExcelr");
			     
		    WebElement Button = driver.findElement(By.xpath("//button[text()='Sign in']"));
			Thread.sleep(1000);
			Button.click();
			try {
				Thread.sleep(5000);
				TakesScreenshot srceenshot = ((TakesScreenshot)driver);
			    File srcfile = srceenshot.getScreenshotAs(OutputType.FILE);
			    File Destination = new File("C:\\Users\\Gaurav\\Documents\\Srceenshot\\regression.png");
			    FileHandler.copy(srcfile, Destination);
			}
			catch(InterruptedException e)
			   {
				   e.printStackTrace();  
			   }		
		}
	}