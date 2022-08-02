package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {
    public HomePage(){
        super(By.xpath("//button[@class='start__button']"), "no button");
    }


    IButton btn = getElementFactory().getButton(By.xpath("//a[@class='start__link']"), "btn");

    public void clickOnBtn() {
        btn.click();
    }

}
