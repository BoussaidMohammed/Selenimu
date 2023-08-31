package com.sqli.testauto.tests;

import com.sqli.testauto.pages.CapsuleCatalogue;
import com.sqli.testauto.pages.LandingPage;
import com.sqli.testauto.pages.MachinesCatalogue;
import com.sqli.testauto.pages.ProductDetailPage;
import com.sqli.testauto.utils.Utilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddingProductsToMiniCartTest extends BaseTest{
    private CapsuleCatalogue capsuleCatalogue;
    private MachinesCatalogue machinesCatalogue;
    private ProductDetailPage pdp;
    private LandingPage landingPage;

    @Test(priority = 0)
    public void navigateToMachineOriginalCatalogue(){
        landingPage = new LandingPage();
        landingPage.navBar.machinesMenu();
        landingPage.navBar.machinesOriginalCataloguePage();
    }

    @Parameters({"machine", "machineQty"})
    @Test(priority = 1)
    public void addMachineOriginalProductToCart(String product, int qty){
        machinesCatalogue = new MachinesCatalogue();
        machinesCatalogue.pdpOfProduct(product);
        pdp = new ProductDetailPage();
        pdp.addProductToCart(qty);
        pdp.miniCart.waitUntilIconCounterShows();
    }

    @Test(priority = 2)
    public void navigateToCapsuleOriginalesCatalogue(){
        landingPage.navBar.cafesMenu();
        landingPage.navBar.capsulesOriginalCataloguePage();
    }

    @Parameters({"capsule", "capsuleQty"})
    @Test(priority = 3)
    public void addcapsuleOriginalProductToCart(String product, int qty){
        capsuleCatalogue = new CapsuleCatalogue();
        capsuleCatalogue.clickAddToCartButton(product, qty);
        capsuleCatalogue.miniCart.waitUntilIconCounterChangeItsValue();
    }

    @Test(priority = 4)
    @Parameters({"capsule", "capsuleQty","machine", "machineQty"})
    public void checkProductInMinicart(String capsule, int capsuleQty, String machine, int machineQty){
        pdp.header.goToMiniCart();
        Assert.assertTrue(pdp.miniCart.isProductExistInCart(capsule, capsuleQty));
        Assert.assertTrue(pdp.miniCart.isProductExistInCart(machine, machineQty));
        pdp.miniCart.clickCheckoutButton();
    }
}
