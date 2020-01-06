package com.app.dmm.home

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Path
import java.nio.file.Paths

import com.app.dmm.common.CommonKeywords
import com.app.dmm.common.CommonKeywords.WaitCondition
import com.app.dmm.utils.FileOperations
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable as GlobalVariable

public class ModelsPage {

	FileOperations fileOperations = new FileOperations()
	CommonKeywords common = new CommonKeywords()

	@Keyword
	def navigateToSidebarItemModels() {
		try {
			WebUI.click(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Models']))

			// Verify page elements after Models tab is clicked
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_side_content_title', [('title'):'Models']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Models title"
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item_highlighted', [('name'):'Models']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Models tab highlighted"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_register_model'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Register Model button clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type', [('modelType'):'Classification']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Type Classification clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type', [('modelType'):'Regression']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Type Regression clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type_selected', [('modelType'):'Classification']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Model Type Classification selected"
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def uploadFileToRegisterModel(String file) {
		try {
			WebUI.click(findTestObject('Object Repository/Home/Models/btn_register_model'))

			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_register_model_popup_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Register Model popup title"

			fileOperations.uploadFile(findTestObject('Object Repository/Home/link_browse'), file)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def registerModelAndVerify(String file) {
		try {
			// Verify File Uploaded
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_upload_complete_status'), 30, WaitCondition.ELEMENT_VISIBLE), "Could not find File upload complete status"
			
			Path filePath = Paths.get(file.replace('\\', '/'))
			Path fileName = filePath.getFileName()
			WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Models/txt_model_config_json_to_be_uploaded', [('fileName'):fileName.toString()]))
			
			// Register Model after upload
			WebUI.click(findTestObject('Object Repository/Home/Models/btn_register_model_inner_container'))
			
			// Verify and close File Saved toast message
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_register_model_popup_title'), 30, WaitCondition.ELEMENT_NOT_VISIBLE), "Error while verifying Register Model popup title invisible (Popup closed)"
			
			// Search uploaded file with name
			WebUI.sendKeys(findTestObject('Object Repository/Home/Models/input_search_file'), GlobalVariable.testCaseId)
			
			KeywordUtil.logInfo('Verify searched File Name')
			WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Home/Models/txt_searched_file_name')), GlobalVariable.testCaseId, false)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
}
