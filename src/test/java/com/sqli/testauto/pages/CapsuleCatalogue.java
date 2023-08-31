package com.sqli.testauto.pages;

import com.sqli.testauto.exceptions.NegativeException;
import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CapsuleCatalogue extends BasePage{

    public void clickAddToCartButton(String product, int qty){
        if(qty < 0) {
            throw new NegativeException("Quantity must not be less or equal to zero");
        }
        WebElement liOfProduct =  driver.findElement(By.xpath("//a[contains(text(),'"+product+"')]/ancestor::li[1]"));
        Utilities.hoverOnElement(driver, liOfProduct);
        WebElement button = liOfProduct.findElement(By.xpath(".//div/form/button"));
        Utilities.hoverOnElement(driver, button);
        button.click();
        try{
            WebElement qtyButton = liOfProduct.findElement(By.xpath(".//li/span[@data-qtyitem = '"+qty+"']"));
            Utilities.hoverOnElement(driver, qtyButton);
            qtyButton.click();
        }catch (NoSuchElementException e){
            liOfProduct.findElement(By.xpath(".//input[@type= 'number']")).sendKeys(String.valueOf(qty));
            WebElement okButton = liOfProduct.findElement(By.xpath(".//button[contains(text(),'OK')]"));
            Utilities.hoverOnElement(driver, okButton);
            okButton.click();
        }
    }



}
