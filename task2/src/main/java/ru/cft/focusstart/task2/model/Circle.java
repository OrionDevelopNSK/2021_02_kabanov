package ru.cft.focusstart.task2.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.controller.Formatter;

import java.util.ArrayList;
import java.util.List;


public class Circle extends Shape{
    private static final Logger logger = LoggerFactory.getLogger(Circle.class);
    private final int radius;
    private final String nameDescription;
    private String areaDescription;
    private String perimeterDescription;
    private String radiusDescription;
    private String diameterDescription;

    public Circle(int radius) {
        this.radius = radius;
        nameDescription = createName("Круг");
        calculateArea();
        calculatePerimeter();
        calculateRadius();
        calculateDiameter();
        combineAllCharacteristics();
    }

    @Override
    protected void calculateArea() {
        logger.info("Поиск площади круга");
        float areaFloat = (float) (Math.PI * radius * radius);
        areaDescription = Formatter.formatStringWithSquare("Площадь", areaFloat);
    }

    @Override
    protected void calculatePerimeter() {
        logger.info("Поиск периметра круга");
        float perimeterFloat =  (float)(2 * Math.PI * radius);
        perimeterDescription =  Formatter.formatString("Периметр", perimeterFloat);
    }

    @Override
    protected void combineAllCharacteristics() {
        logger.info("Объединение всех характеристик");
        characteristics = new ArrayList<>();
        characteristics.add(nameDescription);
        characteristics.add(areaDescription);
        characteristics.add(perimeterDescription);
        characteristics.add(radiusDescription);
        characteristics.add(diameterDescription);
    }

    private void calculateRadius(){
        logger.info("Вычисление радиуса");
        radiusDescription = Formatter.formatString("Радиус", radius);
    }

    private void calculateDiameter(){
        logger.info("Вычисление диаметра");
        diameterDescription =  Formatter.formatString("Диаметр", radius * 2);
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

    public String getRadiusDescription(){
        return radiusDescription;
    }

    public String getDiameterDescription(){
        return diameterDescription;
    }
}
