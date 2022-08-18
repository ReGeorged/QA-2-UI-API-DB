package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TopForm extends Form {
    private IButton newsLetterHyperLink = getElementFactory().getButton(By.xpath("//span[@data-event='newsletter-link-header']"), "top newsLetter hyperlink");

    public TopForm() {
        super(By.xpath("//div[contains(@class,'o-site-header__top')]"), "top form locator");
    }

    public void clickOnNewsLetter() {
        newsLetterHyperLink.click();
    }
}
