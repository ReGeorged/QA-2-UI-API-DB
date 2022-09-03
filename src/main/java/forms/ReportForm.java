package forms;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import java.util.List;

public class ReportForm extends Form {

    private ILabel reportName = getElementFactory().getLabel(By.xpath("//ol[@class='breadcrumb']//li[not(a)]"),"from name label");
    private List<ILabel> testsList =getElementFactory().findElements(By.xpath("//table[@id='allTests']//tbody//tr[(td)]"),"tests list", ElementType.LABEL);
   private ILabel loadingElement = getElementFactory().getLabel(By.xpath("//div[@class='messi-content']"),"loading window");

   public ReportForm() {
        super(By.xpath("//div[@id='pie']"), "report form");
    }
    public String getReportName(){
       return reportName.getText();
    }

    public int getTestListSize(){
        loadingElement.state().waitForNotDisplayed();
        return testsList.size();
    }
}
