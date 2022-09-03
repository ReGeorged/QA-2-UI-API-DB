package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.MouseActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

public class HomePage extends Form {
    private ILabel versionIndicator = getElementFactory().getLabel(By.xpath("//p[contains(@class,'footer-text')]//span"),"bottom version indicator");
    private IButton nexageBtn = getElementFactory().getButton(By.xpath("//a[@class='list-group-item'][contains(text(),'Nexage')]"),"Nexage in projects list");
    private IButton addProjectBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'btn-xs')]"),"add button");


    public HomePage() {
        super(By.xpath("//ol[@class='breadcrumb']"), "home page");

    }



    public void setCookie(String cookieName, String cookieValue){
        Cookie token = new Cookie(cookieName, cookieValue);
        AqualityServices.getBrowser().getDriver().manage().addCookie(token);
    }
    public String getVersion(){
        return versionIndicator.getText();
    }
    public void clickOnNexage(){
        nexageBtn.click();
    }
    public void clickAddBtn(){
        addProjectBtn.click();
    }

    public void moveMouseAndClick(){
        MouseActions mouseActions = new MouseActions(nexageBtn, ElementType.BUTTON.name());
        mouseActions.moveMouseToElement();
        mouseActions.click();
    }
}
