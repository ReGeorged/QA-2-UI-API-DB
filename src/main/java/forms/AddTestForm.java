package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.BrowserUtils;

public class AddTestForm extends Form {
    private ITextBox testName = getElementFactory().getTextBox(By.xpath("//input[@id='testName']"),"Test name input field");
    private ITextBox testMethod = getElementFactory().getTextBox(By.xpath("//input[@id='testMethod']"),"Test method input field");
    private ITextBox startTime = getElementFactory().getTextBox(By.xpath("//input[@id='startTime']"),"Start time input field");
    private ITextBox endTime = getElementFactory().getTextBox(By.xpath("//input[@id='endTime']"),"End time input field");
    private ITextBox environment = getElementFactory().getTextBox(By.xpath("//input[@id='environment']"),"Environment input field");
    private ITextBox browser = getElementFactory().getTextBox(By.xpath("//input[@id='browser']"),"Browser input field");
    private ITextBox logTextArea = getElementFactory().getTextBox(By.xpath("//textarea[@id='log']"),"Log text area field");
    private IButton chooseFileBtn = getElementFactory().getButton(By.xpath("//input[@id='attachment']"),"Choose file button");
    private IButton saveProjectBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'btn-primary') and @type='button']"),"Save project button");
    private IComboBox testStatusDropDown = getElementFactory().getComboBox(By.xpath("//select[@id='testStatus']"),"Test status select box");
    private ILabel successMessage = getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success') and contains(@style,'block')]"),"Success Message Box");
    public AddTestForm() {
        super(By.xpath("//div[contains(@id,'addTest')and @aria-hidden='false']"), "Add test form");
    }

    public void clickOnSaveProjectBtn(){
        saveProjectBtn.click();
    }

    public void fillForm(String name, String method,String start,String end,String env,String whatBrowser,String log,String status){
        testName.clearAndType(name);
        testMethod.clearAndType(method);
        startTime.clearAndType(start);
        endTime.clearAndType(end);
        environment.clearAndType(env);
        browser.clearAndType(whatBrowser);
        logTextArea.clearAndType(log);
        testStatusDropDown.selectByContainingText(status);
        chooseFileBtn.sendKeys(BrowserUtils.takeScreenAndGetPath());
        saveProjectBtn.click();
    }
    public boolean isTestSaved(){
        successMessage.state().waitForDisplayed();
        return successMessage.state().isDisplayed();
    }
}
