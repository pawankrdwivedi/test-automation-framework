package com.iris.automation.test;

import com.iris.automation.framework.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class SampleAppStepDefinition {
    private WebDriver driver;
    private final By TITLE = By.id("title");
    private final By FIRST_NAME = By.id("firstName");
    private final By LAST_NAME = By.id("lastName");
    private final By EMAIL_ADDRESS = By.id("email");
    private final By ADDRESS = By.id("address");
    private final By PHONE = By.id("phone");
    private final By ZIP = By.id("zip");
    private final By COMPANY_NOTIFICATION = By.id("companyNotification");
    private final By CANCEL_BUTTON = By.id("cancelBtn");
    private final By SUBMIT_BUTTON = By.id("submitBtn");

    @Given("user is on sample page")
    public void user_is_on_sample_page() {
        driver = DriverFactory.getDriver();
        String appPath = System.getProperty("user.dir") + File.separator + "sample_app"
                + File.separator + "Dynamic_Page.html";
        driver.get("file:///" + appPath);
        sleep();
    }

    @When("user selects title {string}")
    public void user_selects_title(String title) {
        WebElement selectElement = driver.findElement(TITLE);
        Select select = new Select(selectElement);
        select.selectByVisibleText(title);
        sleep();
    }

    @When("user enters first name {string}")
    public void user_enters_first_name(String fname) {
        driver.findElement(FIRST_NAME).sendKeys(fname);
        sleep();
    }

    @When("user enters last name {string}")
    public void user_enters_last_name(String text) {
        driver.findElement(LAST_NAME).sendKeys(text);
        sleep();
    }

    @When("user enters email {string}")
    public void user_enters_email(String text) {
        driver.findElement(EMAIL_ADDRESS).sendKeys(text);
        sleep();
    }

    @When("user enters address {string}")
    public void user_enters_address(String text) {
        driver.findElement(ADDRESS).sendKeys(text);
        sleep();
    }

    @When("user enters phone {string}")
    public void user_enters_phone(String text) {
        driver.findElement(PHONE).sendKeys(text);
        sleep();
    }

    @When("user enters zip code {string}")
    public void user_enters_zip_code(String text) {
        driver.findElement(ZIP).sendKeys(text);
        sleep();
    }

    @When("select receive company notification")
    public void user_selects_company_notification() {
        WebElement checkbox = driver.findElement(COMPANY_NOTIFICATION);
        if (!checkbox.isSelected()) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", checkbox);
            checkbox.click();
            sleep();
        }
    }

    @When("click on Submit")
    public void user_clicks_on_submit() {
        WebElement submitButton = driver.findElement(SUBMIT_BUTTON);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", submitButton);   
        submitButton.click();
        sleep();
    }

    @When("click on Cancel")
    public void user_clicks_on_cancel() {
        WebElement cancelButton = driver.findElement(CANCEL_BUTTON);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", cancelButton);
        cancelButton.click();
        try {
            Alert alert_popup = driver.switchTo().alert();
            alert_popup.accept();
        } catch (NoAlertPresentException e) {
            // no-op
        }
    }

    @Then("form is cleared")
    public void new_tab_is_opened() {
    }

    public void sleep() {
        try {
            Thread.sleep(750L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
