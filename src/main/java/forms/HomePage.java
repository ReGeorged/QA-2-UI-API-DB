package forms;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.MouseActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Form {
    private ILabel versionIndicator = getElementFactory().getLabel(By.xpath("//p[contains(@class,'footer-text')]//span"), "bottom version indicator");
    private IButton nexageBtn = getElementFactory().getButton(By.xpath("//a[@class='list-group-item'][contains(text(),'Nexage')]"), "Nexage in projects list");
    private IButton addProjectBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'btn-xs')]"), "add button");
    private List<ILabel> projectsList = getElementFactory().findElements(By.xpath("//div[@class='list-group']//a"), "Projects list", ElementType.LABEL);


    public HomePage() {
        super(By.xpath("//div[@class='list-group']"), "home page");

    }


    public void setCookie(String cookieName, String cookieValue) {
        Cookie token = new Cookie(cookieName, cookieValue);
        AqualityServices.getBrowser().getDriver().manage().addCookie(token);
    }

    public String getVersion() {
        return versionIndicator.getText();
    }

    public void clickOnNexage() {
        nexageBtn.click();
    }
    public void clickOnProjectViaIndex(int index){
        projectsList.get(index).click();
    }

    public void clickAddBtn() {
        addProjectBtn.click();
    }



    public List<String> getProjectsNameList() {
        List<String> projectsNameList = new ArrayList<>();
        for (int i = 0; i < projectsList.size(); i++) {
            projectsNameList.add(projectsList.get(i).getText());
        }
        return projectsNameList;
    }
}
