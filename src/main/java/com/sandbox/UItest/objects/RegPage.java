package com.sandbox.UItest.objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegPage {
	WebDriver driver;
	Map<String, String> xPaths;
	
	private static final String CREATEACC_URL = "CREATEACC_URL";
	private static final String CREATEACC_EMAIL = "CREATEACC_EMAIL";
	private static final String CREATEACC_USER = "CREATEACC_USER";
	private static final String CREATEACC_PASS = "CREATEACC_PASS";
	private static final String CREATEACC_CONFPASS = "CREATEACC_CONFPASS";
	private static final String CREATEACC_SUBMIT = "CREATEACC_SUBMIT";
	private static final String CREATEACC_PASSNOTMATCH = "CREATEACC_PASSNOTMATCH";
	private static final String CREATEACC_INVALPASS = "CREATEACC_INVALPASS";
	private static final String CREATACC_INVALUSER = "CREATACC_INVALUSER";
	private static final String CREATEACC_INVALEMAIL = "CREATEACC_INVALEMAIL";

	public RegPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void openCreateAccURL() {
		driver.get(xPaths.get(CREATEACC_URL));
	}

	public void inputEmail(String key) {
		driver.findElement(By.xpath(xPaths.get(CREATEACC_EMAIL))).sendKeys(key);
	}

	public void inputUser(String key) {
		driver.findElement(By.xpath(xPaths.get(CREATEACC_USER))).sendKeys(key);
	}

	public void inputPass(String key) {
		driver.findElement(By.xpath(xPaths.get(CREATEACC_PASS))).sendKeys(key);
	}
	
	public String getPassValue() {
		String passVal = driver.findElement(By.xpath(xPaths.get(CREATEACC_PASS))).getAttribute("value");
		return passVal;
	}

	public void inputConfPass(String key) {
		driver.findElement(By.xpath(xPaths.get(CREATEACC_CONFPASS))).sendKeys(key);
	}
	
	public String getConfPassValue() {
		String confPassVal = driver.findElement(By.xpath(xPaths.get(CREATEACC_CONFPASS))).getAttribute("value");
		return confPassVal;
	}

	
	public WebElement getPassNotMatchMsg() {
		WebElement passNotMatchMsg = driver.findElement(By.xpath(xPaths.get(CREATEACC_PASSNOTMATCH)));
		return passNotMatchMsg;
	}
	
	public WebElement getInvalPassMsg() {
		WebElement invalPassMsg = driver.findElement(By.xpath(xPaths.get(CREATEACC_INVALPASS)));
		return invalPassMsg;
	}
	
	public WebElement getInvalUserMsg() {
		WebElement invalUserMsg = driver.findElement(By.xpath(xPaths.get(CREATACC_INVALUSER)));
		return invalUserMsg;
	}
	
	public WebElement getInvalEmailMsg() {
		WebElement invalEmailMsg = driver.findElement(By.xpath(xPaths.get(CREATEACC_INVALEMAIL)));
		return invalEmailMsg;
	}
	
	
	public void clickSubmit() {
		driver.findElement(By.xpath(xPaths.get(CREATEACC_SUBMIT))).click();
	}
	
	

}
