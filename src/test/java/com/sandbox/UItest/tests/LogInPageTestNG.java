package com.sandbox.UItest.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sandbox.UItest.objects.LogInPage;
import utils.ReadFile;

public class LogInPageTestNG {
	String driverPath = "D:\\Installers\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	LogInPage login;
	
	@BeforeMethod
	public void launchBrowser() {
		System.setProperty( "webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login = new LogInPage(driver, ReadFile.readXPaths());
		login.openLogInPage(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}
	
	
	@Test(priority= 1)
	public void logInKnownUser() {
		SoftAssert sa = new SoftAssert();
		
		login.inputUserName(driver, ReadFile.readXPaths().get("USERNAME"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login.inputPass(driver, ReadFile.readXPaths().get("PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login.clickLogInBtn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		String signInURLok= driver.getCurrentUrl();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		String signInURLnotOk = ReadFile.readXPaths().get("LOGIN_URLERROR");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertNotEquals(signInURLok, signInURLnotOk);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();	  
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}
	
	@Test(priority= 2)
	public void failLogInUnknownUser() {
		SoftAssert sa = new SoftAssert();
		
		login.inputUserName(driver, "MisaMisicMisa");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login.inputPass(driver, "Misa.tttrrr123");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login.clickLogInBtn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		String signInURLnotOk= driver.getCurrentUrl();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		String signInURLerror = ReadFile.readXPaths().get("LOGIN_URLERROR");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertEquals(signInURLnotOk, signInURLerror);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();
	}
	
	
	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}
	
	
}
