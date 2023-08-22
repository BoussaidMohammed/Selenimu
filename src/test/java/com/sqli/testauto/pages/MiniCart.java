package com.sqli.testauto.pages;

import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MiniCart {
    @FindBy(xpath = "//ol[@id='mini-cart']")
    private WebElement listOfProducts;
    @FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
    private WebElement checkoutButton;
    @FindBy(xpath = "//span[@class='counter qty']/span[@class = 'counter-number']")
    private WebElement productCounterIcon;
    private WebDriver driver;
    public MiniCart(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean isProductExistInCart(String product, int qty){
        boolean isExist = true;
        try{
            WebElement productElement = listOfProducts.findElement(By.xpath("//a[contains(text(), '"+product+"')]/ancestor::li[1]"));
            WebElement qtyElement = productElement.findElement(By.xpath(".//input[@data-item-qty = '"+qty+"']"));
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            isExist = false;
        }
        return isExist;
    }

    public void clickCheckoutButton(){
        checkoutButton.click();
    }

    public void waitUntilIconCounterShows(){
        Utilities.waitUntilVisibilityOf(productCounterIcon);
    }

    public void waitUntilIconCounterChangeItsValue(){
        String value = productCounterIcon.getText();
        Utilities.waitUntilTextOfElementChange(productCounterIcon,value);
    }

}
