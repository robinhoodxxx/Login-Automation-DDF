package com.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginElements  {

	WebDriver driver;
	
	public LoginElements(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="username")
	private WebElement Username;
	
	
	
	@FindBy(id="password")
	private WebElement Pass;
	
	@FindBy(xpath="(//span[@class='checkmark'])[1]")
	private WebElement admin;
	
	@FindBy(xpath="(//span[@class='checkmark'])[2]")
	private WebElement user;
	
	
	
	@FindBy(id="terms")
	private WebElement AcceptTerms;
	
	@FindBy(id="signInBtn")
	private WebElement signin;
	
	
	@FindBy(xpath="//div[@class='alert alert-danger col-md-12']")
	private WebElement incorrect;
	
	@FindBy(id="okayBtn")
	private WebElement Ok;
	
	@FindBy(id="cancelBtn")
	private WebElement cancel;
	
	
	
	public WebElement clickok() {
		return Ok;
	}
	
	public WebElement clickcancel() {
		return cancel;
	}
	
	
	public void EnterUsername(String user) {
		
		try {
			
			Username.sendKeys(user);
		}
		catch(Exception e) {
			System.out.println("Exception "+e);
		}
	}
	
	public void EnterPassword(String pass) {
		
      try {
			
    	  Pass.sendKeys(pass);
		}
		catch(Exception e) {
			
			System.out.println("Exception "+e);
		}
	}
	
	public void Selectusertype(String text) {
		 try {
				
			 if(text.equalsIgnoreCase("user")) {
				 if(!user.isSelected()) {
					 user.click();
				 }
				 
			 }else {
				 if(!admin.isSelected()) {
					 admin.click();
				 }
			 }
			}
			catch(Exception e) {
				System.out.println("Exception "+e);
			}
	}
	
	
	public void AcceptTerms() {
		 try {
				
			 AcceptTerms.click();
			}
			catch(Exception e) {
				System.out.println("Exception "+e);
			}
	}
	
	public void ClickSignin() {
		 try {
				
			 signin.click();
			}
			catch(Exception e) {
				System.out.println("Exception "+e);
			}
		
		
	}
	
   public WebElement Incorrectdisplayed() {
	   
	   return incorrect;
   }
	
	
	
	
}
