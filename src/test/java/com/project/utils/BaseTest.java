package com.project.utils;

import com.project.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\project\\utils\\GlobalData.properties");
        properties.load(fis);
        String browserName = properties.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            //firefox
        }
//        LandingPage landingPage = new LandingPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //max timeout
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        return driver;
    }

    @BeforeMethod (alwaysRun = true)
    public LandingPage launchDriver() throws IOException {
        driver = initializeDriver();
        //Landing Page = tela de login
        landingPage = new LandingPage(driver);
        landingPage.goToURL();
        return landingPage;
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}