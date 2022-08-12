package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private IButton privacyButtonAccept = getElementFactory().getButton(By.xpath("//button[contains(@id,'didomi-notice-agree-button')]"), "popup privacy accept button");

    public HomePage() {
        super(By.xpath("//h2[@class=\"c-title qa-section-title\"][contains(text(),'Featured')]"), "Featured Banner");
    }

    public void clickOnPrivacyBtn() {
        privacyButtonAccept.click();

    }

}
