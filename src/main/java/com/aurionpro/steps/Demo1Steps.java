package com.aurionpro.steps;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aurionpro.base.AutomationHooks;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Demo1Steps {
	
	
	//WebDriver driver;
	private static String alertText;
	
	@Given("I have browser with OpenEMR application")
	public void i_have_browser_with_open_emr_application() {
		
		WebDriverManager.chromedriver().setup();
		AutomationHooks.driver=new ChromeDriver();
		
		AutomationHooks.driver.manage().window().maximize();
		AutomationHooks.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));	
		AutomationHooks.driver.get("https://demo.openemr.io/b/openemr");
	   
	}
	
	@When("I Enter username as {string}")
	public void i_enter_username_as(String username) {
		
		AutomationHooks.driver.findElement(By.id("authUser")).sendKeys(username);
	}
	
	@When("I Enter password as {string}")
	public void i_enter_password_as(String password) {
		
		AutomationHooks.driver.findElement(By.cssSelector("#clearPass")).sendKeys(password);
	}
	
	
	@When("I select Language as {string}")
	public void i_select_language_as(String language) {
		Select SelLanguage = new Select(AutomationHooks.driver.findElement(By.xpath("//select[@name='languageChoice']")));
		
		SelLanguage.selectByVisibleText("Default - English (Standard)");
		
	    
	}
	
	
	@When("I click on login")
	public void i_click_on_login() {
		
		AutomationHooks.driver.findElement(By.id("login-button")).click();
	    
	}
	
	
	@Then("I should get access to Portal with title {string}")
	public void i_should_get_access_to_portal_with_title(String expectedTitle) {
		
		Assert.assertEquals(AutomationHooks.driver.getTitle(), expectedTitle);
	   
	}
	
	
	@Given("I open browser with OpenEMR application")
	public void i_open_browser_with_open_emr_application() {
	    
	}
	@Then("I should get access to Portal with Error message as  {string}")
	public void i_should_get_access_to_portal_with_error_message_as(String string) {
	    
	
	}
	
	@Then("I should not get access to Portal with Error message as  {string}")
	public void i_should_not_get_access_to_portal_with_error_message_as(String expectedError) {
	   
String actualError= AutomationHooks.driver.findElement(By.xpath("//*[contains(text(),'Invalid')]")).getText().trim();
	    
		Assert.assertTrue(actualError.contains(expectedError));  //expect true
		
	}
	
	
	@And("I click on Patient Menu")
	public void i_click_on_patient_menu() {
	   
		AutomationHooks.driver.findElement(By.xpath("//div[text()='Patient']")).click();
	}
	@And("I click on New Search menu")
	public void i_click_on_new_search_menu() {
		

		AutomationHooks.driver.findElement(By.xpath("//div[text()='New/Search']")).click();
	    
	}
	@When("I fill the who section form")
	public void i_fill_the_who_section_form(io.cucumber.datatable.DataTable dataTable) {
		
		List<Map< String, String>> ls = dataTable.asMaps();
		String firstname = ls.get(0).get("firstname");
		String lastname = ls.get(0).get("lastname");
		String DOB = ls.get(0).get("DOB");
		String gender = ls.get(0).get("gender");
		
		AutomationHooks.driver.switchTo().frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@name='pat']")));
		
		AutomationHooks.driver.findElement(By.id("form_fname")).sendKeys(firstname);
		AutomationHooks.driver.findElement(By.id("form_lname")).sendKeys(lastname);
		AutomationHooks.driver.findElement(By.id("form_DOB")).sendKeys(DOB);
		
		
		Select selGender = new Select(AutomationHooks.driver.findElement(By.id("form_sex")));
		selGender.selectByValue(gender);
		
	    
	}
	@When("I click on create new patient")
	public void i_click_on_create_new_patient() {
		
		AutomationHooks.driver.findElement(By.id("create")).click();
		AutomationHooks.driver.switchTo().defaultContent();
		
	}
	@When("I click on confirm create new patient")
	public void i_click_on_confirm_create_new_patient()  {
	
		
		
		AutomationHooks.driver.switchTo().frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@id='modalframe']")));
		AutomationHooks.driver.findElement(By.xpath("//input[@value='Confirm Create New Patient']")).click();
		AutomationHooks.driver.switchTo().defaultContent();
	}
	@When("I store the alert text and handle it")
	public void i_store_the_alert_text_and_handle_it() {
		
		
		WebDriverWait wait = new WebDriverWait(AutomationHooks.driver,Duration.ofSeconds(25));
		wait.until(ExpectedConditions.alertIsPresent());
		alertText = AutomationHooks.driver.switchTo().alert().getText();
		AutomationHooks.driver.switchTo().alert().accept();
	
	   
	}
	@When("I close the happy birthday if available")
	public void i_close_the_happy_birthday_if_available() {
		if (AutomationHooks.driver.findElements(By.xpath("//div[@class='closeDlgIframe']")).size()>0)
		{
			AutomationHooks.driver.findElement(By.xpath("//div[@class='closeDlgIframe']")).click();
		}
		
	    
	}
	@Then("I should verify the alert text contains {string}")
	public void i_should_verify_the_alert_text_contains(String expectedAlertText) {
		Assert.assertTrue(alertText.contains(expectedAlertText));
	   
	}
	@Then("I should get the added patient details as {string}")
	public void i_should_get_the_added_patient_details_as(String expectedText) {
		
		AutomationHooks.driver.switchTo().frame(AutomationHooks.driver.findElement(By.xpath("//iframe[@name= 'pat']")));
		String actualText = AutomationHooks.driver.findElement(By.xpath("//h2[contains(text(),'Medical Record Dashboard')]")).getText().trim();
	    Assert.assertEquals(actualText, expectedText);
	}

	
	
	
	
	
	

}
