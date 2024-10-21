package com.martinlorenc.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class CalendarPage {
    private AppiumDriver driver;
    private LocalDate today;
    private DateTimeFormatter monthFormatter;
    private DateTimeFormatter dayFormatter;

    public CalendarPage(AppiumDriver driver) {
        this.driver = driver;
        this.today = LocalDate.now();
        this.monthFormatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        this.dayFormatter = DateTimeFormatter.ofPattern("EEEE, d MMMM", Locale.ENGLISH);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public WebElement findElement(By locator) {
        try {
            return driver.findElement(locator);
        } catch (NoSuchElementException e) {
            System.out.println("Element nebol nájdený: " + locator);
            throw e;
        }
    }

    private String generateXPathForDateElement(String type) {
        switch (type) {
            case "todayButton":
                return "//XCUIElementTypeStaticText[contains(@name, 'Today')]";
            case "monthButton":
                return "//XCUIElementTypeButton[contains(@name, '" + LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM")) + "')]";
            case "yearButton":
                return "//XCUIElementTypeButton[contains(@name, '" + LocalDate.now().getYear() + "')]";
            case "dayView":
                return "//XCUIElementTypeNavigationBar[contains(@name, 'DayViewContainerView')]";
            case "monthView":
                return "//XCUIElementTypeNavigationBar[contains(@name, 'MonthViewContainerView')]";
            case "yearView":
                return "//XCUIElementTypeNavigationBar[contains(@name, 'YearViewContainerView')]";
            default:
                throw new IllegalArgumentException("Invalid type: " + type);
        }
    }

    private void clickElement(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }

    public String getTodayFormatted() {
        return "Today, " + today.format(dayFormatter);
    }

    public String getCurrentMonthFormatted() {
        return "Current month, " + today.format(monthFormatter);
    }

    public void clickTodayButton() {
        clickElement(By.xpath(generateXPathForDateElement("todayButton")));
    }

    public void navigateToMonthView() {
        clickElement(By.xpath(generateXPathForDateElement("monthButton")));
    }

    public void navigateToYearView() {
        clickElement(By.xpath(generateXPathForDateElement("yearButton")));
    }

    public void ensureCorrectView(String expectedView) {
        switch (expectedView.toLowerCase()) {
            case "day":
                if (isDayViewDisplayed()) {
                    System.out.println("Day view is already displayed");
                } else if (isMonthViewDisplayed()) {
                    clickTodayButton();
                } else if (isYearViewDisplayed()) {
                    clickTodayButton();
                    clickTodayButton();
                }
                break;

            case "month":
                if (isMonthViewDisplayed()) {
                    System.out.println("Month view is already displayed");
                } else if (isDayViewDisplayed()) {
                    navigateToMonthView();
                } else if (isYearViewDisplayed()) {
                    clickTodayButton();
                }
                break;

            case "year":
                if (isYearViewDisplayed()) {
                    System.out.println("Year view is already displayed");
                } else if (isDayViewDisplayed()) {
                    navigateToMonthView();
                    navigateToYearView();
                } else if (isMonthViewDisplayed()) {
                    navigateToYearView();
                }
                break;

            default:
                System.out.println("Failed to identify the correct view.");
        }
    }

    private boolean isViewDisplayed(By locator) {
        try {
            return findElement(locator).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isYearViewDisplayed() {
        return isViewDisplayed(By.xpath(generateXPathForDateElement("yearView")));
    }

    public boolean isMonthViewDisplayed() {
        return isViewDisplayed(By.xpath(generateXPathForDateElement("monthView")));
    }

    public boolean isDayViewDisplayed() {
        return isViewDisplayed(By.xpath(generateXPathForDateElement("dayView")));
    }
}
