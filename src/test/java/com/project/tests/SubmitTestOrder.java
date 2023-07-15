package com.project.tests;

import com.project.pageobjects.*;
import com.project.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubmitTestOrder extends BaseTest {

    public SubmitTestOrder() throws IOException {
    }

    @Test (dataProvider = "getData", groups = {"Purchase"})
    void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        productCatalogue.addProductToCart(input.get("product1"));
        productCatalogue.addProductToCart(input.get("product2"));
        CartPage cartPage = productCatalogue.goToCartPage();

        assertTrue(cartPage.verifyProductDisplay(input.get("product1"))); //Verificando item no carrinho
        assertTrue(cartPage.verifyProductDisplay(input.get("product2")));
//Obs: somente as ações devem ir para o PageObject. Asserções/validações são feitas na página do teste

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Br");
        checkoutPage.submitOrder();

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        assertTrue(confirmationPage.confirmOrderText().equalsIgnoreCase("Thankyou for the order."));

    }

    // Esse teste executará automaticamente o teste submitOrder
    @Test (dependsOnMethods = {"submitOrder"}, dataProvider = "getData")
    @DisplayName("Verify item on order tab")
    public void orderHistory(HashMap<String, String> input) {

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrderPage();
        assertTrue(orderPage.verifyOrderDisplay(input.get("product1")));
        assertTrue(orderPage.verifyOrderDisplay(input.get("product2")));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        //Método usando o conversor
        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "\\src\\test\\java\\com\\project\\data\\PuchaseOrder.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};

        //Método manual:
//        HashMap<String, String> map = new HashMap<>();
//        map.put("email", "tester@example.com");
//        map.put("password", "Rodrigotester01");
//        map.put("product1", "ADIDAS ORIGINAL");
//        map.put("product2", "IPHONE 13 PRO");
//
//        HashMap<String, String> map1 = new HashMap<>();
//        map1.put("email", "shetty@gmail.com");
//        map1.put("password", "Iamking@000");
//        map1.put("product1", "ADIDAS ORIGINAL");
//        map1.put("product2", "IPHONE 13 PRO");
    }

}