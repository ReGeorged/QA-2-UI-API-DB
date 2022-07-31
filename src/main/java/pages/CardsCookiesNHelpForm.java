package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;
import utils.StringUtils;

public class CardsCookiesNHelpForm {
    IElementFactory elementFactory = AqualityServices.getElementFactory();
    IButton pageIndicator = elementFactory.getButton(By.xpath("//div[@class='page-indicator']"), "page indicator");
    IButton acceptCookiesBtn = elementFactory.getButton(By.xpath("//button[contains(@class,'button--transparent')]"), "accept cookies button");
    ITextBox cookiesBanner = elementFactory.getTextBox(By.xpath("//div[@class='cookies']"),"cookies banner");
    ITextBox mainTimer = elementFactory.getTextBox(By.xpath("//div[contains(@class,'timer--center')]"),"main timer");


    public String getCurrentPage() {

        return pageIndicator.getText();
    }


    public void acceptCookies() {
        acceptCookiesBtn.click();
    }



    public boolean checkCookiesBanner(){
        return cookiesBanner.state().isDisplayed();
    }

    public Integer getTimerTime(){

        String timerText = mainTimer.getText();
        Integer timeToInt = StringUtils.filteredToInteger(timerText);
        return timeToInt;
    }


}

