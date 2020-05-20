package com.sandbox.UItest.objects;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
	WebDriver driver;
	Map<String, String> xPaths;

	private static final String LOGIN_URL = "LOGIN_URL";
	private static final String LOGIN_USERNAME = "LOGIN_USERNAME";
	private static final String LOGIN_PASS = "LOGIN_PASS";
	private static final String LOGIN_BTN = "LOGIN_BTN";
	private static final String ACCAVATAR_INFO = "ACCAVATAR_INFO";
	private static final String ACCAVATARINFO_LOGOUT = "ACCAVATARINFO_LOGOUT";
	

	public LogInPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}

	public void openLogInPage(WebDriver driver) {
		driver.get(xPaths.get(LOGIN_URL));
	}

	public void logIn(WebDriver driver) {
		openLogInPage(driver);
		inputUserName(driver, xPaths.get("USERNAME"));
		inputPass(driver, xPaths.get("PASS"));
		clickLogInBtn(driver);
	}

	public void inputUserName(WebDriver driver, String key) {
		driver.findElement(By.xpath(xPaths.get(LOGIN_USERNAME))).sendKeys(key);
	}

	public void inputPass(WebDriver driver, String key) {
		driver.findElement(By.xpath(xPaths.get(LOGIN_PASS))).sendKeys(key);
	}

	public void clickLogInBtn(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(LOGIN_BTN))).click();
	}
	
	public void logout(WebDriver driver) {
		driver.findElement(By.xpath(xPaths.get(ACCAVATAR_INFO))).click();
		driver.findElement(By.xpath(xPaths.get(ACCAVATARINFO_LOGOUT))).click();
	}
	

}
