package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.StringUtils;

public class CardsCookiesNHelpForm extends Form {
    IButton pageIndicator = getElementFactory().getButton(By.xpath("//div[@class='page-indicator']"), "page indicator");
    IButton acceptCookiesBtn = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--transparent')]"), "accept cookies button");
    ITextBox cookiesBanner = getElementFactory().getTextBox(By.xpath("//div[@class='cookies']"), "cookies banner");
    ITextBox mainTimer = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'timer--center')]"), "main timer");

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

        String timerText = mainTimer.getText();
        int timeToInt = StringUtils.filteredToInteger(timerText);
        return timeToInt;
    }


}

