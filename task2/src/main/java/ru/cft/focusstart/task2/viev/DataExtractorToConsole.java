package ru.cft.focusstart.task2.viev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class DataExtractorToConsole extends DataExtractor {
    private static final Logger logger = LoggerFactory.getLogger(DataExtractorToConsole.class);
    private final List<String> data;

    public DataExtractorToConsole(List<String> data) {
        this.data = data;
    }

    @Override
    public void dataOutput() {
        logger.info("Вывод результатов работы программы на консоль");
        data.forEach(System.out::println);
    }
}
