package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ISelect;
import utils.IntUtils;
import utils.StringUtils;

import java.util.List;


public class GameForm extends Form {
    public GameForm() {
        super(By.xpath("//a[@class='login-form__terms-conditions']"), "accept TOS Box");
    }


    IElementFactory elementFactory = AqualityServices.getElementFactory();
    ITextBox passwordField = elementFactory.getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password text field");
    ITextBox mailField = elementFactory.getTextBox(By.xpath("//input[@placeholder='Your email']"), "mail text field");
    ITextBox domainField = elementFactory.getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain field");
    ICheckBox tcCheckBox = elementFactory.getCheckBox(By.xpath("//span[@class='checkbox__box']"), "terms & conditions check box");
    //TODO this is not a button find out what it is!
    IButton dropDownBtn = elementFactory.getButton(By.xpath("//div[@class='dropdown__header']"), "drop down menu");
    IButton nextBtn = elementFactory.getButton(By.xpath("//a[@class='button--secondary']"), "next button");
    IButton dotOrgSelector = elementFactory.getButton(By.xpath("//div[@class='dropdown__list-item'][contains(text(),'.org')]"), "button for selecting .org in dropdown");
    //List<IButton> dropDownMailList = elementFactory.findElements(By.xpath("//div[@class='dropdown__list']/div[@class='dropdown__list-item']"), ElementType.BUTTON);
    // old selector -- //div[@class=\"dropdown__list\"]//div[not(contains(text(),'other'))]
    public void fillPassword() {
        passwordField.clearAndType(StringUtils.generatePassayPassword() + "a");
    }

    public void fillMail() {
        mailField.clearAndType(StringUtils.generatePassayPassword() + "a");
    }

    public void fillDomain() {
        domainField.clearAndType("gmail");
    }

    public void clickOnDropDown() throws InterruptedException {
        dropDownBtn.click();
        dropDownBtn.getJsActions().scrollToTheCenter();
        ////Thread.sleep(3000);
       // System.out.println(dropDownMailList.size());
        dotOrgSelector.click();

    }

    public void clickOnTC() {
        tcCheckBox.click();
    }

    public void clickOnNextBtn() {
        nextBtn.click();
    }
}
