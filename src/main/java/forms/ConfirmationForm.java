package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.ElementUtils;

import java.util.List;

public class ConfirmationForm extends Form {
    private List<ICheckBox> checkBoxes = getElementFactory().findElements(By.xpath("//label//input[@type='checkbox']/parent::label"), ElementType.CHECKBOX);
    private IButton submitBtn = getElementFactory().getButton(By.xpath("//form[@id='subscription-modale']//input[@type='submit']"), "submit button in additional information form");
    private ICheckBox droebitiBtn = getElementFactory().getCheckBox(By.xpath("//*[@id=\"subscription-modale\"]/div[2]/div[3]/div[2]/ul/li[1]/label"), "droebitibtn");

    public ConfirmationForm() {
        super(By.xpath("//div[@id='additional-data-modal']"), "additional information form");
    }

    public void checkRandom() {
        droebitiBtn.state().waitForDisplayed();
        droebitiBtn.getJsActions().scrollToTheCenter();
        ElementUtils.randomElementFromList(checkBoxes, 1).check();
    }


    public void clickOnSubmitBtn() {
        submitBtn.state().waitForClickable();
        submitBtn.getJsActions().scrollToTheCenter();
        submitBtn.click();
    }

    public void clickOndroebiti() {
        droebitiBtn.check();
    }
}
