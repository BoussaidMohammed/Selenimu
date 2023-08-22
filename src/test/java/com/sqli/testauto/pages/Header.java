package com.sqli.testauto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    @FindBy(xpath = "//div/a[contains(@href,'/cart')]")
    private WebElement cartButton;
    @FindBy(xpath = "//a[@class='logo']")
    private WebElement landingPageLink;
    private WebDriver driver;
    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToMiniCart(){
        cartButton.click();
    }

    public void goToLandingPage(){
        landingPageLink.click();
    }
}
