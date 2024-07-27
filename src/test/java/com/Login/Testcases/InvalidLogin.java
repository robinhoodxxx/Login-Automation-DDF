package com.Login.Testcases;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pagesObjects.LoginElements;
import com.utils.Baseclass;
import com.utils.ExcelDataReader;



public class InvalidLogin extends Baseclass{
	
	
	
	  @DataProvider(name = "logindata")
	    public Object[][] loginData() throws IOException {
	    	String filelocation = System.getProperty("user.dir")+"//Config//invalid.xlsx";
	    //	ExcelDataReader excelDataReader = new ExcelDataReader();
	    	//System.out.println("data pro");
	    	FileExists(filelocation);
	        return ExcelDataReader.readExcelData(filelocation, "Sheet1");
	    }
	
	
	@Test(dataProvider = "logindata")
	public void PerformLogin(String username,String password,String logtype) {
		//String logtype="user";
		System.out.println(username+" "+password+" "+logtype);

		LoginElements le=new LoginElements(driver);
		Login ln=new Login();
		ln.login(username, password, logtype);
		
		try {
			WaitTillElementisVisible(le.Incorrectdisplayed());
			boolean visible =le.Incorrectdisplayed().isDisplayed();
			if(visible) {
				
				System.out.println("-------"+le.Incorrectdisplayed().getText()+"-------");

				Assert.assertTrue(true);
		    	 System.out.println("----- InValid Login scenario is passed -----");

			}
			else {
				Assert.assertTrue(false);
		    	 System.out.println("----- InValid Login scenario is failed -----");
		    	 System.out.println("user: "+username+" pass: "+password);



			}

			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		

        // Assert that the Bootstrap element is displayed
		
		
	}



}
