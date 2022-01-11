package ru.cft.focusstart.task2.viev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.exceptions.IncorrectFileException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;


public class DataExtractorToFile extends DataExtractor {
    private static final Logger logger = LoggerFactory.getLogger(DataExtractorToFile.class);
    private final List<String> data;
    private final String outputFileName;

    public DataExtractorToFile(List<String> data, String outputFileName) {
        this.data = data;
        this.outputFileName = outputFileName;
    }

    @Override
    public void dataOutput() {
        logger.info("Вывод результатов работы программы в файл");
        String pathString = "task2/src/main/resources/";
        try {
            File file = new File(outputFileName);
            Path path = Path.of(file.getPath());
            Files.write(path, data, Charset.defaultCharset(), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        } catch (SecurityException e) {
            throw new IncorrectFileException("Нет доступа к файлу", e);
        } catch (UnsupportedOperationException e) {
            throw new IncorrectFileException("Операция не поддерживается", e);
        } catch (IOException e) {
            throw new IncorrectFileException("Ошибка ввода/вывода", e);
        }
    }
}
