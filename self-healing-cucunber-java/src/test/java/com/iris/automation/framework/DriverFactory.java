package com.iris.automation.framework;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    public WebDriver init_driver(String browser) {
        System.out.println("browser value is: " + browser);

        if (browser == null || browser.trim().isEmpty()) {
            throw new IllegalArgumentException("Browser value is missing in config.properties");
        }

        ConfigReader configReader = new ConfigReader();
        boolean selfHealingEnabled = Boolean.parseBoolean(
                configReader.init_prop().getProperty("selfHealing", "false").trim()
        );

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriver chromeDelegate = new ChromeDriver(chromeOptions);
                driver = selfHealingEnabled ? SelfHealingDriver.create(chromeDelegate) : chromeDelegate;
                break;
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                WebDriver firefoxDelegate = new FirefoxDriver(firefoxOptions);
                driver = selfHealingEnabled ? SelfHealingDriver.create(firefoxDelegate) : firefoxDelegate;
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                WebDriver edgeDelegate = new EdgeDriver(edgeOptions);
                driver = selfHealingEnabled ? SelfHealingDriver.create(edgeDelegate) : edgeDelegate;
                break;
            default:
                throw new IllegalArgumentException("Please pass the right browser value: " + browser);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        return driver;
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
