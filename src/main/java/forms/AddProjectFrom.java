package forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectFrom extends Form {
    private ITextBox projectNameField = getElementFactory().getTextBox(By.xpath("//input[@class='form-control']"), "Enter project name field");
    private IButton saveBtn = getElementFactory().getButton(By.xpath("//button[@type='submit']"), "Save project Button");
    private ILabel successMessage = getElementFactory().getLabel(By.xpath("//div[contains(@class,'alert-success')]"), "Project saved Successfully message");

    public AddProjectFrom() {
        super(By.xpath("//form[@id='addProjectForm']"), "Add project Form");
    }

    public void createNewProjectAndSave(String projectName) {
        projectNameField.clearAndType(projectName);
        saveBtn.click();
    }

    public boolean isProjectSaved() {
        return successMessage.state().isDisplayed();
    }
}
