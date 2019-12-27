package com.app.dmm.login

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

public class LoginPage {

	@Keyword
	def loginToPortal() {
		WebUI.sendKeys(findTestObject('Object Repository/Login/input_username'), GlobalVariable.username)
		WebUI.sendKeys(findTestObject('Object Repository/Login/input_password'), GlobalVariable.password)
		WebUI.click(findTestObject('Object Repository/Login/btn_login'))

		// Verify Login Success toast message
		/*WebUI.waitForElementVisible(findTestObject('Object Repository/Home/txt_login_success_toast_msg'), 10)
		 WebUI.click(findTestObject('Object Repository/Common/btn_close_toast_msg'))*/

		// Verify Header Title
		assert WebUI.waitForElementVisible(findTestObject('Object Repository/Common/txt_header_title'), 5), "Error while verifying Header title"

		// Verify Sidebar Items
		assert WebUI.waitForElementClickable(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Data']), 5), "Error while verifying sidebar Data tab clickable"
		assert WebUI.waitForElementClickable(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Models']), 5), "Error while verifying sidebar Models tab clickable"
		assert WebUI.waitForElementClickable(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Admin']), 5), "Error while verifying sidebar Admin tab clickable"

		// Verify Logout button
		assert WebUI.waitForElementClickable(findTestObject('Object Repository/Common/link_logout'), 5), "Error while verifying Logout button clickable"

		KeywordUtil.logInfo("Login to portal successful")
	}

	@Keyword
	def logoutFromPortal() {
		WebUI.click(findTestObject('Object Repository/Common/link_logout'))

		// Verify Login Page displayed
		assert WebUI.waitForElementVisible(findTestObject('Object Repository/Common/txt_header_title'), 5), "Error while verifying Header title"
		assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/txt_login'), 5), "Error while verifying Login title"
		assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/input_username'), 5), "Error while verifying Username input"
		assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/input_password'), 5), "Error while verifying Password input"
		assert WebUI.waitForElementClickable(findTestObject('Object Repository/Login/btn_login'), 5), "Error while verifying Login button"

		KeywordUtil.logInfo("Logout from portal successful")
	}
}
