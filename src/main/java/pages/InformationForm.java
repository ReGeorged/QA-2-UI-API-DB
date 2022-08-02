package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.IntUtils;
import java.util.List;


public class InformationForm extends Form {

    public InformationForm() {
        super(By.xpath("//p[@class='avatar-and-interests__text']"), "avatar and interest box");
    }

    ICheckBox unselectAllBox = getElementFactory().getCheckBox(By.xpath("//label[@for='interest_unselectall']//span//span"), "unselect all checkbox");
    IButton nextBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--fluid') and contains(@class,'button--stroked')]"),"next button");
    IButton uploadBtn = getElementFactory().getButton(By.xpath("//a[@class='avatar-and-interests__upload-button']"), "upload button");
    List<ICheckBox> checkBoxes = getElementFactory().findElements(By.xpath("//span[@class='checkbox small']//label[not(@for='interest_selectall') and not(@for='interest_unselectall')]"), ElementType.CHECKBOX);
    ILabel uploadedImage = getElementFactory().getLabel(By.xpath("//div[@class='avatar-and-interests__avatar-image']"),"uploaded image");

    public  void selectBox(){
        unselectAllBox.getJsActions().scrollToTheCenter();
        unselectAllBox.check();
        IntUtils.randomICheckBoxFromList(checkBoxes,2);
    }
    public void clickNextBtn(){
        nextBtn.click();
    }

    public void uploadFile() {
        uploadBtn.getJsActions().scrollToTheCenter();
        uploadBtn.click();
    }

    public void waitForImageToUpload(){
        uploadedImage.state().waitForDisplayed();
    }

}
