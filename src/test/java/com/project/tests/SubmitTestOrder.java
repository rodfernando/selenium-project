package com.project.tests;

import com.project.pageobjects.*;
import com.project.utils.ChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubmitTestOrder extends ChromeTest {

    @Test
    void standAlone() throws InterruptedException {
        String nomeDoProduto1 = "ADIDAS ORIGINAL";
        String nomeDoProduto2 = "IPHONE 13 PRO";

        //Landing Page = tela de login
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goToURL();

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
}