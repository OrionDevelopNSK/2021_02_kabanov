package ru.cft.focusstart.task3.model;

import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameType;

public interface Model {
    void createNewGame();

    boolean inTime();

    void setImageListener(ImageTypeListener listener);

    void setMinesCountListener(MineCountListener listener);

    void setVictoryListener(VictoryListener listener);

    void setDefeatListener(DefeatListener listener);

    void setStartTimerListener(TimerListener listener);

    void setStopTimerListener(TimerListener listener);

    void setRecordListenerNovice(RecordListener listener);

    void setRecordListenerMedium(RecordListener listener);

    void setRecordListenerExpert(RecordListener listener);

    void setNewGameListener(NewGameListener listener);

    void clickedOnCell(int x, int y, ButtonType buttonType);

    void saveNewRecord(String name);

    void setGameType(GameType gameType);

    void initializeRecordsDescriptions();

    void start();

}
