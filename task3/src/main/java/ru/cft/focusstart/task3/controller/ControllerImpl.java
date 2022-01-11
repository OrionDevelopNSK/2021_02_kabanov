package ru.cft.focusstart.task3.controller;

import ru.cft.focusstart.task3.model.Model;
import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameType;

public record ControllerImpl(Model model) implements Controller {

    @Override
    public void startGame() {
        model.start();
    }

    @Override
    public void createNewGame() {
        model.createNewGame();
    }

    @Override
    public void initializeRecordsDescriptions() {
        model.initializeRecordsDescriptions();
    }

    @Override
    public void clickedOnCell(int x, int y, ButtonType buttonType) {
        model.clickedOnCell(x, y, buttonType);
    }

    @Override
    public void saveNewRecord(String name) {
        model.saveNewRecord(name);
    }

    @Override
    public void setGameType(GameType gameType) {
        model.setGameType(gameType);
    }


}
