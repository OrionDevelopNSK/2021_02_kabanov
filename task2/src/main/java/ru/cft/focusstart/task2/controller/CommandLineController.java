package ru.cft.focusstart.task2.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandLineController {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineController.class);
    final static String ARG_RESULT_TO_CONSOLE = "-c";
    final static String ARG_RESULT_TO_FILE = "-f";

    private final String[] args;
    private TypeOfOutput typeOfOutput;
    private String inputFileName;
    private String outputFileName;

    public CommandLineController(String[] args) {
        this.args = args;
        checkValidCommandLine();
        createTypeOutput();
        createInputFileName();
        createOutputFileName();
    }

    private void createTypeOutput() {
        logger.info("Считывание командной строки");
        if (args[0].contains(ARG_RESULT_TO_CONSOLE)) typeOfOutput = TypeOfOutput.CONSOLE;
        else if (args[0].contains(ARG_RESULT_TO_FILE)) typeOfOutput = TypeOfOutput.FILE;
    }

    private void checkValidCommandLine() {
        if (args.length < 3) throw new IllegalArgumentException("Неверное количество аргументов командной строки");
    }

    private void createInputFileName() {
        inputFileName = args[1];
    }

    private void createOutputFileName() {
        outputFileName = args[2];
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public TypeOfOutput getTypeOfOutputResult() {
        return typeOfOutput;
    }




}
