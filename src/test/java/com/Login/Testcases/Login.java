package com.Login.Testcases;


import com.pagesObjects.LoginElements;
import com.utils.Baseclass;

public class Login extends Baseclass{
	
	
	
	
	public  void login(String username,String password,String logtype) {
		
		
        LoginElements le=new LoginElements(driver);
		
		
		le.EnterUsername(username);
			
		le.EnterPassword(password);
		le.Selectusertype(logtype);
	
		if(logtype.equalsIgnoreCase("user")) {
			
			ClickAnyElement(le.clickok());
		}
		
		le.AcceptTerms();
		le.ClickSignin();

		
			}
	
	
	

}
