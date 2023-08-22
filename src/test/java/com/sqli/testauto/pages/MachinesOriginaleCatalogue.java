package com.sqli.testauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MachinesOriginaleCatalogue {
    private WebDriver driver;
    public  MachinesOriginaleCatalogue(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void pdpOfProduct(String product){
        driver.findElement(By.xpath("//a[contains(text(), '"+product+"')]")).click();
    }
}
