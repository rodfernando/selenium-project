package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    //PAGE OBJECT CLASS
    WebDriver driver;

    public ProductCatalogue(WebDriver driver) {
        super(driver); //Toda classe child deve ter o super da classe mãe
        this.driver = driver;
        PageFactory.initElements(driver, this); // necessário para triggar os @FindBy
    }


    @FindBy(css = ".mb-3")
    List<WebElement> itens;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    @FindBy (css = "[routerlink*='cart']")
    WebElement cartHeader;


    By itensBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-child");
    By toastMessage = By.xpath("//*[@id='toast-container']");
    By item = By.cssSelector("b");


    public List<WebElement> getItensList() {
        waitForElementToAppear(itensBy);
        return itens;
    }

    public WebElement getProductByName(String productName) {
        return getItensList().stream()
                .filter(i->i.findElement(item).getText().equals(productName))
                .findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        getProductByName(productName).findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear();
    }

    /*getItensList -> getProductByName -> addProductToCart
    * Observar o uso de uma função dentro de outra.
    * No fim, chamaremos SOMENTE a função addProductToCart*/

    public CartPage goToCartPage() {
        cartHeader.click();
        return new CartPage(driver);
    }

}
