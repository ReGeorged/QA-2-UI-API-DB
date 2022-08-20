package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class PreviewForm extends Form {

    private ILabel unsubscribeLink = getElementFactory().getLabel(By.xpath("//a[contains(text(),'unsubscribe by clicking here')]"), "unsubscribe href");
    private IButton closeBtn = getElementFactory().getButton(By.xpath("//a[@class='close-modal ']"), "close button in form");

    public PreviewForm() {
        super(By.xpath("//div[contains(@id,'previews')]"), "close button of preview");
    }

    public String getUnsubscribeLink() {
        unsubscribeLink.state().waitForDisplayed();
        unsubscribeLink.getJsActions().scrollToTheCenter();
        return unsubscribeLink.getAttribute("href");
    }

    public boolean isCloseBtnDisplayed() {
        closeBtn.state().waitForDisplayed();
        return closeBtn.state().isDisplayed();
    }
}
