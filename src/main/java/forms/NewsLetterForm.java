package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class NewsLetterForm extends Form {
    private List<ILabel> newsLetterSelector = getElementFactory().findElements(By.xpath("//label[contains(@class,'unchecked-label')]"), "newsletter list", ElementType.LABEL);
    private IButton emailSubmitBtn = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"), "email submit button");
    private ILabel emailField = getElementFactory().getLabel(By.xpath("//input[@type='email']"), "email field ");
    private List<IButton> previewListBtn = getElementFactory().findElements(By.xpath("//i[contains(@class,'fa-eye')]"), "list of preview buttons", ElementType.BUTTON);
    private ILabel unsubscribeLink = getElementFactory().getLabel(By.xpath("//a[contains(text(),'unsubscribe by clicking here')]"),"unsubscribe href");
    private ILabel waitHelperElement = getElementFactory().getLabel(By.xpath("//a[@class='close-modal ']"),"close button of preview");

    public NewsLetterForm() {
        super(By.xpath("//span[contains(@class,'text-secondary')]"), "our newsletter label");
    }

    public boolean isEmailFieldDisplayed(){
        return emailField.state().isDisplayed();
    }
    public void selectRandomLetter(int randomIndex) {
        ILabel selectNewsLetter = newsLetterSelector.get(randomIndex);
        selectNewsLetter.getJsActions().scrollToTheCenter();
        selectNewsLetter.clickAndWait();
    }

    public int getNewsLetterAmount() {
        return newsLetterSelector.size();
    }

    public void submitEmail(String email) {
        emailField.sendKeys(email);
        emailSubmitBtn.clickAndWait();
    }
    public void selectRandomPreview(int randomIndex) {
        IButton previewButton = previewListBtn.get(randomIndex);
        previewButton.getJsActions().scrollToTheCenter();
        previewButton.click();
    }

    public boolean checkPreviewOpen(){
        waitHelperElement.state().waitForDisplayed();
        return waitHelperElement.state().isDisplayed();
    }

    public void waitForCloseButtonInPreview(){
        waitHelperElement.state().waitForDisplayed();

    }
    public String getUnsubscribeLink(){
        unsubscribeLink.state().waitForDisplayed();
        unsubscribeLink.getJsActions().scrollToTheCenter();
        return unsubscribeLink.getAttribute("href");
    }




}
