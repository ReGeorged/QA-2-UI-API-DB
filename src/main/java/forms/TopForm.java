package forms;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TopForm extends Form {
    protected TopForm() {
        super(By.xpath("//ol[@class='breadcrumb']"), "Top navigation menu");
    }
}
