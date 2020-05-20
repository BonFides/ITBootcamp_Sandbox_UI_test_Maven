package com.sandbox.UItest.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sandbox.UItest.objects.LogInPage;
import com.sandbox.UItest.objects.ProductsPage;
import utils.ReadExcel;
import utils.ReadFile;

public class ProductsPageTestNG {
	String driverPath = "D:\\Installers\\chromedriver_win32\\chromedriver.exe";
	WebDriver driver;
	ProductsPage prod;
	LogInPage login; 

	@BeforeTest
	public void openBrowserLogIn() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		login = new LogInPage(driver, ReadFile.readXPaths());
		login.logIn(driver);
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		ReadExcel.setExcell("products.xlsx");
		ReadExcel.setWorkSheet(0);
	}

	@Test(priority = 1, enabled = true)
	public void addProd() {
		SoftAssert sa = new SoftAssert();
		prod = new ProductsPage(driver, ReadFile.readXPaths());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		for (int i = 0; i < ReadExcel.getRowNumber(); i++) {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			prod.openURL();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			prod.addNewProd();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			prod.inputName(ReadExcel.getDataAt(i, 0));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			prod.inputPrice(ReadExcel.getDataAt(i, 1));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			prod.clickSubmitBtn();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			sa.assertTrue(prod.prodIsAddedMsg().isDisplayed());
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		}
		sa.assertAll();
	}

	@Test(priority = 2, enabled = true)
	public void checkIfadded() {
		SoftAssert sa = new SoftAssert();
		prod = new ProductsPage(driver, ReadFile.readXPaths());
		prod.openViewURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		for (int i = 0; i < ReadExcel.getRowNumber(); i++) {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			String prodXpath = "//*[contains(text(),'" + ReadExcel.getDataAt(i, 0) + "')]";
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			sa.assertEquals(driver.findElement(By.xpath(prodXpath)), ReadExcel.getDataAt(i, 0));
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		}
	}

	@Test(priority = 3)
	public void editProdPrice() {
		SoftAssert sa = new SoftAssert();
		prod = new ProductsPage(driver, ReadFile.readXPaths());
		prod.openEditURL();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

		Double valIncrement = 100.00;
		String editPricexPath = ReadFile.readXPaths().get("PROD_EDITPRICE");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertTrue(prod.increasePrice(valIncrement, editPricexPath));
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		prod.clickSaveChanges();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertTrue(prod.prodEditUpdateOkMsg().isDisplayed());
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		sa.assertAll();

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
