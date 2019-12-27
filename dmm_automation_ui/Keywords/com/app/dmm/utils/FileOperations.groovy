package com.app.dmm.utils

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.Robot
import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.awt.event.KeyEvent

import org.openqa.selenium.WebDriver

import com.app.dmm.common.CommonKeywords
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.util.KeywordUtil

public class FileOperations {

	CommonKeywords common = new CommonKeywords()

	@Keyword
	def uploadFile(TestObject to, String file) {
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
		robot.keyPress(KeyEvent.VK_CONTROL)
		robot.keyPress(KeyEvent.VK_V)
		robot.keyRelease(KeyEvent.VK_CONTROL)
		robot.keyRelease(KeyEvent.VK_V)
		robot.setAutoDelay(1000)
		robot.keyPress(KeyEvent.VK_ENTER)
		robot.keyRelease(KeyEvent.VK_ENTER)

		// Verify File Uploaded
		// TODO: Need to create wrapper with webdriverwait and expected condition
		// assert WebUI.waitForElementVisible(findTestObject('Object Repository/Home/Data/txt_upload_complete_status'), 30), 'Could not find File upload complete status'
		WebUI.delay(60)

		// Submit file after upload
		WebUI.click(findTestObject('Object Repository/Home/Data/btn_submit_upload_file'))

		// Verify and close File Saved toast message
		/*WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Data/txt_file_save_success_toast_msg'))
		WebUI.click(findTestObject('Object Repository/Home/Data/btn_close_toast_msg'))*/
		
		// TODO: Need to create wrapper with webdriverwait and expected condition
		WebUI.delay(10)

		// Search uploaded file with name
		Path filePath = Paths.get(file.replace('\\', '/'))
		Path fileName = filePath.getFileName()
		WebUI.sendKeys(findTestObject('Object Repository/Home/Data/input_search_file'), fileName.toString())

		KeywordUtil.logInfo('Searched File Path')
		KeywordUtil.logInfo(WebUI.getText(findTestObject('Object Repository/Home/Data/link_searched_filepath')))
	}
}
