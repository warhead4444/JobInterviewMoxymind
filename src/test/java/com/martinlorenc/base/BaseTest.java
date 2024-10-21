package com.martinlorenc.base;

import com.martinlorenc.pages.CalendarPage;
import com.martinlorenc.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected AppiumDriver driver;
    protected CalendarPage calendarPage;


    @BeforeEach
    public void setUp() throws MalformedURLException {
        setupDriver();
        setUpTest();
    }

    private void setupDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Pro");
        capabilities.setCapability("appium:platformVersion", "18.0");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:bundleId", "com.apple.mobilecal");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723/"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        TestUtils.handleAlertIfPresent(driver);

        this.calendarPage = new CalendarPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected abstract void setUpTest();

    public CalendarPage getCalendarPage() {
        return calendarPage;
    }
}
