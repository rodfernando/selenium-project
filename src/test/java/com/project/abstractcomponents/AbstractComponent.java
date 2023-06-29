package com.project.abstractcomponents;

import com.project.pageobjects.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        //passando o driver do super para esta classe:
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy (css = "[routerlink*='cart']")
    WebElement cartHeader;



    public void goToCheckoutPage() {

    }

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }


    public void waitForElementToAppear(By findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElementToDisappear() throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        wait.until(ExpectedConditions.invisibilityOf(element));
        //A aplicação tem um problema ao desaparecer o elemento
        Thread.sleep(1000);
    }
}
