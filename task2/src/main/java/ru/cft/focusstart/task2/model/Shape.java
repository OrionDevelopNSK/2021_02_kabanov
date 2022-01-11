package ru.cft.focusstart.task2.model;

import java.util.List;

public abstract class Shape {
    public List<String> characteristics;

    protected abstract String getNameDescription();

    protected abstract String getAreaDescription();

    protected abstract String getPerimeterDescription();

    public abstract List<String> getCharacteristics();

    protected abstract void calculateArea();

    protected abstract void calculatePerimeter();

    protected abstract void combineAllCharacteristics();

    protected String createName(String str) {
        return "Тип фигуры: " + str;
    }

}
