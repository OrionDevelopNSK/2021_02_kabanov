package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task3.view.ButtonType;
import ru.cft.focusstart.task3.view.GameImage;

public class Game {
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    private final Map map;
    private final Cell[][] field;
    private int countOpenCells;
    private int countRemainingMines;
    private int countFlags;
    private boolean isDefeat;
    private ImageTypeListener imageTypeListener;
    private MineCountListener mineCountListener;
    private VictoryListener victoryListener;
    private DefeatListener defeatListener;
    private TimerListener startTimerListener;
    private TimerListener stopTimerListener;
    public boolean isFirstClick = true;

    public Game(Map map) {
        this.field = map.getField();
        this.countRemainingMines = map.getCountMines();
        this.map = map;
    }

    public void setImageListener(ImageTypeListener listener) {
        this.imageTypeListener = listener;
    }

    public void setMinesCountListener(MineCountListener listener) {
        this.mineCountListener = listener;
    }

    public void setVictoryListener(VictoryListener listener) {
        this.victoryListener = listener;
    }

    public void setDefeatListener(DefeatListener listener) {
        this.defeatListener = listener;
    }

    public void setStartTimerListener(TimerListener listener) {this.startTimerListener = listener;}

    public void setStopTimerListener(TimerListener listener) {this.stopTimerListener = listener;}

    private void victory(){
        stopTimerListener.onTimerStatusChanged();
        victoryListener.onVictoryChanged();
    }

    private void defeat(){
        isDefeat = true;
        stopTimerListener.onTimerStatusChanged();
        defeatListener.onDefeatChanged();
    }

    public void clickedOnCell(int x, int y, ButtonType buttonType) {
        final int xPos = y;
        final int yPos = x;
        Cell cell = field[xPos][yPos];
        checkingFirstLeftClick(x, y, buttonType);
        switch (buttonType) {
            case LEFT_BUTTON -> clickedLeftButton(cell);
            case MIDDLE_BUTTON -> clickedMiddleButton(cell);
            case RIGHT_BUTTON -> clickedRightButton(cell);
        }
        checkOnVictory();
    }

    private void checkingFirstLeftClick(int x, int y, ButtonType buttonType) {
        log.debug("Проверка на первое нажатие левой кнопкой");
        if (isFirstClick && buttonType == ButtonType.LEFT_BUTTON){
            map.initialize(x, y);
            startTimerListener.onTimerStatusChanged();
            isFirstClick = false;
        }
    }

    private void clickedLeftButton(Cell cell) {
        log.debug("Нажата левая кнопка");
        if (!cell.isFlagged && !cell.isOpened) {
            if (cell.isContainsBomb) {
                imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.BOMB);
                defeat();
                return;
            }
            openingCell(cell);
        }
    }

    private void checkOnVictory() {
        log.debug("Проверка на победу");
        if (countOpenCells + countFlags + countRemainingMines == map.getTotalCells()) victory();
    }

    private void clickedMiddleButton(Cell cell) {
        log.debug("Нажата средняя кнопка");
        //  если число на клетке соответвует количество флагов на соседних ячейках,
        //  открыть все ячейки без флагов
        if (cell.isShowBombCount &&
                cell.countNeighborhoodBombs == cell.neighbors
                        .stream().filter(o -> o.isFlagged).count()) {
            for (Cell c : cell.neighbors) {
                if (isDefeat) break;
                if (!c.isFlagged && !c.isOpened) openingCell(c);
            }
        }
    }

    private void clickedRightButton(Cell cell) {
        log.debug("Нажата правая кнопка");
        if (!cell.isFlagged && !cell.isOpened && countRemainingMines > 0) {
            imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.MARKED);
            cell.isFlagged = true;
            countRemainingMines--;
            countFlags++;
            mineCountListener.onMineCountChanged(countRemainingMines);
        } else if (cell.isFlagged && !cell.isOpened){
            imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.CLOSED);
            cell.isFlagged = false;
            countRemainingMines++;
            countFlags--;
            mineCountListener.onMineCountChanged(countRemainingMines);
        }
    }

    private void openingCell(Cell cell) {
        log.debug("Открыть текущую ячейку");
        if(!cell.isFlagged && !cell.isContainsBomb){
            cell.isOpened = true;
            imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.EMPTY);
            countOpenCells++;
        }else if (!cell.isFlagged && cell.isContainsBomb){
            imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.BOMB);
            defeat();
            return;
        }
        openingNeighborCells(cell);
    }

    private void openingNeighborCells(Cell cell) {
        log.debug("Открыть соседнюю ячейку");
        if (cell.countNeighborhoodBombs == 0) {
            for (Cell c : cell.neighbors) {
                if (!c.isFlagged && !c.isOpened) openingCell(c);
            }
        }
        else {
            showCountOfNeighboringBombs(cell);
        }
    }

    private void showCountOfNeighboringBombs(Cell cell) {
        log.debug("Установка на ячейку картинки с числом");
        imageTypeListener.onImageTypeChanged(cell.xPos, cell.yPos, GameImage.getGameImage(cell.countNeighborhoodBombs));
        cell.isShowBombCount = true;
    }
}
