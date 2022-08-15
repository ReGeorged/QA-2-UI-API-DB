package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebElement;
import utils.ElementUtils;

import java.util.List;

public class NewsLetterForm extends Form {

    private By subjectLocator;


    private List<ILabel> newsLetterSelector = getElementFactory().findElements(By.xpath("//label[contains(@class,'unchecked-label')]"), "newsletter list", ElementType.LABEL);
    private IButton emailSubmitBtn = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"), "email submit button");
    private ILabel emailField = getElementFactory().getLabel(By.xpath("//input[@type='email']"), "email field ");
    //private IButton unsubscribeBtn = getElementFactory().getButton("asda","ada");

    public NewsLetterForm() {
        super(By.xpath("//span[contains(@class,'text-secondary')]"), "our newsletter label");
    }

    public void selectRandomLetter() {
        System.out.println("first try "+subjectLocator);
        ILabel placeholderButton = ElementUtils.randomElementFromList(newsLetterSelector, 1);
        placeholderButton.getJsActions().scrollToTheCenter();
        placeholderButton.clickAndWait();
        subjectLocator = placeholderButton.getLocator();

    }
    private IButton oldBtn = getElementFactory().getButton(subjectLocator,"old subject");

    public void sendKeysToEmail(String whatEMail) {
        emailField.sendKeys(whatEMail);
        emailSubmitBtn.clickAndWait();
    }


    public void reSelectSubject(){
        System.out.println(oldBtn.getLocator());
        oldBtn.getJsActions().scrollToTheCenter();
        oldBtn.click();
//        locatorToReUse.getJsActions().scrollToTheCenter();
//        locatorToReUse.click();

    }



}
