package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class HelpForm extends Form {
    private IButton closeHelpMenuBtn = getElementFactory().getButton(By.xpath("//span[@class='highlight']"), "send to bottom button");
    private ILabel helpBoxHiddenElementLocator = getElementFactory().getLabel(By.xpath("//div[contains(@class,'is-hidden')]"), "hidden state of help menu");

    public HelpForm() {
        super(By.xpath("//span[@class='highlight']"), "send to bottom button");
    }

    public void closeHelpMenu() {
        closeHelpMenuBtn.click();
    }

    public boolean isHelpBoxDisplayed() {
        return !helpBoxHiddenElementLocator.state().isDisplayed();
    }

}
