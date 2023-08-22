package com.sqli.testauto;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class Second {
    WebDriver driver;
    @BeforeTest
    public void init() {
       // System.setProperty("webdriver.gecko.driver", "src/chromedriver/geckodriver");
      /*  ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");*/
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://ma.buynespresso.com/ma_fr/cafe/original.html");
        driver.manage().window().maximize();
        new WebDriverWait(driver, Duration.ofSeconds(40, 40)).until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//*[@class = 'loading']"))));
        driver.findElement(By.xpath("//*/button[@id='_evidon-accept-button']")).click();//coockies button must be clicked
    }
    @Test
    public void test(){

        //WebElement e = driver.findElement(By.xpath("//*/a[@class=\"dropdown-item\"]"));
     // WebElement e = driver.findElement(By.xpath("//*/a[contains(@href,'filter-style-mild')]/../..//div/form/button"));
        WebElement li =  driver.findElement(By.xpath("//a[contains(@href, 'filter-style-mild')]/ancestor::li[1]"));
        System.out.println(li.getTagName());
        WebElement button = li.findElement(By.xpath(".//div/form/button"));
        button.click();
        System.out.println(li.getTagName());

        WebElement qty = li.findElement(By.xpath(".//li/span[@data-qtyitem = '12']"));
        System.out.println(qty.getTagName());
        qty.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement cartButton = driver.findElement(By.xpath("//div/a[contains(@href,'/cart')]"));
        cartButton.click();
        ////*[@id="mini-cart"]/li/div/div/strong[1]/a

        WebElement listProduct = driver.findElement(By.xpath("//ol[@id='mini-cart']"));

        WebElement product = listProduct.findElement(By.xpath(".//a[contains(@href, 'filter-style-mild')]/ancestor::li[1]"));
        WebElement qtyElement = product.findElement(By.xpath(".//input[@data-item-qty = '12']"));
////*[@id="top-cart-btn-checkout"]
        System.out.println(product.getTagName()+ " :: " + product.getText());
        System.out.println(qtyElement.getTagName()+ " :: " + qtyElement.getText());
        WebElement proceedToCheckButton = driver.findElement(By.xpath("//button[@id='top-cart-btn-checkout']"));
        proceedToCheckButton.click();
    }

    @AfterTest
    public void closeSession(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //driver.quit();
    }
}