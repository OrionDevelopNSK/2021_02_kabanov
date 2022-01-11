package ru.cft.focusstart.task2.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RectangleTest {

    Rectangle rectangle;





    @Test
    void getArea() {
        rectangle = new Rectangle(4,5);
        assertEquals("Площадь: 20 кв. мм", rectangle.getAreaDescription());
    }

    @Test
    void getPerimeter() {
        rectangle = new Rectangle(4,5);
        assertEquals("Периметр: 18 мм", rectangle.getPerimeterDescription());
    }

    @Test
    void getCharacteristics() {
        rectangle = new Rectangle(4,5);
        List<String> l = List.of("Тип фигуры: Прямоугольник",
                "Площадь: 20 кв. мм",
                "Периметр: 18 мм",
                "Длина диагонали: 6,40 мм",
                "Размер короткой стороны: 4 мм",
                "Размер длинной стороны: 5 мм");

        assertEquals(rectangle.getCharacteristics(), l);
    }

    @Test
    void getDiagonal() {
        rectangle = new Rectangle(4,5);

        assertEquals("Длина диагонали: 6,40 мм", rectangle.getDiagonal());
    }

    @Test
    void getWidth() {
        rectangle = new Rectangle(4,5);

        assertEquals("Размер короткой стороны: 4 мм", rectangle.getWidthDescription());
    }

    @Test
    void getLength() {
        rectangle = new Rectangle(4,5);
        assertEquals("Размер длинной стороны: 5 мм", rectangle.getLengthDescription());
    }

    @Test
    void getNameDescription() {
        rectangle = new Rectangle(4,5);
        assertEquals("Тип фигуры: Прямоугольник", rectangle.getNameDescription());
    }
}