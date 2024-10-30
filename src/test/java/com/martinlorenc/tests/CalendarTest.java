package com.martinlorenc.tests;

import com.martinlorenc.base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class CalendarTest extends BaseTest {

    @Test
    @Description("Test checking if current month is displayed properly")
    @Severity(SeverityLevel.NORMAL)
    public void testCurrentMonthIsDisplayed() {
        getCalendarPage().ensureCorrectView("year");

        String expectedMonth = getCalendarPage().getCurrentMonthFormatted();
        String currentMonth = getCalendarPage()
                .findElement(By.xpath("//XCUIElementTypeButton[contains(@name, 'Current month')]"))
                .getAttribute("label");

        Assertions.assertEquals(expectedMonth, currentMonth, "Wrong month selected!");
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

        Assertions.assertEquals(expectedDay, currentDay, "Wrong day selected!" );
    }
}
