package com.project.tests;

import com.project.pageobjects.CartPage;
import com.project.pageobjects.CheckoutPage;
import com.project.pageobjects.ConfirmationPage;
import com.project.pageobjects.ProductCatalogue;
import com.project.utils.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorValidation extends BaseTest {

    public ErrorValidation() throws IOException {
    }

    @Test (groups = {"ErrorHandling"}) //ao acionar o grupo no xml, faz-se necessário criar anotação (alwaysRun = true) nos métodos que contém before e after na BaseTest
    void loginErrorValidation() throws InterruptedException, IOException {
        String nomeDoProduto1 = "ADIDAS ORIGINAL";
        String nomeDoProduto2 = "IPHONE 13 PRO";

        landingPage.loginApplication("tester@example.com", "fdasfdafdafdsfdaf");
        Assert.assertEquals("Incorrect email or password.", landingPage.errorMessage());
    }

    @Test
    void ProductErrorValidation() throws InterruptedException, IOException {
        String nomeDoProduto1 = "ADIDAS ORIGINAL";
        String nomeDoProduto2 = "IPHONE 13 PRO";

        ProductCatalogue productCatalogue = landingPage.loginApplication("tester@example.com", "Rodrigotester01");
        productCatalogue.addProductToCart(nomeDoProduto1);
        productCatalogue.addProductToCart(nomeDoProduto2);
        CartPage cartPage = productCatalogue.goToCartPage();

        //Verificando item no carrinho
        assertTrue(cartPage.verifyProductDisplay(nomeDoProduto1));
        assertTrue(cartPage.verifyProductDisplay(nomeDoProduto2));
    }
}