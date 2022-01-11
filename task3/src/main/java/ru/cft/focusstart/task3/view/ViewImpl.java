package ru.cft.focusstart.task3.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.controller.Controller;
import ru.cft.focusstart.task3.model.Model;
import ru.cft.focusstart.task3.timers.AbstractTimer;

import java.awt.*;

public class ViewImpl implements View {
    private static final Logger log = LoggerFactory.getLogger(ViewImpl.class);

    private MainWindow mainWindow;
    private HighScoresWindow highScoresWindow;
    private LoseWindow loseWindow;
    private WinWindow winWindow;
    private SettingsWindow settingsWindow;
    private RecordsWindow recordsWindow;
    private final AbstractTimer timer;
    private final Controller controller;
    private final Model model;

    public ViewImpl(Controller controller, Model model, AbstractTimer timer) {
        this.controller = controller;
        this.model = model;
        this.timer = timer;
        createAllViews();
    }

    private void createAllViews(){
        mainWindow = new MainWindow();
        highScoresWindow = new HighScoresWindow(mainWindow);
        loseWindow = new LoseWindow(mainWindow);
        winWindow = new WinWindow(mainWindow);
        settingsWindow = new SettingsWindow(mainWindow);
        subscribeOnViewAction();
        controller.createNewGame();
        subscribeOnModelAction();
        subscribeOnTimerAction();
        controller.startGame();
    }

    @Override
    public void setNoviceRecord(String winnerName, int timeValue) {
        highScoresWindow.setNoviceRecord(winnerName, timeValue);
    }

    @Override
    public void setMediumRecord(String winnerName, int timeValue) {
        highScoresWindow.setMediumRecord(winnerName, timeValue);
    }

    @Override
    public void setExpertRecord(String winnerName, int timeValue) {
        highScoresWindow.setExpertRecord(winnerName, timeValue);
    }

    @Override
    public String createRecordText(String winnerName, int timeValue) {
        return highScoresWindow.createRecordText(winnerName, timeValue);
    }

    @Override
    public void setCellListener(CellEventListener listener) {
        mainWindow.setCellListener(listener);
    }

    @Override
    public void setBombsCount(int bombsCount) {
        mainWindow.setBombsCount(bombsCount);
    }

    @Override
    public void setTimerValue(int value) {
        mainWindow.setTimerValue(value);
    }

    @Override
    public void createGameField(int rowsCount, int colsCount) {
        mainWindow.createGameField(rowsCount, colsCount);
    }

    @Override
    public void setNameListener(RecordNameListener nameListener) {
        recordsWindow.setNameListener(nameListener);
    }

    @Override
    public void setGameType(GameType gameType) {
        settingsWindow.setGameType(gameType);
    }

    @Override
    public void setGameTypeListener(GameTypeListener gameTypeListener) {
        settingsWindow.setGameTypeListener(gameTypeListener);
    }

    private void openLoseWindow() {
        log.info("Открыть окно проигравшего");
        loseWindow.setLocation(calculateCentrePosition(loseWindow.getWidth(), loseWindow.getHeight()));
        loseWindow.setVisible(true);
    }

    private void openWinWindow() {
        log.info("Открыть окно победителя");
        winWindow.setLocation(calculateCentrePosition(winWindow.getWidth(), winWindow.getHeight()));
        winWindow.setVisible(true);
    }

    private void openNewRecordWindow(){
        log.info("Открытие окна для сохранения имени нового рекордсмена");
        recordsWindow = new RecordsWindow(mainWindow);
        recordsWindow.setNameListener(controller::saveNewRecord);
        recordsWindow.setLocation(calculateCentrePosition(recordsWindow.getWidth(), recordsWindow.getHeight()));
        recordsWindow.setVisible(true);
    }

    private Point calculateCentrePosition(int width, int height){
        log.debug("Расчет центрального положения окна относительно главного");
        int x = mainWindow.getLocation().x;
        int y = mainWindow.getLocation().y;
        int deltaX = x + (mainWindow.getWidth() - width)/2;
        int deltaY = y + (mainWindow.getHeight() - height)/2;
        return new Point(deltaX, deltaY);
    }

    private void subscribeOnViewAction(){
        log.debug("Подписки на события отображений");
        loseWindow.setNewGameListenerLoseWindow(e -> {
            controller.createNewGame();
            subscribeOnModelAction();
            subscribeOnTimerAction();
        });
        loseWindow.setExitListenerLoseWindow(e -> mainWindow.dispose());
        winWindow.setNewGameListenerWinWindow(e -> {
            controller.createNewGame();
            subscribeOnModelAction();
            subscribeOnTimerAction();
        });
        winWindow.setExitListenerWinWindow(e -> mainWindow.dispose());
        mainWindow.setNewGameMenuAction(e -> {
            controller.createNewGame();
            subscribeOnModelAction();
            subscribeOnTimerAction();
            timer.stopTimer();
        });
        mainWindow.setHighScoresMenuAction(e -> {
            controller.initializeRecordsDescriptions();
            highScoresWindow.setLocation(calculateCentrePosition(highScoresWindow.getWidth(), highScoresWindow.getHeight()));
            highScoresWindow.setVisible(true);
        });
        mainWindow.setSettingsMenuAction(e -> {
            settingsWindow.setLocation(calculateCentrePosition(settingsWindow.getWidth(), settingsWindow.getHeight()));
            settingsWindow.setVisible(true);
        });
        mainWindow.setExitMenuAction(e -> mainWindow.dispose());
        mainWindow.setCellListener(controller::clickedOnCell);
        settingsWindow.setGameTypeListener(gameType -> {
            controller.setGameType(gameType);
            subscribeOnModelAction();
            subscribeOnTimerAction();
        });
    }

    private void subscribeOnModelAction(){
        log.debug("Подписки на события модели");
        model.setNewGameListener((height, width, count)->{
            mainWindow.createGameField(height, width);
            mainWindow.setVisible(true);
            mainWindow.setBombsCount(count);
            timer.stopTimer();
        });
        model.setImageListener(mainWindow::setCellImage);
        model.setMinesCountListener(mainWindow::setBombsCount);
        model.setDefeatListener(this::openLoseWindow);
        model.setVictoryListener(() -> {
            if (model.inTime()){
                openNewRecordWindow();
            }
            openWinWindow();
        });
        model.setStartTimerListener(timer::startTimer);
        model.setStopTimerListener(timer::stopTimer);
        model.setRecordListenerNovice(highScoresWindow::setNoviceRecord);
        model.setRecordListenerMedium(highScoresWindow::setMediumRecord);
        model.setRecordListenerExpert(highScoresWindow::setExpertRecord);
    }

    private void subscribeOnTimerAction(){
        log.debug("Подписки на события таймера");
        timer.setTimerTickListener(mainWindow::setTimerValue);
    }





}
