package com.martinlorenc.runners;

import com.martinlorenc.tests.CalendarTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CalendarTest.class);
        System.out.println("Test result: " + result.wasSuccessful());
    }
}
