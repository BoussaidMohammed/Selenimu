package com.sqli.testauto.pages;

import com.aventstack.extentreports.ExtentTest;
import com.sqli.testauto.components.Header;
import com.sqli.testauto.components.MiniCart;
import com.sqli.testauto.components.NavBar;
import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    @FindBy(xpath = "//*[@class = 'loader']")
    private WebElement loadingPageCafeIcon;
    @FindBy(xpath = "//*[@class='loading']")
    private WebElement loadingPageIcon;
    @FindBy(xpath = "//*/button[@id='_evidon-accept-button']")
    private WebElement cookiesButton;
    protected static WebDriver driver;
    public final Header header;
    public final NavBar navBar;
    public final MiniCart miniCart;
    protected static ExtentTest logger;

    public BasePage() {
        PageFactory.initElements(driver, this);
        header = new Header();
        navBar = new NavBar();
        miniCart = new MiniCart();
    }

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    public static void setLogger(ExtentTest logger) {
        BasePage.logger = logger;
    }

    public void waitLoadingPage(){
        Utilities.waitUntilInvisibilityOf(loadingPageCafeIcon);
        Utilities.waitUntilInvisibilityOf(loadingPageIcon);
        Utilities.waitPageToLoadCompletely();
    }

    public void clickCookiesButton(){
        cookiesButton.click();
    }
}
