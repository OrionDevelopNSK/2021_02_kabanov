package ru.cft.focusstart.task2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.controller.Formatter;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Shape{
    private static final Logger logger = LoggerFactory.getLogger(Triangle.class);

    private final int lengthSideA;
    private final int lengthSideB;
    private final int lengthSideC;

    private final String nameDescription;
    private String areaDescription;
    private String perimeterDescription;
    private String lengthSideAAndAngleDescription;
    private String lengthSideBAndAngleDescription;
    private String lengthSideCAndAngleDescription;

    public Triangle(int lengthSideA, int lengthSideB, int lengthSideC) {
        this.lengthSideA = lengthSideA;
        this.lengthSideB = lengthSideB;
        this.lengthSideC = lengthSideC;
        nameDescription = createName("Треугольник");
        calculateArea();
        calculatePerimeter();
        findLengthSideAAndAngle();
        findLengthSideBAndAngle();
        findLengthSideCAndAngle();
        combineAllCharacteristics();
    }

    @Override
    protected void calculateArea() {
        logger.info("Поиск площади треугольника");
        float halfPerimeter = (lengthSideA + lengthSideB + lengthSideC) / 2.0f;
        float area = (float)Math.sqrt(halfPerimeter
                * (halfPerimeter - lengthSideA)
                * (halfPerimeter - lengthSideB)
                * (halfPerimeter - lengthSideC));
        areaDescription =  Formatter.formatStringWithSquare("Площадь", area);
    }

    @Override
    protected void calculatePerimeter() {
        logger.info("Поиск периметра треугольника");
        int perimeter = lengthSideA + lengthSideB + lengthSideC;
        perimeterDescription = Formatter.formatString("Периметр", perimeter);
    }

    @Override
    protected void combineAllCharacteristics() {
        logger.info("Объединение всех характеристик");
        characteristics = new ArrayList<>();
        characteristics.add(nameDescription);
        characteristics.add(areaDescription);
        characteristics.add(perimeterDescription);
        characteristics.add(lengthSideAAndAngleDescription);
        characteristics.add(lengthSideBAndAngleDescription);
        characteristics.add(lengthSideCAndAngleDescription);
    }

    private void findLengthSideAAndAngle(){
        logger.info("Поиск длины стороны А треугольника и противолежащего угла");
        float angle = calculateAngleOppositeSideA();
        lengthSideAAndAngleDescription =
                Formatter.formatStringLengthSideCAndAngle("Длина строны А составляет", lengthSideA, angle);
    }

    private void findLengthSideBAndAngle(){
        logger.info("Поиск длины стороны B треугольника и противолежащего угла");
        float angle = calculateAngleOppositeSideB();
        lengthSideBAndAngleDescription =
                Formatter.formatStringLengthSideCAndAngle("Длина строны B составляет", lengthSideB, angle);
    }

    private void findLengthSideCAndAngle(){
        logger.info("Поиск длины стороны C треугольника и противолежащего угла");
        float angle = calculateAngleOppositeSideC();
        lengthSideCAndAngleDescription =
                Formatter.formatStringLengthSideCAndAngle("Длина строны C составляет", lengthSideC, angle);
    }

    public String getLengthSideAAndAngleDescription(){
        return lengthSideAAndAngleDescription;
    }

    public String getLengthSideBAndAngleDescription(){
        return lengthSideBAndAngleDescription;
    }

    public String getLengthSideCAndAngleDescription(){
        return lengthSideCAndAngleDescription;
    }

    //  Вычисление противолежащих углов через Аркоссинус
    public float calculateAngleOppositeSideA(){
        logger.info("Расчет противолежащего угла от стороны А");
        float cosAngle = ((lengthSideB * lengthSideB)
                        + (lengthSideC * lengthSideC)
                        - (lengthSideA * lengthSideA))
                / (float)(2 * lengthSideC * lengthSideB);
        return (float)(Math.acos(cosAngle) * 180 / Math.PI);
    }

    public float calculateAngleOppositeSideB(){
        logger.info("Расчет противолежащего углаот стороны B");
        float cosAngle = ((lengthSideA * lengthSideA)
                        + (lengthSideC * lengthSideC)
                        - (lengthSideB * lengthSideB))
                / (float)(2 * lengthSideA * lengthSideC);

        return (float)(Math.acos(cosAngle) * 180 / Math.PI);
    }

    public float calculateAngleOppositeSideC(){
        logger.info("Расчет противолежащего угла от стороны C");
        float cosAngle = ((lengthSideA * lengthSideA)
                        + (lengthSideB * lengthSideB)
                        - (lengthSideC * lengthSideC))
                / (float)(2 * lengthSideA * lengthSideB);

        return (float)(Math.acos(cosAngle) * 180 / Math.PI);
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










}
