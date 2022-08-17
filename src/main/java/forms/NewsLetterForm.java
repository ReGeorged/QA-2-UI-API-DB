package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import base.BrowserBase;
import org.openqa.selenium.By;

import java.util.List;

public class NewsLetterForm extends Form {
    private List<ILabel> newsLetterSelector = getElementFactory().findElements(By.xpath("//label[contains(@class,'unchecked-label')]"), "newsletter list", ElementType.LABEL);
    private IButton emailSubmitBtn = getElementFactory().getButton(By.xpath("//input[@data-event='NL_submit']"), "email submit button");
    private ILabel emailField = getElementFactory().getLabel(By.xpath("//input[@type='email']"), "email field ");
    private List<IButton> previewListBtn = getElementFactory().findElements(By.xpath("//i[contains(@class,'fa-eye')]"), "list of preview buttons", ElementType.BUTTON);
    private List<ILabel> iframesInNewsLetterParent = getElementFactory().findElements(By.xpath("//div[@class='modal ']"),"iframes lis in newsletters page",ElementType.LABEL);
    private ILabel unsubscribeLink = getElementFactory().getLabel(By.xpath("//a[contains(text(),'unsubscribe by clicking here')]"),"unsubscribe href");


    public NewsLetterForm() {
        super(By.xpath("//span[contains(@class,'text-secondary')]"), "our newsletter label");
    }

    public void selectRandomLetter(int randomIndex) {
        ILabel selectNewsLetter = newsLetterSelector.get(randomIndex);
        selectNewsLetter.getJsActions().scrollToTheCenter();
        selectNewsLetter.clickAndWait();
    }

    public int getNewsLetterAmount() {
        return newsLetterSelector.size();
    }

    public void sendKeysToEmail(String whatEMail) {
        emailField.sendKeys(whatEMail);
        emailSubmitBtn.clickAndWait();
    }

    public void selectRandomPreview(int randomIndex) {
        IButton previewButton = previewListBtn.get(randomIndex);
        System.out.println(previewListBtn.size());
        previewButton.getJsActions().scrollToTheCenter();
        previewButton.click();

    }
    public int getIframeSize(){
        iframesInNewsLetterParent.get(1).state().waitForExist();
        int size = iframesInNewsLetterParent.size();
        return size;
    }

    public void switchToIFrame(int whichIframe){

        BrowserBase.initialize().getDriver().switchTo().frame(whichIframe);
    }
    public String getUnsubscribeLink(){
        unsubscribeLink.getJsActions().scrollToTheCenter();

        return unsubscribeLink.getAttribute("href");
    }



}
