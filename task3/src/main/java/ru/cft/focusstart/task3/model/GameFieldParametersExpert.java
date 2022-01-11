package ru.cft.focusstart.task3.model;

public class GameFieldParametersExpert extends GameFieldParameters{
    @Override
    public int getHeightGameField() {
        return 16;
    }

    @Override
    public int getWidthGameField() {
        return 30;
    }

    @Override
    public int getCountMines() {
        return 99;
    }
}
