package ru.cft.focusstart.task3.controller;

import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameType;

public interface Controller {
    void startGame();

    void createNewGame();

    void initializeRecordsDescriptions();

    void clickedOnCell(int x, int y, ButtonType buttonType);

    void saveNewRecord(String name);

    void setGameType(GameType gameType);
}
