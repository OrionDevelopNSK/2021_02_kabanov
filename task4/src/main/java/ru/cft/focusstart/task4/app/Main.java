package ru.cft.focusstart.task4.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;

/**
 * В программе используется помимо ввода количества итераций в консоль, так же и ввод номера функции от 1 до 4 для рассчета
 */

public class Main {
    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ProcessorCoreController processorCoreController = new ProcessorCoreController();
        int processorCoreCount = processorCoreController.getProcessorCoreCount();
        ConsoleReader consoleReader = new ConsoleReader();
        long numberOfIterations = consoleReader.getNumberOfIterations();
        NumberOfIterationsSeparator numberOfIterationsSeparator = new NumberOfIterationsSeparator(processorCoreCount, numberOfIterations);
        TasksController tasksController = new TasksController(numberOfIterationsSeparator.getPartsNumberOfIterations(), processorCoreCount);

        try {
            tasksController.functionSelection(consoleReader.getFunctionNumber());
        } catch (InterruptedException e) {
            log.error("Прерывание потока", e.fillInStackTrace());
        } catch (ExecutionException e) {
            log.error("Попытке получить результат задачи, которая была прервана выдачей исключения", e.fillInStackTrace());
        }
    }
}


