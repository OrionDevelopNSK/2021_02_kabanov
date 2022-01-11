package ru.cft.focusstart.task2.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CircleTest {

    Circle circle;


    @Test
    void getArea() {
        circle = new Circle(5);
        assertEquals("Площадь: 78,54 кв. мм", circle.getAreaDescription());
    }

    @Test
    void getPerimeter() {
        circle = new Circle(10);
        assertEquals("Периметр: 62,83 мм", circle.getPerimeterDescription());
    }


    @Test
    void getCharacteristics() {
        circle = new Circle(5);

        List<String> l = List.of("Тип фигуры: Круг",
                "Площадь: 78,54 кв. мм",
                "Периметр: 31,42 мм",
                "Радиус: 5 мм",
                "Диаметр: 10 мм");

        assertEquals(circle.getCharacteristics(), l);

    }


    @Test
    void getRadius(){
        circle = new Circle(5);
        assertEquals("Радиус: 5 мм", circle.getRadiusDescription());
    }

    @Test
    void getDiameter(){
        circle = new Circle(5);
        assertEquals("Диаметр: 10 мм", circle.getDiameterDescription());
    }

    @Test
    void getNameDescription() {
        circle = new Circle(5);
        assertEquals("Тип фигуры: Круг", circle.getNameDescription());
    }
}