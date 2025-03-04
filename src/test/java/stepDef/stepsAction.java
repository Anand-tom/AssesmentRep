package stepDef;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
 
import hooks.preTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 

public class stepsAction {
	
WebDriver driver = preTest.driver;
	
	@Given("Load Login page")
	public void load_sign_in_page() {
	    
	    driver.get("https://o2.openmrs.org/openmrs/login.htm");
		driver.manage().window().maximize();
	    
	}
 
	
	@When("Enter Valid username and password")
	public void login() {
	    // Write code here that turns the phrase above into concrete actions
	    driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("Admin123");
		driver.findElement(By.xpath("//li[@id='Inpatient Ward']")).click();
		driver.findElement(By.id("loginButton")).click();
	}
	
	@Then("User should be succesfully logged into home page")
	public void home() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
		
		WebElement logoutbutton = driver.findElement(By.xpath("//*[@id=\"navbarSupportedContent\"]/ul/li[3]/a"));
 
	Assert.assertEquals(logoutbutton.getText(), "Logout");
		
	}
	
	@When("Click on Register a patient tile")
	public void register() {
	    driver.findElement(By.xpath("//a[@id='referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension']")).click();
	}
	@When("Enter demographics details")
	public void demographics() {
	    driver.findElement(By.xpath("//input[@name='givenName']")).sendKeys("Anu");
	    driver.findElement(By.xpath("//input[@name='familyName']")).sendKeys("Anu");
	    driver.findElement(By.xpath("//button[@id='next-button']")).click();
	    
	    WebElement gender = driver.findElement(By.id("gender-field"));
	    Select obj = new Select(gender);
	    obj.selectByVisibleText("Female");
	    driver.findElement(By.xpath("//button[@id='next-button']")).click();
	    driver.findElement(By.xpath("//input[@id='birthdateDay-field']")).sendKeys("21");
	    WebElement month = driver.findElement(By.id("birthdateMonth-field"));
	    Select mon = new Select(month);
	    mon.selectByVisibleText("June");
	    driver.findElement(By.xpath("//input[@id='birthdateYear-field']")).sendKeys("1995");
	    driver.findElement(By.xpath("//button[@id='next-button']")).click();
	    
	}
	@When("Enter contactinformation")
	public void contact() {
		 driver.findElement(By.xpath("//input[@id=\"address1\"]")).sendKeys("pala");
		 driver.findElement(By.xpath("//button[@id='next-button']")).click();
		 driver.findElement(By.xpath("//button[@id='next-button']")).click();
	}
	@When("Enter retaionship details")
	public void redetails() {
		driver.findElement(By.xpath("//button[@id='next-button']")).click();
	}
 
 
	@Then("Clicks on confirm button Patient should be register successfully")
	public void patient_register() {
		driver.findElement(By.id("submit")).click();
		
		WebDriverWait wdw=new WebDriverWait(driver,Duration.ofSeconds(20));
		wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"patient-header row \"]/div[2]/div/span")));
				
				boolean patientId =  driver.findElement(By.xpath("//*[@class=\"patient-header row \"]/div[2]/div/span")).isDisplayed();
				Assert.assertTrue("Patient Id should generate ",
						patientId);
 
	}
 
	@When("Click on Find Patient Record")
	public void patientRecord() {
		driver.findElement(By.xpath("//a[@id='coreapps-activeVisitsHomepageLink-coreapps-activeVisitsHomepageLink-extension']")).click();
	}
	@When("Enter search by patient name")
	public void patientName() {
	    driver.findElement(By.xpath("//input[@placeholder='Search by ID or Name']")).sendKeys("Anuja");
	}
	@When("Select search patient")
	public void patientSearch() throws InterruptedException {
		WebElement moveMouse=driver.findElement(By.xpath("//*[@id='patient-search-results-table_wrapper']/table/tbody/tr[1]"));
		Actions ac=new Actions(driver);
		ac.moveToElement(moveMouse).click().perform();
		
 
	}
	@When("Click on edit")
	public void edit() {
		
		driver.findElement(By.xpath("//span[@id='edit-patient-demographics']")).click();
	}
	@When("Update patient name and save")
	public void name() {
	    driver.findElement(By.xpath("//input[@name='givenName']")).clear();
	    driver.findElement(By.xpath("//input[@name='givenName']")).sendKeys("AnujaAnu");
	    driver.findElement(By.xpath("//a[@id='save-form']")).click();
	}
	@Then("Clicks on confirm and patient name should be update successfully")
	public void patientsuccess() {
		driver.findElement(By.xpath("//button[@id='registration-submit']")).click();
		WebDriverWait wdw=new WebDriverWait(driver,Duration.ofSeconds(20));
		wdw.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='PersonName-givenName']")));
				
				WebElement actual_updated_name =  driver.findElement(By.xpath("//span[@class='PersonName-givenName']"));
				Assert.assertEquals(actual_updated_name.getText(), "AnujaAnu");
	}

}
