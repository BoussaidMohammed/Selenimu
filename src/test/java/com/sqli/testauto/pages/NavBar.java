package com.sqli.testauto.pages;

import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavBar {
    private final WebDriver driver;
    @FindBy(xpath = "//a[contains(@href, 'cafe.html')]")
    private WebElement cafeMenuIcon;

    @FindBy(xpath = "//a[contains(@href,'cafe/original.html')]")
    private WebElement capsulesOriginalCafeCatalogueLink;

    @FindBy(xpath = "//a[contains(@href,'machines.html')]")
    private WebElement machineIcon;

    @FindBy(xpath = "//a[contains(@href,'machines/original.html')]")
    private WebElement machinesOriginalCafeCatalogueLink;

    public NavBar(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void cafesMenu(){
        Utilities.hoverOnElement(driver, cafeMenuIcon);
    }

    public void capsulesOriginalCataloguePage(){
        capsulesOriginalCafeCatalogueLink.click();
    }

    public void machinesMenu(){
        Utilities.hoverOnElement(driver, machineIcon);
    }

    public void machinesOriginalCataloguePage(){
        machinesOriginalCafeCatalogueLink.click();
    }


}
