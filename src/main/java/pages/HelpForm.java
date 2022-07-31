package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;


public class HelpForm extends Form {
    public HelpForm() {
        super(By.xpath("//span[@class='highlight']"), "send to bottom button");
    }

    IElementFactory elementFactory = AqualityServices.getElementFactory();
    IButton closeHelpMenuBtn = elementFactory.getButton(By.xpath("//span[@class='highlight']"), "send to bottom button");
    ILabel helpBoxHiddenElementLocator = elementFactory.getLabel(By.xpath("//div[contains(@class,'is-hidden')]"), "hidden state of help menu");

    public void closeHelpMenu() {
        closeHelpMenuBtn.click();
    }


    public boolean isHelpBoxHiddenDisplayed() {
        return helpBoxHiddenElementLocator.state().isDisplayed();
    }

}
