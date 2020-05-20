package com.sandbox.UItest.objects;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
	WebDriver driver;
	Map<String, String> xPaths;
	private static final String PROD_ADDNEWLINK = "PROD_ADDNEWLINK";
	private static final String PROD_SUBMITNEW = "PROD_SUBMITNEW";
	private static final String PROD_NAMESUBMITNEW = "PROD_NAMESUBMITNEW";
	private static final String PROD_PRICESUBMITNEW = "PROD_PRICESUBMITNEW";
	private static final String PROD_EDITPRICESAVECHANGES = "PROD_EDITPRICESAVECHANGES";
	private static final String PROD_EDITUPDATESUCCEEDED = "PROD_EDITUPDATESUCCEEDED";
	private static final String PROD_VALIDSUBMITNEW = "PROD_VALIDSUBMITNEW";
	private static final String PROD_URL = "PROD_URL";
	private static final String PROD_VIEWURL = "PROD_VIEWURL";
	private static final String PROD_EDITURL = "PROD_EDITURL";
	
	public ProductsPage(WebDriver driver, Map<String, String> xPaths) {
		this.driver = driver;
		this.xPaths = xPaths;
	}
	
	public void openURL() {
		driver.get(xPaths.get(PROD_URL));
	}
	
	public void openViewURL() {
		driver.get(xPaths.get(PROD_VIEWURL));
	}
	
	public void openEditURL() {
		driver.get(xPaths.get(PROD_EDITURL));
	}

	public void addNewProd() {
		driver.findElement(By.xpath(xPaths.get(PROD_ADDNEWLINK))).click();
	}

	public void inputName(String key) {
		driver.findElement(By.xpath(xPaths.get(PROD_NAMESUBMITNEW))).sendKeys(key);
	}

	public void inputPrice(String key) {
		driver.findElement(By.xpath(xPaths.get(PROD_PRICESUBMITNEW))).sendKeys(key);
	}

	public void clickSubmitBtn() {
		driver.findElement(By.xpath(xPaths.get(PROD_SUBMITNEW))).click();
	}
	
	public void clickSaveChanges() {
		driver.findElement(By.xpath(xPaths.get(PROD_EDITPRICESAVECHANGES))).click();
	}
	
	public WebElement prodEditUpdateOkMsg() {
		WebElement updateOk = driver.findElement(By.xpath(xPaths.get(PROD_EDITUPDATESUCCEEDED)));
		return updateOk;
	}
	
	
	public WebElement prodIsAddedMsg() {
		WebElement prodAdded = driver.findElement(By.xpath(xPaths.get(PROD_VALIDSUBMITNEW)));
		return prodAdded;
	}

	public boolean increasePrice(Double num, String xPath) {
		List<WebElement> items = driver.findElements(By.xpath(xPath));
		Iterator<WebElement> itr = items.iterator();
		boolean validInc[] = new boolean[items.size()];
		while (itr.hasNext()) {
			WebElement element = itr.next();
			String value1 = element.getAttribute("value");
			double incValue1 = num + Double.parseDouble(value1);
			element.clear();
			element.sendKeys(String.valueOf(incValue1));

			String value2 = element.getAttribute("value");
			if (!value2.equals(value1)) {
				Arrays.fill(validInc, true);
			}
		}
		boolean priceIncOk = true;
		for (int i = 0; i < validInc.length; i++) {
			if (validInc[i] == false) {
				priceIncOk = false;
			}
		}
		return priceIncOk;
	}
}
