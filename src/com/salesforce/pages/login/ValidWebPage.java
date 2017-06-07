package com.salesforce.pages.login;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.salesforce.base.Page;

public class ValidWebPage extends Page{
	String actWebsite="";
	
	public void doLogin(String userName,String password) throws InterruptedException{
	
		driver.get(CONFIG.getProperty("testSalesForcePageURL"));	
		input("USERNAME",userName);
		Thread.sleep(5000);
		input("PASSWORD",password);
		Thread.sleep(5000);
		click("LOGIN_BUTTON");
		driver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
		
		String actUser = driver.findElement(By.xpath("//div[@id='userNavButton']/span")).getText();
		Thread.sleep(5000);
		System.out.println("Logged in User "+actUser);
		String user = "karan raman";
		driver.findElement(By.id("userNavLabel")).click();
		
		String lighting = driver.findElement(By.xpath("//div[@id='userNav-menuItems']/a[4]")).getText();
		System.out.println("Value of calssic :"+lighting);
		if(!lighting.equals("Switch to Lightning Experience"))
		{
			driver.findElement(By.linkText("Switch to Salesforce Classic")).click();
		}
		String service="";
	    service = driver.findElement(By.id("tsidLabel")).getText();
	    System.out.println("Service displayed "+service);
	    
		if(!service.equals("Sales"))
		{
			driver.findElement(By.linkText("Sales")).click();
		}
	    	
	    driver.findElement(By.linkText("Accounts")).click();	     
	    WebElement element1=driver.findElement(By.xpath("//div[2]/form/div/span/span[1]/select"));
	    element1.click();
	    element1.sendKeys("All Account");
	    driver.findElement(By.name("go")).click();
	    driver.findElement(By.xpath("//div[@id='0017F000005lUUz_Name']/a/span")).click();
	    String expWebsite = CONFIG.getProperty("expWebSite");
	    actWebsite= driver.findElement(By.xpath("//div[2]/div[2]/table/tbody/tr[4]/td[4]/div/a")).getText();
	    System.out.println("Actual Website"+actWebsite+"Expected Website :"+expWebsite);
	    Assert.assertEquals(actWebsite, expWebsite);

	}
}
