package com.Login.Testcases;


import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.utils.Baseclass;
import com.utils.ExcelDataReader;

public class ValidLogin extends Baseclass{
	
	
	@DataProvider(name = "logindata")
    public Object[][] loginData() throws IOException {
    	String filelocation = System.getProperty("user.dir")+"//Config//Valid.xlsx";
    	//ExcelDataReader excelDataReader = new ExcelDataReader();
    	//System.out.println("data pro");
    	FileExists(filelocation);

        return ExcelDataReader.readExcelData(filelocation, "Sheet1");
    }
	
	
	@Test(dataProvider = "logindata")
	public void PerformLogin(String username,String password,String logtype) {
		
		//LoginElements le=new LoginElements(driver);
		System.out.println(username+" "+password+" "+logtype);
		Login ln=new Login();
		ln.login(username, password, logtype);
		Wait();

		
		String Expectedurl = "https://rahulshettyacademy.com/angularpractice/shop";
		String currenturl=driver.getCurrentUrl();
		
		
     if(Expectedurl.equalsIgnoreCase(currenturl)) {
    	 Assert.assertTrue(true);
    	 System.out.println("-----"+driver.getTitle()+"-----");
    	 System.out.println("----Valid Login scenario is passed-----");

     }
     else {
    	 System.out.println("-----Valid Login scenario is failed------");
    	 System.out.println("user: "+username+" pass: "+password);

    	 Assert.assertTrue(false);

     }
		
		 System.out.println();
		
		
		

        // Assert that the Bootstrap element is displayed
		
		
		

	}

}
