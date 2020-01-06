package com.app.dmm.common

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.testobject.TestObjectProperty
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory

class CommonKeywords {

	public void clickJS(TestObject to, WebDriver driver) {
		WebElement element = WebUiCommonHelper.findWebElement(to, 60)
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("arguments[0].click();", element)
	}

	public enum WaitCondition {
		ELEMENT_VISIBLE,
		ELEMENT_NOT_VISIBLE,
		ELEMENT_CLICKABLE,
		ELEMENT_NOT_CLICKABLE,
		ELEMENT_TO_BE_SELECTED
	}

	private By getSelector(String locatorType, String locatorValue) {
		switch(locatorType.toUpperCase()) {
			case "XPATH":
				return By.xpath(locatorValue)
			case "CSS":
				return By.cssSelector(locatorValue)
			case "ID":
				return By.id(locatorValue)
			case "NAME":
				return By.name(locatorValue)
			default:
				KeywordUtil.logInfo("Locator type is invalid")
				return false
		}
	}

	public boolean waitForElement(TestObject to, int timeout, WaitCondition conditionToVerify) {
		try {
			WebDriver driver = DriverFactory.getWebDriver()
			WebDriverWait wait = new WebDriverWait(driver, timeout)

			TestObjectProperty objectProperty = to.getProperties().get(0)
			String locatorType = objectProperty.getName()
			String value = objectProperty.getValue()
			KeywordUtil.logInfo("Object Property: " + "Name = " + locatorType + " , value = " + value)

			By by = getSelector(locatorType, value)

			switch(conditionToVerify.name()) {
				case "ELEMENT_VISIBLE":
					wait.until(ExpectedConditions.visibilityOfElementLocated(by))
					KeywordUtil.logInfo("Given element is visible")
					return true
				case "ELEMENT_NOT_VISIBLE":
					wait.until(ExpectedConditions.invisibilityOfElementLocated(by))
					KeywordUtil.logInfo("Given element is not visible")
					return true
				case "ELEMENT_CLICKABLE":
					wait.until(ExpectedConditions.elementToBeClickable(by))
					KeywordUtil.logInfo("Given element is clickable")
					return true
				case "ELEMENT_TO_BE_SELECTED":
					wait.until(ExpectedConditions.elementToBeSelected(by))
					return true
				default:
					KeywordUtil.logInfo("Wait Condition is invalid")
					return false
			}
		} catch (Exception e) {
			return false
		}
	}
}
