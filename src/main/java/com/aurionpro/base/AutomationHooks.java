package com.aurionpro.base;

import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;

/// This is automation

public class AutomationHooks {
	
	public static WebDriver driver;
	
	@After
	public void endScenario()
	{
		if (AutomationHooks.driver!= null)
		{
			AutomationHooks.driver.quit();
		}
	}

}
