package ru.cft.focusstart.task2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.controller.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Rectangle extends Shape{
    private static final Logger logger = LoggerFactory.getLogger(Rectangle.class);
    private final int lengthSideA;
    private final int lengthSideB;
    private final String nameDescription;
    private String areaDescription;
    private String perimeterDescription;
    private String diagonalDescription;
    private String widthDescription;
    private String lengthDescription;

    public Rectangle(int lengthSideA, int lengthSideB) {
        this.lengthSideA = lengthSideA;
        this.lengthSideB = lengthSideB;
        nameDescription = createName("Прямоугольник");
        calculateArea();
        calculatePerimeter();
        combineAllCharacteristics();
        calculateDiagonal();
        findWidth();
        findLength();
        combineAllCharacteristics();
    }


    @Override
    protected void calculateArea() {
        logger.info("Поиск площади прямоугольника");
        int area = lengthSideA * lengthSideB;
        areaDescription = Formatter.formatStringWithSquare("Площадь", area);
    }

    @Override
    protected void calculatePerimeter() {
        logger.info("Поиск периметра прямоугольника");
        int perimeter =  2 * (lengthSideA + lengthSideB);
        perimeterDescription = Formatter.formatString("Периметр", perimeter);
    }

    @Override
    protected void combineAllCharacteristics() {
        logger.info("Объединение всех характеристик");
        characteristics = new ArrayList<>();
        characteristics.add(nameDescription);
        characteristics.add(areaDescription);
        characteristics.add(perimeterDescription);
        characteristics.add(diagonalDescription);
        characteristics.add(widthDescription);
        characteristics.add(lengthDescription);
    }

    private void calculateDiagonal(){
        logger.info("Поиск диагонали прямоуголника");
        float diagonal =  (float) Math.sqrt(lengthSideA * lengthSideA + lengthSideB * lengthSideB);
        diagonalDescription =  Formatter.formatString("Длина диагонали", diagonal);
    }

    private void findWidth(){
        logger.info("Поиск короткой стороны прямоугольника");
        int min =  Math.min(lengthSideA, lengthSideB);
        widthDescription = Formatter.formatString("Размер короткой стороны", min);
    }

    private void findLength(){
        logger.info("Поиск длинной стороны прямоугольника");
        int max = Math.max(lengthSideA, lengthSideB);
        lengthDescription =  Formatter.formatString("Размер длинной стороны", max);
    }

    @Override
    public String getNameDescription() {
        return nameDescription;
    }

    @Override
    public String getAreaDescription() {
        return areaDescription;
    }

    @Override
    public String getPerimeterDescription() {
        return perimeterDescription;
    }

    @Override
    public List<String> getCharacteristics() {
        return characteristics;
    }

    public String getDiagonal() {
        return diagonalDescription;
    }

    public String getWidthDescription() {
        return widthDescription;
    }

    public String getLengthDescription() {
        return lengthDescription;
    }

}
