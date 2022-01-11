package ru.cft.focusstart.task2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task2.exceptions.IncorrectFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public record ReaderOfFile(CommandLineController commandLineController) {
    private static final Logger logger = LoggerFactory.getLogger(ReaderOfFile.class);

    public List<String> readFile() {
        logger.info("Открытие файла для считывания");
        try {
            File file = new File(commandLineController.getInputFileName());
            Path path = Path.of(file.getPath());
            if (file.length() == 0) throw new IncorrectFileException("Файл имеет нулевую длину");
            return Files.readAllLines(path, Charset.defaultCharset());
        } catch (NullPointerException | FileNotFoundException e) {
            throw new IncorrectFileException("Файл не найден, либо пуст", e);
        } catch (SecurityException e) {
            throw new IncorrectFileException("Нет доступа к файлу", e);
        } catch (IOException e) {
            throw new IncorrectFileException("Ошибка ввода/вывода", e);
        }
    }


}
