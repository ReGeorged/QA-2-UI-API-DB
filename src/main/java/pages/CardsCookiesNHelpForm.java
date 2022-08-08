package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.StringUtils;

public class CardsCookiesNHelpForm extends Form {
    private IButton pageIndicator = getElementFactory().getButton(By.xpath("//div[@class='page-indicator']"), "page indicator");
    private IButton acceptCookiesBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--transparent')]"), "accept cookies button");
    private ITextBox cookiesBanner = getElementFactory().getTextBox(By.xpath("//div[@class='cookies']"), "cookies banner");
    private ITextBox mainTimer = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'timer--center')]"), "main timer");

    public CardsCookiesNHelpForm() {
        super(By.xpath("//span[@class='highlight']"), "send to bottom button");
    }


    public String getCurrentPage() {
        return pageIndicator.getText();
    }

    public void acceptCookies() {
        acceptCookiesBtn.click();
    }


    public boolean isCookiesBannerDisplayed() {
        return cookiesBanner.state().isDisplayed();
    }

    public Integer getTimerTime() {
        AqualityServices.getLogger().info("Getting timer time");
        String timerText = mainTimer.getText();
        int timeToInt = StringUtils.filteredToInteger(timerText);
        return timeToInt;
    }
}

