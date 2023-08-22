package com.sqli.testauto.pages;

import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CapsuleOriginaleCatalogue {
    private WebDriver driver;
    private String product;
    private int qty;

    public CapsuleOriginaleCatalogue(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        product = "";
        qty = 0;
    }

    public void clickAddToCartButton(String product, int qty){
        setProductInfo(product,qty);
        //WebElement liOfProduct =  driver.findElement(By.xpath("//*/a[contains(@href,'"+this.product+"')]/../.."));
        WebElement liOfProduct =  driver.findElement(By.xpath("//a[contains(text(),'"+product+"')]/ancestor::li[1]"));
        Utilities.pause(4000);
        Utilities.hoverOnElement(driver, liOfProduct);
        WebElement button = liOfProduct.findElement(By.xpath(".//div/form/button"));
        button.click();

        try{
            liOfProduct.findElement(By.xpath(".//li/span[@data-qtyitem = '"+qty+"']")).click();
        }catch (NoSuchElementException e){
            liOfProduct.findElement(By.xpath(".//input[@type= 'number']")).sendKeys(String.valueOf(qty));
            liOfProduct.findElement(By.xpath(".//button[contains(text(),'OK')]")).click();
        }
    }

    private void setProductInfo(String product, int qty){
        if(qty < 0){
            throw new NegativeException("Quantity must not be less or equal to zero");
        }else{
            this.product = product;
            this.qty = qty;
        }
    }

  /*  private String convertString(String productName){
        String[] words = productName.trim().split(" ");
        productName = Arrays.stream(words).map(s -> s.toLowerCase()).collect(Collectors.joining("-"));
        System.out.println(productName);
        return productName;
    } */


}
