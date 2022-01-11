package ru.cft.focusstart.task2.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandLineControllerTest {

    String[] arg;
    CommandLineController commandLineController;



    @Test
    void getInputFileName() {
        arg = new String[]{"-f", "inputFile.txt", "outputFile.txt"};
        commandLineController = new CommandLineController(arg);
        assertEquals("inputFile.txt",commandLineController.getInputFileName());
    }

    @Test
    void getOutputFileName() {
        arg = new String[]{"-f", "inputFile.txt", "outputFile.txt"};
        commandLineController = new CommandLineController(arg);
        assertEquals("outputFile.txt",commandLineController.getOutputFileName());
    }

    @Test
    void getTypeOfOutputResult() {
        arg = new String[]{"-f", "inputFile.txt", "outputFile.txt"};
        commandLineController = new CommandLineController(arg);
        assertEquals(TypeOfOutput.FILE,commandLineController.getTypeOfOutputResult());
    }

    @Test
    void getInputFileNameNotCorrect() {
        try {
            arg = new String[]{};
            commandLineController = new CommandLineController(arg);
            commandLineController.getInputFileName();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getOutputFileNameNotCorrect() {
        try {
            arg = new String[]{};
            commandLineController = new CommandLineController(arg);
            commandLineController.getOutputFileName();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    void getTypeOfOutputResultNotCorrect() {
        try {
            arg = new String[]{};
            commandLineController = new CommandLineController(arg);
            commandLineController.getTypeOfOutputResult();
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }






}