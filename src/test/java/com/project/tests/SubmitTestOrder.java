package com.project.tests;

import com.project.pageobjects.LandingPage;
import com.project.pageobjects.ProductCatalogue;
import com.project.utils.ChromeTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
        landingPage.loginApplication("tester@example.com", "Rodrigotester01");

        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        List<WebElement> products = productCatalogue.getItensList();

        productCatalogue.addProductToCart(nomeDoProduto1);
//        Thread.sleep(5000);
        productCatalogue.addProductToCart(nomeDoProduto2);



//        //Quantidade de itens no carrinho
//        List<WebElement> itensCart = driver.findElements(By.cssSelector(".cartSection h3"));
//
//
//       boolean match1 = itensCart.stream().anyMatch(i->i.getText().equalsIgnoreCase(nomeDoProduto1));
//       boolean match2 = itensCart.stream().anyMatch(i->i.getText().equalsIgnoreCase(nomeDoProduto2));
//       assertTrue(match1);
//       assertTrue(match2);
//
//       /*Se eu quero encontrar um elemento usando stream, uso o filter. Caso queira somente verificar, uso anyMatch*/
//
//        driver.findElement(By.xpath("//button[normalize-space()='Checkout']")).click();
//
//
//
//        //Tela Checkout
//        Actions a = new Actions(driver);
//        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Br").build().perform();
//        //Espera para aparecer a lista na tela:
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
//        driver.findElement(By.xpath("//span[normalize-space()='Brazil']")).click();
//        driver.findElement(By.cssSelector(".action__submit")).click();
//        String confirmOrder = driver.findElement(By.cssSelector("h1")).getText();
//        assertTrue(confirmOrder.equalsIgnoreCase("Thankyou for the order."));
//    }


    }
}