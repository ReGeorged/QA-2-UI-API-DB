package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {
    private IButton btn = getElementFactory().getButton(By.xpath("//a[@class='start__link']"), "btn");
    public HomePage(){
        super(By.xpath("//button[@class='start__button']"), "no button");
    }

    public void clickOnBtn() {
        btn.click();
    }
}
