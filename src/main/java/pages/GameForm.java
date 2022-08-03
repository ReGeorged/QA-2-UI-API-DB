package pages;



import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.Passay;

public class GameForm extends Form {

    private ITextBox passwordField = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Choose Password']"), "password text field");
    private ITextBox mailField = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Your email']"), "mail text field");
    private ITextBox domainField = getElementFactory().getTextBox(By.xpath("//input[@placeholder='Domain']"), "domain field");
    private ICheckBox tcCheckBox = getElementFactory().getCheckBox(By.xpath("//span[@class='checkbox__box']"), "terms & conditions check box");
    //TODO this is not a button find out what it is!
    private IButton dropDownBtn = getElementFactory().getButton(By.xpath("//div[@class='dropdown__header']"), "drop down menu");
    private IButton nextBtn = getElementFactory().getButton(By.xpath("//a[@class='button--secondary']"), "next button");
    private IButton dotOrgSelector = getElementFactory().getButton(By.xpath("//div[@class='dropdown__list-item'][contains(text(),'.org')]"), "button for selecting .org in dropdown");

    //List<IButton> dropDownMailList = elementFactory.findElements(By.xpath("//div[@class='dropdown__list']/div[@class='dropdown__list-item']"), ElementType.BUTTON);
    // old selector -- //div[@class=\"dropdown__list\"]//div[not(contains(text(),'other'))]


    public GameForm() {
        super(By.xpath("//a[@class='login-form__terms-conditions']"), "accept TOS Box");
    }

    public void fillPassword() {
        passwordField.clearAndType(Passay.generatePassayPassword(2, 10) + "a");
    }

    public void fillMail() {
        mailField.clearAndType(Passay.generatePassayPassword(2, 10) + "a");
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
