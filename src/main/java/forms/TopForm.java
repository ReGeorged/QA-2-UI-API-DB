package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.actions.MouseActions;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TopForm extends Form {
    private IButton homeBtn = getElementFactory().getButton(By.xpath("//li//a"), "Home Button");    public TopForm() {
        super(By.xpath("//ol[@class='breadcrumb']"), "Top navigation menu");
    }

    public void moveMouseAndClick() {
        MouseActions mouseActions = new MouseActions(homeBtn, ElementType.BUTTON.name());
        mouseActions.moveMouseToElement();
        mouseActions.click();
    }
}
