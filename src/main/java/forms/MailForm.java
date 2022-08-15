package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class MailForm extends Form {

    private IButton confirmBtn = getElementFactory().getButton(By.xpath("//td[@align='left']//a"),"confirm button");
    public MailForm() {
        super(By.xpath("//span[contains(text(),'Dear reader')]"),"Confirmation from extracted from mail");
    }

    public void clickOnConfirmBtn(){
        confirmBtn.getJsActions().scrollToTheCenter();
        confirmBtn.click();
    }
}
