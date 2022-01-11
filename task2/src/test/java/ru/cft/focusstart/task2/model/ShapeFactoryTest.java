package ru.cft.focusstart.task2.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.cft.focusstart.task2.exceptions.IncorrectShapeException;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


class ShapeFactoryTest {



    String[] argsOfShape;
    ShapeFactory shapeFactory;


    //  корректные аргументы

    @Test
    void factoryMethodCorrectCircle() {

        argsOfShape = new String[]{"CIRCLE", "5"};
        shapeFactory = new ShapeFactory(List.of(argsOfShape));
        Assertions.assertTrue(shapeFactory.factoryMethod() != null &&
                shapeFactory.factoryMethod().getClass().isAssignableFrom(Circle.class));
    }

    @Test
    void factoryMethodCorrectRectangle() {
        argsOfShape = new String[]{"RECTANGLE", "5 3"};
        shapeFactory = new ShapeFactory(List.of(argsOfShape));
        Assertions.assertTrue(shapeFactory.factoryMethod() != null &&
                shapeFactory.factoryMethod().getClass().isAssignableFrom(Rectangle.class));
    }

    @Test
    void factoryMethodCorrectTriangle() {
        argsOfShape = new String[]{"TRIANGLE", "5 3 4"};
        shapeFactory = new ShapeFactory(List.of(argsOfShape));
        Assertions.assertTrue(shapeFactory.factoryMethod() != null &&
                shapeFactory.factoryMethod().getClass().isAssignableFrom(Triangle.class));
    }


    @Test
    void factoryMethodWithNull() {
        argsOfShape = new String[]{null};
        try {
            shapeFactory.factoryMethod();
            fail();
        } catch (NullPointerException e) {
            assertTrue(true);
        }

    }


    @ParameterizedTest
    @MethodSource("substituteSetInputData")
    void factoryMethodThrowsIncorrectShapeException_incorrectAmountOfInputData(String[] str){
        try {
            shapeFactory = new ShapeFactory(List.of(str));
        } catch (IncorrectShapeException e) {
            assertTrue(true);
        }

    }

    static Stream<Arguments> substituteSetInputData(){
        return Stream.of(
                Arguments.arguments((Object)new String[] {"TRIANGLE"}),
                Arguments.arguments((Object)new String[] {""}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "5 3"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "5 3 5 6"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "One Two Three"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "1 2 Three"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "-5 -3 -4"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "0 3 3"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "0 0 0"}),
                Arguments.arguments((Object)new String[] {"HEXAGON", "0 0 0"}),
                Arguments.arguments((Object)new String[] {"TRIANGLE", "0 3 3"})
        );
    }





}


















