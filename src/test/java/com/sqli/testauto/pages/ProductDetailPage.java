package com.sqli.testauto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {
    @FindBy(xpath = "//button[contains(@class,'action tocart primary list pdpcart-btn')]")
    private WebElement addToCartButton;
    private WebDriver driver;
    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(int qty){
        addToCartButton.click();
        try{
            driver.findElement(By.xpath(".//li/span[@data-qtyitem = '"+qty+"']")).click();
        }catch (NoSuchElementException e){
            driver.findElement(By.xpath(".//input[@type= 'number']")).sendKeys(String.valueOf(qty));
            driver.findElement(By.xpath(".//button[contains(text(),'OK')]")).click();
        }
    }
}
