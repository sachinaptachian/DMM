import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.app.dmm.common.CommonKeywords
import com.app.dmm.common.CommonKeywords.WaitCondition
import com.kms.katalon.core.annotation.AfterTestCase
import com.kms.katalon.core.annotation.AfterTestSuite
import com.kms.katalon.core.annotation.BeforeTestCase
import com.kms.katalon.core.annotation.BeforeTestSuite
import com.kms.katalon.core.context.TestCaseContext
import com.kms.katalon.core.context.TestSuiteContext
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

class TestListener {
	
	CommonKeywords common = new CommonKeywords()
	
	/**
	 * Executes before every test case starts.
	 * @param testCaseContext related information of the executed test case.
	 */
	@BeforeTestCase
	def sampleBeforeTestCase(TestCaseContext testCaseContext) {
		GlobalVariable.testCaseId = testCaseContext.getTestCaseId().split("/")[1]
		
		// Open browser
		WebUI.openBrowser(GlobalVariable.web_url)
		
		// Maximize window
		WebUI.maximizeWindow()
		
		// Verify Login Page displayed
		// assert common.waitForElement(findTestObject('Object Repository/Common/txt_header_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Header title is visible"
		// assert common.waitForElement(findTestObject('Object Repository/Login/txt_login'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Login title is visible"
		assert common.waitForElement(findTestObject('Object Repository/Login/input_username'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Username input is visible"
		assert common.waitForElement(findTestObject('Object Repository/Login/input_password'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Password input is visible"
		assert common.waitForElement(findTestObject('Object Repository/Login/btn_login'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Login button is clickable"
		KeywordUtil.logInfo("Login Page displayed successfully")
	}

	/**
	 * Executes after every test case ends.
	 * @param testCaseContext related information of the executed test case.
	 */
	@AfterTestCase
	def sampleAfterTestCase(TestCaseContext testCaseContext) {
		// Close browser
		WebUI.closeBrowser()
	}

	/**
	 * Executes before every test suite starts.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@BeforeTestSuite
	def sampleBeforeTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}

	/**
	 * Executes after every test suite ends.
	 * @param testSuiteContext: related information of the executed test suite.
	 */
	@AfterTestSuite
	def sampleAfterTestSuite(TestSuiteContext testSuiteContext) {
		println testSuiteContext.getTestSuiteId()
	}
}
