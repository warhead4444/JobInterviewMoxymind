package com.martinlorenc.tests;

import com.martinlorenc.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CalendarTest extends BaseTest {

    @Override
    public void setUpTest() {
    }

    @Test
    @Description("Test checking if current month is displayed properly")
    @Severity(SeverityLevel.NORMAL)
    public void testCurrentMonthIsDisplayed() {
        getCalendarPage().ensureCorrectView("year");

        String expectedMonth = getCalendarPage().getCurrentMonthFormatted();
        String currentMonth = getCalendarPage()
                .findElement(By.xpath("//XCUIElementTypeButton[contains(@name, 'Current month')]"))
                .getAttribute("label");

        Assert.assertEquals("Wrong month selected!", expectedMonth, currentMonth);
    }

    @Test
    @Description("Test checking if current day is displayed properly")
    @Severity(SeverityLevel.NORMAL)
    public void testCurrentDayIsSelected() {
        getCalendarPage().ensureCorrectView("day");

        String expectedDay = getCalendarPage().getTodayFormatted();
        String currentDay = getCalendarPage()
                .findElement(By.xpath("//XCUIElementTypeOther[contains(@name, 'Today')]"))
                .getAttribute("label");

        Assert.assertEquals("Wrong day selected!", expectedDay, currentDay);
    }
}
