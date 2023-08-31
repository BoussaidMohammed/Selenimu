package com.sqli.testauto.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Header extends BaseComponent {
    @FindBy(xpath = "//a[contains(@href,'/cart')]")
    private WebElement cartButton;
    @FindBy(xpath = "//a[@class='logo']")
    private WebElement landingPageLink;


    public void goToMiniCart(){
        cartButton.click();
    }

    public void goToLandingPage(){
        landingPageLink.click();
    }
}
