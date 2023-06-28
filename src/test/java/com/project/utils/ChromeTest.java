package com.project.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ChromeTest {

    public static WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //max timeout
        driver.manage().deleteAllCookies();
    }

//    @AfterEach
//    void teardown() {
//        driver.quit();
//    }

    // Alternative
//    @BeforeEach
//    void setup() {
//        driver = WebDriverManager.chromedriver().create();
//
//    }
}
