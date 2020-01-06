import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration

import internal.GlobalVariable as GlobalVariable

'Login to portal'
CustomKeywords.'com.app.dmm.login.LoginPage.loginToPortal'()

'Navigate to sidebar items Data'
CustomKeywords.'com.app.dmm.home.DataPage.navigateToSidebarItemData'()

'Upload Training Data File'
String path = RunConfiguration.getProjectDir() + '/Data Files/'
path = path.replace('/', '\\')
String trainingFile = path + GlobalVariable.trainingDataFile
CustomKeywords.'com.app.dmm.utils.FileOperations.uploadFile'(findTestObject('Object Repository/Home/link_browse'), trainingFile)

'Submit and verify Training Data is uploaded'
CustomKeywords.'com.app.dmm.home.DataPage.submitAndVerifyTrainingDataUploaded'(trainingFile)

'Navigate to sidebar items Models'
CustomKeywords.'com.app.dmm.home.ModelsPage.navigateToSidebarItemModels'()

'Upload File to Register Model'
String registerModelFile = path + GlobalVariable.registerModelFile
CustomKeywords.'com.app.dmm.home.ModelsPage.uploadFileToRegisterModel'(registerModelFile)

'Register Model and verify'
CustomKeywords.'com.app.dmm.home.ModelsPage.registerModelAndVerify'(registerModelFile)

'Logout from portal'
CustomKeywords.'com.app.dmm.login.LoginPage.logoutFromPortal'()
