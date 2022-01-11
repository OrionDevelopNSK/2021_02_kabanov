package ru.cft.focusstart.task2.controller;

public class Formatter {
    private final static String unitsOfMeasurement = "мм";

    public static String formatString(String str, int i){
        return String.format("%s: %s %s", str, i, unitsOfMeasurement);
    }

    public static String formatString(String str, float f){
        return String.format("%s: %.2f %s", str, f, unitsOfMeasurement);
    }

    public static String formatStringWithSquare(String str, int i){
        return String.format("%s: %s кв. %s", str, i, unitsOfMeasurement);
    }

    public static String formatStringWithSquare(String str, float f){
        return String.format("%s: %.2f кв. %s", str, f, unitsOfMeasurement);
    }

    public static String formatStringLengthSideCAndAngle(String str, int length, float angle){
        return String.format("%s: %s %s, противолежащий угол равен: %.2f градусов", str, length, unitsOfMeasurement, angle);
    }



}

