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
			verifyModelsPageElements()
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	private void verifyModelsPageElements() {
		assert common.waitForElement(findTestObject('Object Repository/Home/txt_side_content_title', [('title'):'Models']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Models title"
		assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item_highlighted', [('name'):'Models']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Models tab highlighted"
		assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_register_model'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Register Model button clickable"
		assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type', [('modelType'):'Classification']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Type Classification clickable"
		assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type', [('modelType'):'Regression']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Type Regression clickable"
		assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_model_type_selected', [('modelType'):'Classification']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Model Type Classification selected"
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

			// Verify Models Page elements
			verifyModelsPageElements()

			// Search uploaded file with name
			WebUI.sendKeys(findTestObject('Object Repository/Home/Models/input_search_file'), GlobalVariable.testCaseId)

			KeywordUtil.logInfo('Verify searched File Name')
			WebUI.verifyMatch(WebUI.getText(findTestObject('Object Repository/Home/Models/txt_searched_file_name')), GlobalVariable.testCaseId, false)
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def navigateToRegisteredModelDataDrift() {
		try {
			// Search Registered Model and click
			WebUI.sendKeys(findTestObject('Object Repository/Home/Models/input_search_file'), GlobalVariable.testCaseId)
			WebUI.click(findTestObject('Object Repository/Home/Models/txt_searched_file_name'))

			// Verify Registered Model Data Drift page elements
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_registered_model_heading', [('modelName'):GlobalVariable.testCaseId]), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Registered Model heading"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Data Drift']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Data Drift tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Model Quality']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Quality tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Scheduled Tests']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Scheduled Tests tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Notification Channels']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Notification Channels tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tab_selected', [('tabName'):'Data Drift']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Data Drift tab selected"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_data_drift_desc'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Data Drift description"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_add_prediction_data'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Add Prediction Data clickable"
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def addPredictionData(String file) {
		try {
			// Click Add Prediction Data button
			WebUI.click(findTestObject('Object Repository/Home/Models/btn_add_prediction_data'))
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_add_prediction_data_popup_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Add Prediction Data popup title"
			
			// Upload Prediction Data json file			
			fileOperations.uploadFile(findTestObject('Object Repository/Home/link_browse'), file)
			
			// Verify File Uploaded
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_upload_complete_status'), 30, WaitCondition.ELEMENT_VISIBLE), "Could not find File upload complete status"
			
			Path filePath = Paths.get(file.replace('\\', '/'))
			Path fileName = filePath.getFileName()
			WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Models/txt_model_config_json_to_be_uploaded', [('fileName'):fileName.toString()]))
			
			// Click Submit button after upload
			WebUI.click(findTestObject('Home/Models/btn_submit_data'))
			
			// Verify and close File Saved toast message
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_prediction_registered_success_toast_msg'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Prediction registered success toast message"
			WebUI.click(findTestObject('Object Repository/Home/Data/btn_close_toast_msg'))
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
	
	@Keyword
	def navigateToRegisteredModelQuality() {
		try {
			// Search Registered Model and click
			WebUI.sendKeys(findTestObject('Object Repository/Home/Models/input_search_file'), GlobalVariable.testCaseId)
			WebUI.click(findTestObject('Object Repository/Home/Models/txt_searched_file_name'))

			// Verify Registered Model Data Drift page elements
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_registered_model_heading', [('modelName'):GlobalVariable.testCaseId]), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Registered Model heading"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Model Quality']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Model Quality tab clickable"
			
			// Click Model Quality tab
			WebUI.click(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Model Quality']))
				
			// Verify Registered Model Quality page elements
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Data Drift']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Data Drift tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Scheduled Tests']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Scheduled Tests tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tabs', [('tabName'):'Notification Channels']), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Notification Channels tab clickable"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_react_tab_selected', [('tabName'):'Model Quality']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Model Quality tab selected"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_model_quality_desc'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Model Quality description"
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/btn_add_ground_truth_data'), 5, WaitCondition.ELEMENT_CLICKABLE), "Error while verifying Add Ground Truth Data clickable"
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
	
	@Keyword
	def addGroundTruthData(String file) {
		try {
			// Click Add Ground Truth Data button
			WebUI.click(findTestObject('Object Repository/Home/Models/btn_add_ground_truth_data'))
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_add_ground_truth_data_popup_title'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Add Prediction Data popup title"
			
			// Upload Ground Truth Data json file
			fileOperations.uploadFile(findTestObject('Object Repository/Home/link_browse'), file)
			
			// Verify File Uploaded
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_upload_complete_status'), 30, WaitCondition.ELEMENT_VISIBLE), "Could not find File upload complete status"
			
			Path filePath = Paths.get(file.replace('\\', '/'))
			Path fileName = filePath.getFileName()
			WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Models/txt_model_config_json_to_be_uploaded', [('fileName'):fileName.toString()]))
			
			// Click Submit button after upload
			WebUI.click(findTestObject('Home/Models/btn_submit_data'))
			
			// Verify and close File Saved toast message
			assert common.waitForElement(findTestObject('Object Repository/Home/Models/txt_ground_truths_registered_success_toast_msg'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Ground Truths registered success toast message"
			WebUI.click(findTestObject('Object Repository/Home/Data/btn_close_toast_msg'))
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
	
	@Keyword
	def searchAndVerifyDataDriftFeature(String feature) {
		// Search Data Drift feature
		WebUI.sendKeys(findTestObject('Object Repository/Home/Models/input_search_feature'), feature)
		
		// Verify searched feature name
		WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Models/txt_searched_data_drift_feature_name', [('featureName'):feature]))
	}
}
