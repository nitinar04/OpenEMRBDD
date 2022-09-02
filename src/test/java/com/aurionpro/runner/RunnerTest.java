package com.aurionpro.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


//@CucumberOptions(features = {"src/test/resources/Feature/Login.feature"}, 

@CucumberOptions(features = {"src/test/resources/Feature"}, 

glue = {"com.aurionpro.steps","com.aurionpro.base"}, 
monochrome = true, publish = true,
plugin = {"html:target/cucumber-report.html"},
dryRun = false,
tags = "@addpatient"



		
		)

public class RunnerTest extends AbstractTestNGCucumberTests
{
	
	
	

}
