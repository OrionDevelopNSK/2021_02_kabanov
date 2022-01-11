package ru.cft.focusstart.task2.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.controller.CommandLineController;
import ru.cft.focusstart.task2.controller.ReaderOfFile;
import ru.cft.focusstart.task2.controller.TypeOfOutput;
import ru.cft.focusstart.task2.exceptions.IncorrectFileException;
import ru.cft.focusstart.task2.exceptions.IncorrectShapeException;
import ru.cft.focusstart.task2.model.Shape;
import ru.cft.focusstart.task2.model.ShapeFactory;
import ru.cft.focusstart.task2.viev.DataExtractor;
import ru.cft.focusstart.task2.viev.DataExtractorToConsole;
import ru.cft.focusstart.task2.viev.DataExtractorToFile;

import java.util.List;


public class Main {
    //  входные параметры
    // -c inputFile.txt outputFile.txt
    // -f inputFile.txt outputFile.txt

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static CommandLineController commandLineController;

    public static void main(String[] args) {
        try {
            logger.info("Запуск программы");
            commandLineController = new CommandLineController(args);
            ReaderOfFile reader = new ReaderOfFile(commandLineController);
            ShapeFactory shapeFactory = new ShapeFactory(reader.readFile());
            Shape shape = shapeFactory.factoryMethod();
            TypeOfOutput typeOfOutput = commandLineController.getTypeOfOutputResult();
            changeTypeExtractor(shape, typeOfOutput);
        } catch (IncorrectFileException e) {
            logger.error("Ошибка чтения файла", e.fillInStackTrace());
        }catch (IncorrectShapeException e) {
            logger.error("Ошибка создания фигуры", e.fillInStackTrace());
        }catch (IllegalArgumentException e) {
            logger.error("Ошибка командной строки: {}", e.getMessage());
        }
    }

    private static void changeTypeExtractor(Shape shape, TypeOfOutput typeOfOutput){
        logger.info("Выбор способа вывода результирующих данных");
        List<String> characteristics = shape.getCharacteristics();
        String outputFileName = commandLineController.getOutputFileName();
        if (typeOfOutput == TypeOfOutput.FILE){
            extract(new DataExtractorToFile(characteristics, outputFileName));
        }else if (typeOfOutput == TypeOfOutput.CONSOLE){
            extract(new DataExtractorToConsole(characteristics));
        }
    }

    private static void extract(DataExtractor extractor){
        extractor.dataOutput();
    }

}
