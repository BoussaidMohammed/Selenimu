package com.sqli.testauto.components;

import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBar extends BaseComponent {

    @FindBy(xpath = "//a[contains(@href, 'cafe.html')]")
    private WebElement cafeMenuIcon;

    @FindBy(xpath = "//a[contains(@href,'cafe/original.html')]")
    private WebElement capsulesOriginalCafeCatalogueLink;

    @FindBy(xpath = "//a[contains(@href,'machines.html')]")
    private WebElement machineIcon;

    @FindBy(xpath = "//a[contains(@href,'machines/original.html')]")
    private WebElement machinesOriginalCafeCatalogueLink;

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
