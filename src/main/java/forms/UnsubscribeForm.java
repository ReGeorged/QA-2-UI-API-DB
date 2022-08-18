package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class UnsubscribeForm extends Form {
    private ITextBox unsubscribeField = getElementFactory().getTextBox(By.xpath("//input[@type='email']"), "unsubscribe email field");
    private IButton unsubscribeBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "unsubscribe button");
    private ILabel unsubscribeMessage = getElementFactory().getLabel(By.xpath("//strong[contains(text(),'You are unsubscribed')]"),"unsubscribe text");
    public UnsubscribeForm() {
        super(By.xpath("//img[@class='unsubscribe-logo']"), "unsubscribe logo");
    }

    public void sendEmail(String email) {
        unsubscribeField.clearAndType(email);
    }

    public void clickUnsubscribeBtn() {
        unsubscribeBtn.click();
    }
    public boolean isUnsubscribeMessageDisplayed(){
        return unsubscribeMessage.state().isDisplayed();
    }
}
