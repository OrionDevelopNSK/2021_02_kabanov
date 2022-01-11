package ru.cft.focusstart.task2.viev;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataExtractorToFileTest {

    List<String> strings = List.of("TEST");
    String pathOutOfFile = "src/main/resources/outputFile.txt";

    DataExtractorToFile dataExtractorToFile = new DataExtractorToFile(strings, pathOutOfFile);

    @Test
    void dataOutput() {
        dataExtractorToFile.dataOutput();
        Path path = Path.of(pathOutOfFile).toAbsolutePath();
        assertTrue(Files.exists(path));
    }
}