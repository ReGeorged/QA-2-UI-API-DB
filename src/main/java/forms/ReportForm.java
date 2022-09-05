package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ReportForm extends Form {


    private ILabel reportName = getElementFactory().getLabel(By.xpath("//ol[@class='breadcrumb']//li[not(a)]"), "from name label");
    private List<ILabel> testsList = getElementFactory().findElements(By.xpath("//table[@id='allTests']//tbody//tr[(td)]"), "tests list", ElementType.LABEL);
    private ILabel loadingElement = getElementFactory().getLabel(By.xpath("//div[@class='messi-content']"), "loading window");
    private List<ILabel> startTimeList = getElementFactory().findElements(By.xpath("//table[@id='allTests']//tbody//tr[(td)]//td[4]"), "test start time list", ElementType.LABEL);
    private IButton addBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'pull-right')]"), "Add test button");
    private ILabel testsCountText = getElementFactory().getLabel(By.xpath("//li[@role='presentation']//a[contains(text(),'test')]"), "All running tests number");

    public ReportForm() {
        super(By.xpath("//div[@id='pie']"), "report form");
    }

    public String getReportName() {
        return reportName.getText();
    }

    public int getTestListSize() {
        loadingElement.state().waitForNotDisplayed();
        return testsList.size();
    }

    public String getStartDateByIndex(int index) {
        return startTimeList.get(index).getText();
    }

    public void waitForAddBtnToDisappear() {
        addBtn.state().waitForNotDisplayed();
    }

    public void waitForAddBtnAppear() {
        addBtn.state().waitForDisplayed();
    }

    public String getAllRunningTestsAsString() {
        return testsCountText.getText();
    }
}
