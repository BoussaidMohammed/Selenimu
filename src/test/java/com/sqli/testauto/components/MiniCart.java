package com.sqli.testauto.components;

import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MiniCart extends BaseComponent {
    @FindBy(xpath = "//ol[@id='mini-cart']")
    private WebElement listOfProducts;
    @FindBy(xpath = "//button[@id='top-cart-btn-checkout']")
    private WebElement checkoutButton;
    @FindBy(xpath = "//span[@class='counter qty']/span[@class = 'counter-number']")
    private WebElement productCounterIcon;


    public boolean isProductExistInCart(String product, int qty){
        boolean isExist = true;
        try{
            WebElement productElement = listOfProducts.findElement(By.xpath("//a[contains(text(), '"+product+"')]/ancestor::li[1]"));
            WebElement qtyElement = productElement.findElement(By.xpath(".//input[@data-item-qty = '"+qty+"']"));
        }catch (NoSuchElementException e){
            logger.info(e.getMessage());
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
