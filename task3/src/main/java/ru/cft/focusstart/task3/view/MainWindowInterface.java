package ru.cft.focusstart.task3.view;

public interface MainWindowInterface {
    void setCellListener(CellEventListener listener);

    void setBombsCount(int bombsCount);

    void setTimerValue(int value);

    void createGameField(int rowsCount, int colsCount);
}
