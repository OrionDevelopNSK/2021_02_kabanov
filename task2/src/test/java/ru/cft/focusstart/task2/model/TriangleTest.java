package ru.cft.focusstart.task2.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TriangleTest {

    Triangle triangle;
    @Test
    void getArea() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Площадь: 9,92 кв. мм", triangle.getAreaDescription());
    }

    @Test
    void getPerimeter() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Периметр: 15 мм", triangle.getPerimeterDescription());
    }

    @Test
    void getCharacteristics() {
        triangle = new Triangle(4,5, 6);
        List<String> l = List.of("Тип фигуры: Треугольник",
                "Площадь: 9,92 кв. мм",
                "Периметр: 15 мм",
                "Длина строны А составляет: 4 мм, противолежащий угол равен: 41,41 градусов",
                "Длина строны B составляет: 5 мм, противолежащий угол равен: 55,77 градусов",
                "Длина строны C составляет: 6 мм, противолежащий угол равен: 82,82 градусов");

        assertEquals(triangle.getCharacteristics(), l);
    }

    @Test
    void getLengthSideAAndAngle() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Длина строны А составляет: 4 мм, противолежащий угол равен: 41,41 градусов", triangle.getLengthSideAAndAngleDescription());
    }

    @Test
    void getLengthSideBAndAngle() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Длина строны B составляет: 5 мм, противолежащий угол равен: 55,77 градусов", triangle.getLengthSideBAndAngleDescription());
    }

    @Test
    void getLengthSideCAndAngle() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Длина строны C составляет: 6 мм, противолежащий угол равен: 82,82 градусов", triangle.getLengthSideCAndAngleDescription());
    }


    @Test
    void calculateAngleOppositeSideA() {
        triangle = new Triangle(4,5, 6);
        assertTrue(String.valueOf(triangle.calculateAngleOppositeSideA()).contains("41.40"));
    }

    @Test
    void calculateAngleOppositeSideB()  {
        triangle = new Triangle(4,5, 6);
        assertTrue(String.valueOf(triangle.calculateAngleOppositeSideB()).contains("55.77"));
    }

    @Test
    void calculateAngleOppositeSideC() {
        triangle = new Triangle(4,5, 6);
        assertTrue(String.valueOf(triangle.calculateAngleOppositeSideC()).contains("82.81"));
    }

    @Test
    void getNameDescription() {
        triangle = new Triangle(4,5, 6);
        assertEquals("Тип фигуры: Треугольник", triangle.getNameDescription());
    }
}