package com.app.dmm.home

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

public class DataPage {

	@Keyword
	def navigateToSidebarItemData(String name) {
		WebUI.click(findTestObject('Object Repository/Home/btn_sidebar_item', [('name'):name]))

		// Verify page elements after Data tab is clicked
		WebUI.verifyElementVisible(findTestObject('Object Repository/Home/btn_sidebar_item_highlighted', [('name'):name]))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Home/txt_side_content_title', [('title'):'Files']))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Data/txt_upload_to_s3_heading'))
		WebUI.verifyElementVisible(findTestObject('Object Repository/Home/Data/txt_list_of_files_heading'))
	}
}
