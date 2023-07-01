package com.project.tests;

import com.project.pageobjects.*;
import com.project.utils.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubmitTestOrder extends BaseTest {

    String nomeDoProduto1 = "ADIDAS ORIGINAL";
    String nomeDoProduto2 = "IPHONE 13 PRO";

    public SubmitTestOrder() throws IOException {
    }

    @Test
    void submitOrder() throws InterruptedException, IOException {


        ProductCatalogue productCatalogue = landingPage.loginApplication("tester@example.com", "Rodrigotester01");
        productCatalogue.addProductToCart(nomeDoProduto1);
        productCatalogue.addProductToCart(nomeDoProduto2);
        CartPage cartPage = productCatalogue.goToCartPage();

        assertTrue(cartPage.verifyProductDisplay(nomeDoProduto1)); //Verificando item no carrinho
        assertTrue(cartPage.verifyProductDisplay(nomeDoProduto2));
//Obs: somente as ações devem ir para o PageObject. Asserções/validações são feitas na página do teste

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Br");
        checkoutPage.submitOrder();

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        assertTrue(confirmationPage.confirmOrderText().equalsIgnoreCase("Thankyou for the order."));

    }

    // Esse teste executará automaticamente o teste submitOrder
    @Test (dependsOnMethods = {"submitOrder"})
    @DisplayName("Verify item on order tab")
    public void orderHistory() {

        ProductCatalogue productCatalogue = landingPage.loginApplication("tester@example.com", "Rodrigotester01");
        OrderPage orderPage = productCatalogue.goToOrderPage();
        assertTrue(orderPage.verifyOrderDisplay(nomeDoProduto1));
    }

}