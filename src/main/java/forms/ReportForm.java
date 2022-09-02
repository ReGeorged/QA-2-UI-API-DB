package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ReportForm extends Form {

    private ILabel reportName = getElementFactory().getLabel(By.xpath("//ol[@class='breadcrumb']//li[not(a)]"),"from name label");
    private List<ILabel> testsList =getElementFactory().findElements(By.xpath("//table[@id='allTests']//tbody//tr"),"tests list", ElementType.LABEL);
    private ILabel allListsElement = getElementFactory().getLabel(By.xpath("//table[@id='allTests']//tbody"),"all lists holder element");

    public ReportForm() {
        super(By.xpath("//div[@id='pie']"), "report form");
    }
    public String getReportName(){
       return reportName.getText();
    }

    public int getTestListSize(){
        allListsElement.state().waitForDisplayed();
        allListsElement.getJsActions().scrollToTheCenter();
        return testsList.size();
    }
}
