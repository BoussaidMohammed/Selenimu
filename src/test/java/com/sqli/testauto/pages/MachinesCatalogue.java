package com.sqli.testauto.pages;

import org.openqa.selenium.By;

public class MachinesCatalogue extends BasePage{
    public void pdpOfProduct(String product){
        driver.findElement(By.xpath("//a[contains(text(), '"+product+"')]")).click();
    }
}
