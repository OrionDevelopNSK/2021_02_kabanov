package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.view.GameType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HighScoresTables {
    private static final Logger log = LoggerFactory.getLogger(HighScoresTables.class);

    private final String PATH_TABLE_EXPERT = "tableOfRecordsExpert.txt";
    private final String PATH_TABLE_MEDIUM = "tableOfRecordsMedium.txt";
    private final String PATH_TABLE_NOVICE = "tableOfRecordsNovice.txt";

    public List<String> getTableData(GameType gameType) {
        log.debug("Выбор файла для чтения рекорда");
        return switch (gameType) {
            case NOVICE -> read(PATH_TABLE_NOVICE);
            case MEDIUM -> read(PATH_TABLE_MEDIUM);
            case EXPERT -> read(PATH_TABLE_EXPERT);
        };
    }

    public void setTableData(GameType gameType, List<String> leaderBoardElements) {
        log.debug("Выбор файла для записи рекорда");
        switch (gameType) {
            case NOVICE -> write(PATH_TABLE_NOVICE, leaderBoardElements);
            case MEDIUM -> write(PATH_TABLE_MEDIUM, leaderBoardElements);
            case EXPERT -> write(PATH_TABLE_EXPERT, leaderBoardElements);
        }
    }

    private void write(String pathToTable, List<String> leaderBoardElements) {
        log.debug("Запись таблицы лидеров в файл {}", pathToTable);
        try {
            File file = new File(pathToTable);
            Path path = Path.of(file.getAbsolutePath());
            Files.write(path, leaderBoardElements);
        } catch (NullPointerException | FileNotFoundException e) {
            log.error("Файл не найден", e.fillInStackTrace());
        } catch (SecurityException e) {
            log.error("Нет доступа к файлу", e.fillInStackTrace());
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода", e.fillInStackTrace());
        }
    }

    private List<String> read(String pathToTable) {
        log.debug("Чтение таблицы лидеров из файла {}", pathToTable);
        try {
            File file = new File(pathToTable);
            Path path = Path.of(file.getAbsolutePath());
            if (file.length() == 0) throw new FileNotFoundException("Файл имеет нулевую длину");
            return Files.readAllLines(path, StandardCharsets.UTF_8);
        } catch (NullPointerException e) {
            log.error("Файл не найден, либо пуст", e.fillInStackTrace());
        } catch (SecurityException e) {
            log.error("Нет доступа к файлу", e.fillInStackTrace());
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода", e.fillInStackTrace());
        }
        return null;
    }

}
