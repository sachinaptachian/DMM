import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
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
import internal.GlobalVariable as GlobalVariable

'Login to portal'
CustomKeywords.'com.app.dmm.login.LoginPage.loginToPortal'()

'Navigate to sidebar items'
CustomKeywords.'com.app.dmm.home.DataPage.navigateToSidebarItemData'()

'Upload Data File'
String path = RunConfiguration.getProjectDir() + '/Data Files/'
path = path.replace('/', '\\')
path = path + 'Rutul_BMAF-TrainingData.csv'
CustomKeywords.'com.app.dmm.utils.FileOperations.uploadFile'(findTestObject('Home/link_browse'), path)

'Logout from portal'
CustomKeywords.'com.app.dmm.login.LoginPage.logoutFromPortal'()

