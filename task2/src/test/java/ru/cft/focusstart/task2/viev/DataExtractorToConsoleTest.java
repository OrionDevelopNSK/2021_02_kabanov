package ru.cft.focusstart.task2.viev;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataExtractorToConsoleTest {


    List<String> testList = List.of("One", "Two", "Three");


    @Test
    void dataOutput() {
        DataExtractorToConsole dataExtractorToConsole = new DataExtractorToConsole(testList);

        try {
            dataExtractorToConsole.dataOutput();
        }catch (Exception e){
            Assertions.fail();
        }
        Assertions.assertTrue(true);


    }
}