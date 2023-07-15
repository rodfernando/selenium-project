package com.project.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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
        // LandingPage landingPage = new LandingPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //max timeout
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        return driver;
    }

    // Conversor Json -> hashmap
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

        //Reading json to string:
        String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        //String to hashmap:
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
        });
        return data;
        //o objeto data ficará -> {map}, {map1}
    }

    public String getScreenShot(String testCaseName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File file = new File(System.getProperty("user.dir") + "\\evidences" + testCaseName + ".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
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