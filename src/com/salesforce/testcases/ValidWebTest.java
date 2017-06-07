package com.salesforce.testcases;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.salesforce.base.Page;
import com.salesforce.pages.login.ValidWebPage;
import com.salesforce.util.TestUtil;

public class ValidWebTest {

	@Test(dataProvider="getData")
	public void DomainURLTest(Hashtable<String,String> data) throws InterruptedException{	
		// check the runmode of test case
		if(!TestUtil.isExecutable("ValidWebsiteTest",Page.xls1))
		 throw new SkipException("Runmode set to NO");
		
		// check runmode for data set
		if(!data.get("Runmode").equals("Y"))
			 throw new SkipException("Runmode set to NO for the data set");

		System.out.println(data.get("Runmode")+" -- "+data.get("Username") +" -- "+data.get("Password"));
		ValidWebPage validWebPage = new ValidWebPage();
		validWebPage.doLogin(data.get("Username"), data.get("Password"));	

	}
	
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("ValidWebsiteTest", Page.xls1);
	}
	
}
