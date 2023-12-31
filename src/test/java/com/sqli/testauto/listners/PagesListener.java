package com.sqli.testauto.listners;

import com.sqli.testauto.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.WebDriverEventListener;

@SuppressWarnings({"deprecation"})
public class PagesListener implements WebDriverEventListener {
    @FindBy(xpath = "//*[@class = 'loader']")
    private WebElement loadingPageCafeIcon;
    @FindBy(xpath = "//*[@class='loading']")
    private WebElement loadingPageIcon;
    @FindBy(xpath = "//*/button[@id='_evidon-accept-button']")
    private WebElement cookiesButton;

    private String elementType;

    private BasePage basePage;

    public PagesListener(BasePage basePage) {
        this.basePage = basePage;
    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        System.out.println("after navigate to " + url);
        basePage.waitLoadingPage();
        basePage.clickCookiesButton();
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        elementType = element.getTagName();
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        if("a".equals(elementType) || "button".equals(elementType)){
            System.out.println(elementType + " is clicked");
            basePage.waitLoadingPage();
        }
    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }



    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {
    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

    }



    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        System.out.println("changing value of :: " + element);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {

    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void beforeSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void afterSwitchToWindow(String windowName, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> target) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {

    }

    @Override
    public void beforeGetText(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterGetText(WebElement element, WebDriver driver, String text) {

    }
}
