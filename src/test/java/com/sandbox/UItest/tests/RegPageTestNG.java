package com.sandbox.UItest.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sandbox.UItest.objects.LogInPage;
import com.sandbox.UItest.objects.RegPage;
import utils.ReadExcel;
import utils.ReadFile;
public class RegPageTestNG {
	String driverPath = "D:\\Installers\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	public RegPage createAcc; 
	public LogInPage login = new LogInPage(driver, ReadFile.readXPaths());

	@BeforeMethod
	public void openBrowserCreateAcc() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		ReadExcel.setExcell("users.xlsx");
		ReadExcel.setWorkSheet(0);
	}

	@Test (priority = 1, enabled = true)
	public void regUsers() {
		SoftAssert sa = new SoftAssert();
		login.logIn(driver);		
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		createAcc.openCreateAccURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		for (int i = 0; i < ReadExcel.getRowNumber(); i++) {
			createAcc.inputEmail(ReadExcel.getDataAt(i, 0));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			createAcc.inputUser(ReadExcel.getDataAt(i, 1));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			createAcc.inputPass(ReadExcel.getDataAt(i, 2));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			createAcc.inputConfPass(ReadExcel.getDataAt(i, 2));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			String password = createAcc.getPassValue();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			String confPassword = createAcc.getConfPassValue();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			sa.assertEquals(confPassword, password, "Passwords do not match");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			createAcc.clickSubmit();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			ReadExcel.setDataAt(i, 3, "User registered!");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			createAcc.openCreateAccURL();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		}
	}

	@Test (priority = 2, enabled = true)
	public void checkIfUserReg() {
		SoftAssert sa = new SoftAssert();
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		for (int i = 0; i < ReadExcel.getRowNumber(); i++) {
			login.openLogInPage(driver);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			login.inputUserName(driver, ReadExcel.getDataAt(i, 1));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			login.inputPass(driver, ReadExcel.getDataAt(i, 2));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			login.clickLogInBtn(driver);

			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			String currUrl = driver.getCurrentUrl();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			String succUrl = ReadFile.readXPaths().get("SUCCESSLOGIN_URL");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			sa.assertEquals(currUrl, succUrl);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			sa.assertAll();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			ReadExcel.setDataAt(i, 4, "Login confirmed");
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			login.logout(driver);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		}
	}

	@Test (priority = 3, enabled = true)
	public void invalidRegNoEmail() {
		SoftAssert sa = new SoftAssert();
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		login.logIn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.openCreateAccURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputUser(ReadFile.readXPaths().get("EXAMPLE_USERNAME"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputConfPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.clickSubmit();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertTrue(createAcc.getInvalEmailMsg().isDisplayed());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();
	}

	@Test (priority = 4, enabled = true)
	public void invalidRegNoUserName() {
		SoftAssert sa = new SoftAssert();
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		login.logIn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.openCreateAccURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputEmail(ReadFile.readXPaths().get("EXAMPLE_EMAIL"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputConfPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.clickSubmit();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertTrue(createAcc.getInvalUserMsg().isDisplayed());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();
	}

	@Test (priority = 5, enabled = true)
	public void invalidRegNoPass() {
		SoftAssert sa = new SoftAssert();
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		login.logIn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.openCreateAccURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputEmail(ReadFile.readXPaths().get("EXAMPLE_EMAIL"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputUser(ReadFile.readXPaths().get("EXAMPLE_USERNAME"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputConfPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.clickSubmit();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertTrue(createAcc.getInvalPassMsg().isDisplayed());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();
	}

	@Test (priority = 6,/* groups = {"unit"},*/ enabled = true)
	public void invalidConfPass() {
		SoftAssert sa = new SoftAssert();
		createAcc = new RegPage(driver, ReadFile.readXPaths());
		login.logIn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.openCreateAccURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputEmail(ReadFile.readXPaths().get("EXAMPLE_EMAIL"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputUser(ReadFile.readXPaths().get("EXAMPLE_USERNAME"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.inputPass(ReadFile.readXPaths().get("EXAMPLE_PASS"));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		createAcc.clickSubmit();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		
		sa.assertTrue(createAcc.getPassNotMatchMsg().isDisplayed());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();
	}

	@AfterMethod
	public void closeBrowser() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.quit();
	}

}
