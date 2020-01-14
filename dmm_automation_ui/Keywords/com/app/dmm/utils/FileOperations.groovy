package com.app.dmm.utils

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import org.openqa.selenium.WebDriver

import com.app.dmm.common.CommonKeywords
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class FileOperations {

	CommonKeywords common = new CommonKeywords()

	@Keyword
	def uploadFile(TestObject to, String file) {
		try {
			try {
				WebUI.click(to)
			}
			catch (Exception e) {
				WebDriver driver = DriverFactory.getWebDriver()
				common.clickJS(to, driver)
			}

			Robot robot = new Robot()
			StringSelection path = new StringSelection(file)
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null)
			robot.setAutoDelay(1000)
			WebUI.delay(5)
			robot.keyPress(KeyEvent.VK_ENTER)
			robot.keyRelease(KeyEvent.VK_ENTER)
			robot.keyPress(KeyEvent.VK_CONTROL)
			robot.keyPress(KeyEvent.VK_V)
			robot.keyRelease(KeyEvent.VK_CONTROL)
			robot.keyRelease(KeyEvent.VK_V)
			robot.setAutoDelay(1000)
			robot.keyPress(KeyEvent.VK_ENTER)
			robot.keyRelease(KeyEvent.VK_ENTER)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
}
