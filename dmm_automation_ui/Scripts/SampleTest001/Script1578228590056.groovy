import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

'Login to portal'
CustomKeywords.'com.app.dmm.login.LoginPage.loginToPortal'()

// Upload Training Data Set
'Navigate to sidebar items Data'
CustomKeywords.'com.app.dmm.home.DataPage.navigateToSidebarItemData'()

String path = RunConfiguration.getProjectDir() + '/Data Files/'
if (RunConfiguration.getOS().indexOf('Windows') != -1) {
    path = path.replace('/', '\\')
}

'Upload Training Data File'
String trainingDataSet = path + GlobalVariable.trainingDataSet
CustomKeywords.'com.app.dmm.utils.FileOperations.uploadFile'(findTestObject('Object Repository/Home/link_browse'), trainingDataSet)

'Submit and verify Training Data is uploaded'
CustomKeywords.'com.app.dmm.home.DataPage.submitAndVerifyDataSetUploaded'(trainingDataSet)

// Register Model
'Navigate to sidebar items Models'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToSidebarItemModels'()

'Upload File to Register Model'
String registerModelFile = path + GlobalVariable.registerModelFile
CustomKeywords.'com.app.dmm.home.ModelsPage.uploadFileToRegisterModel'(registerModelFile)

'Register Model and verify'
CustomKeywords.'com.app.dmm.home.ModelsPage.registerModelAndVerify'(registerModelFile)

// Upload Prediction Data Set
'Navigate to sidebar items Data'
CustomKeywords.'com.app.dmm.home.DataPage.navigateToSidebarItemData'()

'Upload Prediction Data File'
String predictionDataSet = path + GlobalVariable.predictionDataSet
CustomKeywords.'com.app.dmm.utils.FileOperations.uploadFile'(findTestObject('Object Repository/Home/link_browse'), predictionDataSet)

'Submit and verify Prediction Data is uploaded'
CustomKeywords.'com.app.dmm.home.DataPage.submitAndVerifyDataSetUploaded'(predictionDataSet)

// Upload Prediction Data File
'Navigate to sidebar items Models'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToSidebarItemModels'()

'Navigate to Registered Model'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToRegisteredModelDataDrift'()

'Add Prediction Data'
String predictionDataJsonFile = path + GlobalVariable.predictionDataJsonFile
CustomKeywords.'com.app.dmm.home.ModelsPage.addPredictionData'(predictionDataJsonFile)

// Upload Ground Truth Data Set
'Navigate to sidebar items Data'
CustomKeywords.'com.app.dmm.home.DataPage.navigateToSidebarItemData'()

'Upload Ground Truth Data File'
String groundTruthDataSet = path + GlobalVariable.groundTruthDataSet
CustomKeywords.'com.app.dmm.utils.FileOperations.uploadFile'(findTestObject('Object Repository/Home/link_browse'), groundTruthDataSet)

'Submit and verify Ground Truth Data is uploaded'
CustomKeywords.'com.app.dmm.home.DataPage.submitAndVerifyDataSetUploaded'(groundTruthDataSet)

// Upload Ground Truth File
'Navigate to sidebar items Models'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToSidebarItemModels'()

'Navigate to Registered Model'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToRegisteredModelQuality'()

'Add Ground Truth Data'
String groundTruthDataJsonFile = path + GlobalVariable.groundTruthDataJsonFile
CustomKeywords.'com.app.dmm.home.ModelsPage.addGroundTruthData'(groundTruthDataJsonFile)

'Logout from portal'
CustomKeywords.'com.app.dmm.login.LoginPage.logoutFromPortal'()
