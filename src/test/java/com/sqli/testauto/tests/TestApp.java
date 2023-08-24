package com.sqli.testauto.tests;

import com.sqli.testauto.listners.LoadingPageListener;
import com.sqli.testauto.pages.*;
import com.sqli.testauto.utils.PagesCommonActions;
import com.sqli.testauto.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

@SuppressWarnings({"deprecation"})
public class TestApp {
    private WebDriver driver;
    private CapsuleOriginaleCatalogue capsuleOriginaleCatalogue;
    private MachinesOriginaleCatalogue machinesOriginaleCatalogue;
    private ProductDetailPage pdp;
    private LandingPage landingPage;
    private NavBar navBar;
    private MiniCart miniCartPage;
    private Header header;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/chromedriver/chromedriver");
        driver = setupDriverAndPages();
        driver.get("https://ma.buynespresso.com/ma_fr/");
        driver.manage().window().maximize();
    }

    private WebDriver setupDriverAndPages() {
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("user-data-dir=/home/boussaidmohammed/.config/google-chrome/Default");*/
        WebDriver webDriver = new ChromeDriver(/*options*/);
        setupPages(webDriver);
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        LoadingPageListener loadingPageListener = new LoadingPageListener(new PagesCommonActions(webDriver));
        eventFiringWebDriver.register(loadingPageListener);


        //options.addArguments()
        return eventFiringWebDriver;
    }

    private void setupPages(WebDriver driver) {
        navBar = new NavBar(driver);
        miniCartPage = new MiniCart(driver);
        header = new Header(driver);
        Utilities.setDriver(driver);
    }

    @Test(priority = 0)
    public void navigateToCapsuleOriginalesCatalogue(){
        landingPage = new LandingPage(driver);
        navBar.cafesMenu();
        navBar.capsulesOriginalCataloguePage();
    }

   @Parameters({"capsule", "capsuleQty"})
   @Test(priority = 1)
    public void addcapsuleOriginalProductToCart(String product, int qty){
        capsuleOriginaleCatalogue = new CapsuleOriginaleCatalogue(driver);
        capsuleOriginaleCatalogue.clickAddToCartButton(product, qty);
        miniCartPage.waitUntilIconCounterShows();
    }

    @Test(priority = 2)
    public void navigateToMachineOriginalCatalogue(){
        navBar.machinesMenu();
        navBar.machinesOriginalCataloguePage();
    }

    @Parameters({"machine", "machineQty"})
    @Test(priority = 3)
    public void addMachineOriginalProductToCart(String product, int qty){
        machinesOriginaleCatalogue = new MachinesOriginaleCatalogue(driver);
        machinesOriginaleCatalogue.pdpOfProduct(product);
        pdp = new ProductDetailPage(driver);
        pdp.addProductToCart(qty);
        miniCartPage.waitUntilIconCounterChangeItsValue();
    }

    @Test(priority = 4)
    @Parameters({"capsule", "capsuleQty","machine", "machineQty"})
    public void checkProductInMinicart(String capsule, int capsuleQty, String machine, int machineQty){
        header.goToMiniCart();
        Assert.assertTrue(miniCartPage.isProductExistInCart(capsule, capsuleQty));
        Assert.assertTrue(miniCartPage.isProductExistInCart(machine, machineQty));
        miniCartPage.clickCheckoutButton();
    }

    @AfterTest
    public void tearDown(){
        Utilities.pause(10000);
        driver.quit();
    }

}


