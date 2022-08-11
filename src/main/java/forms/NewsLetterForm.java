package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ElementUtils;

import java.util.List;

public class NewsLetterForm extends Form {

    private List<ILabel> newsLetterSelector = getElementFactory().findElements(By.xpath("//label[contains(@class,'unchecked-label')]"), "newsletter list", ElementType.LABEL);
    private IButton emailSubmitBtn = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"), "email submit button");
    private ILabel emailField = getElementFactory().getLabel(By.xpath("//input[@type='email']"), "email field ");

    public NewsLetterForm() {
        super(By.xpath("//span[contains(@class,'text-secondary')]"), "our newsletter label");
    }

    public void selectRandomLetter() {
        ILabel placeholderButton = ElementUtils.randomElementFromList(newsLetterSelector, 1);
        placeholderButton.getJsActions().scrollToTheCenter();
        placeholderButton.clickAndWait();
    }

    public void sendKeysToEmail() {
        emailField.sendKeys("asda@hotveryhot.com");
        emailSubmitBtn.clickAndWait();
    }


}
