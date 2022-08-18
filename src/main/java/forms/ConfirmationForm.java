package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class ConfirmationForm extends Form {
    private IButton backToSiteBtn = getElementFactory().getButton(By.xpath("//a[@aria-label='Back to the site']"),"back to site btn");
    public ConfirmationForm() {
        super(By.xpath("//div[@class='enw-block-confirmation__text']"), "email subscription confirmation form");
    }
    public void clickOnBackToSiteBtn(){
        backToSiteBtn.getJsActions().scrollToTheCenter();
        backToSiteBtn.click();
    }
}
