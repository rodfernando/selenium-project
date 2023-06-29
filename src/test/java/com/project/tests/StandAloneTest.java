package com.project.tests;

import com.project.pageobjects.LandingPage;
import com.project.utils.ChromeTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StandAloneTest extends ChromeTest {

    @Test
    void standAlone() throws InterruptedException {
        String nomeDoProduto1 = "ADIDAS ORIGINAL";
        String nomeDoProduto2 = "IPHONE 13 PRO";

        //Landing Page = tela de login
        LandingPage landingPage = new LandingPage(driver);

        driver.get("https://www.rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("tester@example.com");
        driver.findElement(By.id("userPassword")).sendKeys("Rodrigotester01");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
        // Assertion do toast de login
        String loging = driver.findElement(By.xpath("//*[@id='toast-container']")).getText();
        assertEquals(loging, "Login Successfully");

        //Espera dos produtos aparecerem na tela, em específico o webelement:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> itens = driver.findElements(By.cssSelector(".mb-3"));

        WebElement item1 = itens.stream()
                .filter(i -> i.findElement(By.cssSelector("b")).getText().equals(nomeDoProduto1))
                .findFirst().orElse(null); // isso achará o card que tem o adidas original
        item1.findElement(By.cssSelector(".card-body button:last-child")).click();

        Thread.sleep(5000);

        WebElement item2 = itens.stream()
                .filter(i -> i.findElement(By.cssSelector("b")).getText().equals(nomeDoProduto2))
                .findFirst().orElse(null);
        item2.findElement(By.cssSelector(".card-body button:last-child")).click();


        //buscando pelo toast após adicionar item no carrinho:
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
        String msg = driver.findElement(By.xpath("//*[@id='toast-container']")).getText();
        assertEquals(msg, "Product Added To Cart");

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        //Quantidade de itens no carrinho
        List<WebElement> itensCart = driver.findElements(By.cssSelector(".cartSection h3"));

        boolean match1 = itensCart.stream().anyMatch(i -> i.getText().equalsIgnoreCase(nomeDoProduto1));
        boolean match2 = itensCart.stream().anyMatch(i -> i.getText().equalsIgnoreCase(nomeDoProduto2));
        assertTrue(match1);
        assertTrue(match2);

        /*Se eu quero encontrar um elemento usando stream, uso o filter. Caso queira somente verificar, uso anyMatch*/


        //Tela Checkout
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "Br").build().perform();
        //Espera para aparecer a lista na tela:
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
        driver.findElement(By.xpath("//span[normalize-space()='Brazil']")).click();
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmOrder = driver.findElement(By.cssSelector("h1")).getText();
        assertTrue(confirmOrder.equalsIgnoreCase("Thankyou for the order."));
    }


}
