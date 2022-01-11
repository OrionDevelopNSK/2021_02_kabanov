package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.timers.AbstractTimer;
import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameType;

public class ModelImpl implements Model {
    private static final Logger log = LoggerFactory.getLogger(Map.class);

    private final AbstractTimer timer;
    private Game game;
    private Map map;
    private Records records;
    private NewGameListener newGameListener;
    private int currentRecordScore;
    private GameFieldParameters gameFieldParameters;
    private GameType gameType;

    public ModelImpl(AbstractTimer timer) {
        this.timer = timer;
        initialize();
    }

    private void initialize() {
        log.debug("Инициализация игры");
        records = new Records(new HighScoresTables());
        currentRecordScore = records.getScore(GameType.NOVICE);
        gameFieldParameters = new GameFieldParametersNovice();
        gameType = GameType.NOVICE;
    }

    @Override
    public void createNewGame(){
        log.debug("Создание новой игры");
        map = new Map(gameFieldParameters);
        game = new Game(map);
        if (newGameListener != null){
            newGameListener.onStartGame(gameFieldParameters.getHeightGameField(),
                    gameFieldParameters.getWidthGameField(), gameFieldParameters.getCountMines());
        }
    }

    @Override
    public void initializeRecordsDescriptions(){
        records.initialize();
    }

    @Override
    public void start(){
        log.debug("Запуск игры");
        newGameListener.onStartGame(gameFieldParameters.getHeightGameField(),
                gameFieldParameters.getWidthGameField(), gameFieldParameters.getCountMines());
    }

    @Override
    public void clickedOnCell(int x, int y, ButtonType buttonType) {
        game.clickedOnCell(x, y, buttonType);
    }

    @Override
    public void saveNewRecord(String name){
        log.info("Сохранение нового рекорда в зависимости от сложности игры");
        records.setScoreAndName(gameType, name, timer.getTime());
    }

    @Override
    public void setGameType(GameType gameType) {
        log.info("Выбор сложности игры");
        gameFieldParameters = switch (gameType) {
            case NOVICE -> new GameFieldParametersNovice();
            case MEDIUM -> new GameFieldParametersMedium();
            case EXPERT -> new GameFieldParametersExpert();
        };
        this.gameType = gameType;
        selectRecordScore(gameType);
        game.isFirstClick = true;
        timer.stopTimer();
        createNewGame();
    }

    private void selectRecordScore(GameType gameType){
        log.debug("Выбор очков рекорда для текущей сложности");
        switch (gameType) {
            case NOVICE -> currentRecordScore = records.getScore(GameType.NOVICE);
            case MEDIUM -> currentRecordScore = records.getScore(GameType.MEDIUM);
            case EXPERT -> currentRecordScore = records.getScore(GameType.EXPERT);
        }
    }

    @Override
    public boolean inTime(){
        log.info("Поставлен новый рекорд");
        return timer.getTime() < currentRecordScore;
    }

    @Override
    public void setImageListener(ImageTypeListener listener) {
        game.setImageListener(listener);
    }

    @Override
    public void setMinesCountListener(MineCountListener listener) {
        game.setMinesCountListener(listener);
    }

    @Override
    public void setVictoryListener(VictoryListener listener) {
        game.setVictoryListener(listener);
    }

    @Override
    public void setDefeatListener(DefeatListener listener) {
        game.setDefeatListener(listener);
    }

    @Override
    public void setStartTimerListener(TimerListener listener) {
        game.setStartTimerListener(listener);
    }

    @Override
    public void setStopTimerListener(TimerListener listener) {
        game.setStopTimerListener(listener);
    }

    @Override
    public void setRecordListenerNovice(RecordListener listener) {
        records.setRecordListenerNovice(listener);
    }

    @Override
    public void setRecordListenerMedium(RecordListener listener) {
        records.setRecordListenerMedium(listener);
    }

    @Override
    public void setRecordListenerExpert(RecordListener listener) {
        records.setRecordListenerExpert(listener);
    }

    @Override
    public void setNewGameListener(NewGameListener listener) {
        newGameListener = listener;
    }

}
