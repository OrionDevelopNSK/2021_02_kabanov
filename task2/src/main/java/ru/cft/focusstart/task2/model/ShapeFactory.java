package ru.cft.focusstart.task2.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.exceptions.IncorrectFileException;
import ru.cft.focusstart.task2.exceptions.IncorrectShapeException;

import java.util.Arrays;
import java.util.List;


public record ShapeFactory(List<String> args) {
    private static final Logger logger = LoggerFactory.getLogger(ShapeFactory.class);

    private String parseNameOfShape(List<String> args) {
        try {
            return args.get(0);
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new IncorrectFileException("Во входном файле не указаны параметры для построения фигуры", e);
        }
    }

    private int[] parseParametersOfShape(List<String> args) {
        try {
            return Arrays.stream(args.get(1).split("\\s+"))
                    .mapToInt(Integer::parseInt).toArray();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new IncorrectFileException("Во входном файле не указаны параметры для построения фигуры", e);
        } catch (NumberFormatException e) {
            throw new IncorrectFileException("Во входном файле указаны некорректные типы параметров для построения фигуры", e);
        }
    }

    public Shape factoryMethod() {
        logger.info("Выбор типа фигуры");
        String shapeName;
        int[] parametersOfShape;
        shapeName = parseNameOfShape(args);
        parametersOfShape = parseParametersOfShape(args);
        int parametersCount = parametersOfShape.length;
        if (shapeName.equals("CIRCLE") && parametersCount == 1) return createCircle(parametersOfShape);
        else if (shapeName.equals("RECTANGLE") && parametersCount == 2) return createRectangle(parametersOfShape);
        else if (shapeName.equals("TRIANGLE") && parametersCount == 3) return createTriangle(parametersOfShape);
        else throw new IncorrectShapeException("Невозможно создать фигуру с заданными параметрами");
    }

    private Shape createCircle(int[] parameters) {
        logger.info("Создание круга");
        if (!isValidShapeParameters(parameters)) {
            System.err.println("Невозможно создать круг с заданными параметрами");              //для пользователя
            throw new IncorrectShapeException("Невозможно создать круг с заданными параметрами");
        }
        return new Circle(parameters[0]);
    }

    private Shape createRectangle(int[] parameters) {
        logger.info("Создание прямоугольника");
        if (!isValidShapeParameters(parameters)) {
            System.err.println("Невозможно создать прямоугольник с заданными параметрами");     //для пользователя
            throw new IncorrectShapeException("Невозможно создать прямоугольник с заданными параметрами");
        }
        return new Rectangle(parameters[0], parameters[1]);
    }

    private Shape createTriangle(int[] parameters) {
        logger.info("Создание треугольника");
        if (!isValidTriangle(parameters)) {
            System.err.println("Невозможно создать треугольник с заданными параметрами");       //для пользователя
            throw new IncorrectShapeException("Невозможно создать треугольник с заданными параметрами");
        }
        return new Triangle(parameters[0], parameters[1], parameters[2]);
    }

    //  у треугольника гипотенуза не может быть равна или быть больше суммы катетов
    private boolean isValidTriangle(int[] parameters) {
        logger.info("Проверка возможности существования заданного треугольника");
        int[] tmp = Arrays.copyOf(parameters, parameters.length);
        Arrays.sort(tmp);
        if (tmp[0] <= 0) return false;   //  проверка на отрицательные цифры
        return tmp[2] < (tmp[1] + tmp[0]);
    }

    //  параметры фигуры не могут быть нулевыми и отрицательными
    private boolean isValidShapeParameters(int[] parameters) {
        logger.info("Проверка возможности существования заданной фигуры");
        return Arrays.stream(parameters).noneMatch(s -> s <= 0);
    }

}
