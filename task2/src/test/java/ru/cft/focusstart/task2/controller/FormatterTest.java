package ru.cft.focusstart.task2.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FormatterTest {



    @Test
    void formatStringInt() {
        assertEquals("String: 10 мм", Formatter.formatString("String", 10));
    }

    @Test
    void FormatStringFloat() {
        assertEquals("String: 10,50 мм", Formatter.formatString("String", 10.5f));
    }

    @Test
    void formatStringWithSquareInt() {
        assertEquals("String: 5 кв. мм", Formatter.formatStringWithSquare("String", 5));
    }

    @Test
    void formatStringWithSquareFloat() {
        assertEquals("String: 10,50 кв. мм", Formatter.formatStringWithSquare("String", 10.5f));
    }

    @Test
    void formatStringLengthSideCAndAngle() {
        assertEquals("String: 10 мм, противолежащий угол равен: 5,60 градусов",
                Formatter.formatStringLengthSideCAndAngle("String", 10, 5.6f));
    }
}