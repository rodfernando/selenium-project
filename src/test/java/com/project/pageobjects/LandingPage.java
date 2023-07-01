package com.project.pageobjects;

import com.project.abstractcomponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    //PAGE OBJECT CLASS
    WebDriver driver;

    public LandingPage(WebDriver driver) {
        super(driver); //Para distribuir o driver na classe AbstractComponent, temos que criar um super e mandar um construtor para la
        this.driver = driver;
        PageFactory.initElements(driver, this); // necessário para triggar os @FindBy
    }

    /*Podemos declarar uma variável do modo normal:
    WebElement userName =  driver.findElement(By.id("userEmail"));
    * ou pode-se usar o design pattern PageObject:
    */

    @FindBy(id="userEmail")
    WebElement userName;

    @FindBy(id="userPassword")
    WebElement password;

    @FindBy(id="login")
    WebElement submit;
    //Para ver como é escrito os localizadores, basta clicar no @FindBy + CTRL. Lá estará descrito o modo exato

    @FindBy(css="div[aria-label='Incorrect email or password.']")
    WebElement errorMessage;



    public void goToURL() {

        driver.get("https://www.rahulshettyacademy.com/client");
    }

    public String errorMessage() {

        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();
    }

    public ProductCatalogue loginApplication(String email, String passwordKey) {

        userName.sendKeys(email);
        password.sendKeys(passwordKey);
        submit.click();
        // Se vamos para a página de catálogos, inicia-se o objeto diretamente nesta etapa:
        return new ProductCatalogue(driver);
        /*
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
        */

        // Na Classe SubmitTestOrder, é lançado -> ProductCatalogue productCatalogue = landingPage.loginApplication("tester@example.com", "Rodrigotester01");
        // Isso define a transição de uma página para a outra
    }




}
