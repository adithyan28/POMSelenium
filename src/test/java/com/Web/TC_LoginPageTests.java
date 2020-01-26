package com.Web;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_LoginPageTests {
 WebDriver driver;
 WebDriverObject driverObj=new WebDriverObject();
 FileManager fileManager=new FileManager();
	
 
 @BeforeClass
 void setup() throws IOException, InterruptedException {
	 String browser=fileManager.pReader("browser");
	 driver=driverObj.setup(browser);
	 driver.get(fileManager.pReader("URL"));
 }
 @Test
 void validateLoginError() throws InterruptedException, IOException {
	 LoginPage login=new LoginPage(driver);
	 login.loginApplication(fileManager.pReader("username"), fileManager.pReader("passwordDev"));
	 assertEquals(login.errorPassword.isDisplayed(), true);
 
 }
 @Test (dependsOnMethods="validateLoginError")
 void validateLoginFeature() throws InterruptedException, IOException {
	 LoginPage login=new LoginPage(driver);
	 HomePage home=new HomePage(driver);
	 login.loginApplication(fileManager.pReader("username"), fileManager.pReader("passwordLinkdIn"));
	 assertEquals(home.tabHome.isDisplayed(), true);
 }
 @Test (dependsOnMethods="validateLoginFeature")
 void validateLogOut() {
	 HomePage home=new HomePage(driver);
	 home.dropDownProfile.click();
	 home.linkLogout.click();
	 boolean present=home.linkSignIn.isDisplayed();
	 assertEquals(present, true);
 }
	
}
