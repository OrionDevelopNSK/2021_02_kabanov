package ru.cft.focusstart.task3.model;

public class GameFieldParametersMedium extends GameFieldParameters{
    @Override
    public int getHeightGameField() {
        return 16;
    }

    @Override
    public int getWidthGameField() {
        return 16;
    }

    @Override
    public int getCountMines() {
        return 40;
    }
}
