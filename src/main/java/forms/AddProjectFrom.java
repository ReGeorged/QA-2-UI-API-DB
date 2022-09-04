package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectFrom extends Form {
    private ITextBox projectName = getElementFactory().getTextBox(By.xpath("//input[@id='projectName']"),"Add project text box");

    private IButton saveProjectBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"),"Save project button");
    private ILabel successMessage = getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success')]"),"Project successfully saved message");

    public AddProjectFrom() {
        super(By.xpath("//div[contains(@id,'addProject')and@aria-hidden='false']"), "Add project Form");
    }

    public void addProject(String whatName){
        projectName.clearAndType(whatName);
    }
    public void clickSaveProjectBtn(){
        saveProjectBtn.click();
    }
    public boolean isProjectSaved(){
        return successMessage.state().isDisplayed();
    }

    //TODO leave this one or default isOpen()
    public boolean isFormOpen(){
        saveProjectBtn.state().waitForNotDisplayed();
        return saveProjectBtn.state().isDisplayed();

    }
}
