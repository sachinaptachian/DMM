package com.app.dmm.home

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.nio.file.Path
import java.nio.file.Paths

import com.app.dmm.common.CommonKeywords
import com.app.dmm.common.CommonKeywords.WaitCondition
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DataPage {

	CommonKeywords common = new CommonKeywords()

	@Keyword
	def navigateToSidebarItemData() {
		try {
			WebUI.click(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):'Data']))

			// Verify page elements after Data tab is clicked
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_side_content_title', [('title'):'Files']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Files title"
			assert common.waitForElement(findTestObject('Object Repository/Home/btn_sidebar_item_highlighted', [('name'):'Data']), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Data tab highlighted"
			assert common.waitForElement(findTestObject('Object Repository/Home/Data/txt_upload_to_s3_heading'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying Upload To S3 heading"
			assert common.waitForElement(findTestObject('Object Repository/Home/Data/txt_list_of_files_heading'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying List of Files heading"
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}

	@Keyword
	def submitAndVerifyTrainingDataUploaded(String file) {
		try {
			// Verify File Uploaded
			assert common.waitForElement(findTestObject('Object Repository/Home/txt_upload_complete_status'), 90, WaitCondition.ELEMENT_VISIBLE), "Could not find File upload complete status"
			
			Path filePath = Paths.get(file.replace('\\', '/'))
			Path fileName = filePath.getFileName()
			WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Data/input_file_description_placeholder', [('fileName'):fileName.toString()]))

			// Submit file after upload
			WebUI.click(findTestObject('Object Repository/Home/Data/btn_submit_upload_file'))

			// Verify and close File Saved toast message
			assert common.waitForElement(findTestObject('Object Repository/Home/Data/txt_file_save_success_toast_msg'), 5, WaitCondition.ELEMENT_VISIBLE), "Error while verifying file success toast message"
			WebUI.click(findTestObject('Object Repository/Home/Data/btn_close_toast_msg'))
			
			// Search uploaded file with name
			WebUI.sendKeys(findTestObject('Object Repository/Home/Data/input_search_file'), fileName.toString())
			KeywordUtil.logInfo('Searched File Path')
			KeywordUtil.logInfo(WebUI.getText(findTestObject('Object Repository/Home/Data/link_searched_filepath')))
		} catch (Exception e) {
			KeywordUtil.markFailedAndStop(e.getMessage())
		}
	}
}
