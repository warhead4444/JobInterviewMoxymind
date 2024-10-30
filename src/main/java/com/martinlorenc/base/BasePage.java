package com.martinlorenc.base;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class BasePage {
    protected AppiumDriver driver;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element not found: " + locator);
            throw e;
        }
    }

    public void clickElement(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }
}
