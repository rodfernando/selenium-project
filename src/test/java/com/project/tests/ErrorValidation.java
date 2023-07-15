package com.project.tests;

import com.project.pageobjects.CartPage;
import com.project.pageobjects.ProductCatalogue;
import com.project.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ErrorValidation extends BaseTest {

    public ErrorValidation() throws IOException {
    }

    @Test (groups = {"ErrorHandling"}, dataProvider = "getWrongData") //ao acionar o grupo no xml, faz-se necessário criar anotação (alwaysRun = true) nos métodos que contém before e after na BaseTest
    public void loginErrorValidation(HashMap<String, String> input) throws InterruptedException, IOException {

        landingPage.loginApplication(input.get("email"), input.get("password"));
        Assert.assertEquals("Incorrect email or password.", landingPage.errorMessage());
    }

    @Test (dataProvider = "getData")
    void ProductErrorValidation(String email, String password, String product1, String product2) throws InterruptedException, IOException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);
        productCatalogue.addProductToCart(product1);
        productCatalogue.addProductToCart(product2);
        CartPage cartPage = productCatalogue.goToCartPage();

        //Verificando item no carrinho
        assertTrue(cartPage.verifyProductDisplay(product1));
        assertTrue(cartPage.verifyProductDisplay(product2));
    }

    @DataProvider
    public Object[][] getData() {

        return new Object[][] {{"tester@example.com", "Rodrigotester01", "ADIDAS ORIGINAL", "IPHONE 13 PRO"},
                                {"shetty@gmail.com", "Iamking@000", "ADIDAS ORIGINAL", "IPHONE 13 PRO"}};// aqui teremos dois teste. Se quisermos mais, só adicionar o {}
    }

    @DataProvider
    public Object[][] getWrongData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(
                System.getProperty("user.dir") + "\\src\\test\\java\\com\\project\\data\\LoginError.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}};

//        HashMap<String, String> mapWrong = new HashMap<>();
//        mapWrong.put("email", "tester@example.com");
//        mapWrong.put("password", "fdasfdafdafdsfdaf");
//
//        HashMap<String, String> mapWrong1 = new HashMap<>();
//        mapWrong1.put("email", "tester@example.com");
//        mapWrong1.put("password", "123");

    }
}