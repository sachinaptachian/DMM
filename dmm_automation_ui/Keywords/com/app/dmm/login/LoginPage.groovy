package com.app.dmm.login

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.app.dmm.common.CommonKeywords
import com.app.dmm.common.CommonKeywords.WaitCondition
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class LoginPage {

	CommonKeywords common = new CommonKeywords()

	@Keyword
	def loginToPortal() {
		try {
			WebUI.sendKeys(findTestObject('Object Repository/Login/input_username'), GlobalVariable.username)
			WebUI.sendKeys(findTestObject('Object Repository/Login/input_password'), GlobalVariable.password)
			WebUI.click(findTestObject('Object Repository/Login/btn_login'))

			// Verify Login Success toast message
			// assert common.waitForElement(findTestObject('Object Repository/Home/txt_login_success_toast_msg'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Login success toast message"
			// WebUI.click(findTestObject('Object Repository/Common/btn_close_toast_msg'))

			// Verify Header Title
			assert common.waitForElement(findTestObject('Object Repository/Common/txt_header_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Header title"

			// Verify Sidebar Items
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Data']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying sidebar Data tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Models']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying sidebar Models tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Settings']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying sidebar Settings tab clickable"

			// Verify Logout button
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Logout']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Logout button clickable"

			KeywordUtil.logInfo("Login to portal successful")
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def logoutFromPortal() {
		try {
			WebUI.click(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Logout']))

			// Verify Login Page displayed
			/*assert WebUI.waitForElementVisible(findTestObject('Object Repository/Common/txt_header_title'), 5), "Error while verifying Header title"
			assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/txt_login'), 5), "Error while verifying Login title"
			assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/input_username'), 5), "Error while verifying Username input"
			assert WebUI.waitForElementVisible(findTestObject('Object Repository/Login/input_password'), 5), "Error while verifying Password input"
			assert WebUI.waitForElementClickable(findTestObject('Object Repository/Login/btn_login'), 5), "Error while verifying Login button"*/
			
			// assert common.waitForElement(findTestObject('Object Repository/Common/txt_header_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Header title"
			// assert common.waitForElement(findTestObject('Object Repository/Login/txt_login'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Login title"
			assert common.waitForElement(findTestObject('Object Repository/Login/input_username'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Username input"
			assert common.waitForElement(findTestObject('Object Repository/Login/input_password'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Password input"
			assert common.waitForElement(findTestObject('Object Repository/Login/btn_login'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Login button clickable"
			
			KeywordUtil.logInfo("Logout from portal successful")
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
}
