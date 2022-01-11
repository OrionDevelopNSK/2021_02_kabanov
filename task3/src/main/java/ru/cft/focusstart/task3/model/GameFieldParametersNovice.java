package ru.cft.focusstart.task3.model;

public class GameFieldParametersNovice extends GameFieldParameters{
    @Override
    public int getHeightGameField() {
        return 9;
    }

    @Override
    public int getWidthGameField() {
        return 9;
    }

    @Override
    public int getCountMines() {
        return 10;
    }
}
