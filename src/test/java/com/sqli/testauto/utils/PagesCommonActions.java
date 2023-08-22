package com.sqli.testauto.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PagesCommonActions {
    @FindBy(xpath = "//*[@class = 'loader']")
    private WebElement loadingPageCafeIcon;
    @FindBy(xpath = "//*[@class='loading']")
    private WebElement loadingPageIcon;
    @FindBy(xpath = "//*/button[@id='_evidon-accept-button']")
    private WebElement cookiesButton;

    private WebDriver driver;
    public PagesCommonActions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitLoadingPage(){
        Utilities.waitUntilInvisibilityOf(loadingPageCafeIcon);
        Utilities.waitUntilInvisibilityOf(loadingPageIcon);
    }

    public void clickCookiesButton(){
        cookiesButton.click();
    }

}
