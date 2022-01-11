package ru.cft.focusstart.task3.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Map {
    private static final Logger log = LoggerFactory.getLogger(Map.class);

    private final int heightMap;
    private final int widthMap;
    private final int minesCount;
    private final int totalCells;
    private Cell [][] field;
    private int countMinesPlaced;

    public Map(GameFieldParameters gameFieldParameters) {
        this.heightMap = gameFieldParameters.getHeightGameField();
        this.widthMap = gameFieldParameters.getWidthGameField();
        this.minesCount = gameFieldParameters.getCountMines();
        this.totalCells = widthMap * heightMap;
        createMap(heightMap, widthMap);
        fillMap(heightMap, widthMap);
    }

    public void initialize(int x, int y) {
        placementOfBombs(x,y);
        findNeighborsCells();
        countingNeighborhoodBombs();
    }

    public Cell[][] getField() {
        return field;
    }

    public int getTotalCells() {
        return totalCells;
    }

    public int getCountMines() {
        return minesCount;
    }

    private void createMap(int heightMap, int widthMap){
        log.debug("Создание игрового поля");
        field = new Cell[heightMap][widthMap];
    }

    private void fillMap(int heightMap, int widthMap){
        log.debug("Заполнение игрового поля ячейками");
        for (int i = 0; i < heightMap; i++) {
            for (int j = 0; j < widthMap; j++) {
                field[i][j] = new Cell();
                field[i][j].xPos = j;
                field[i][j].yPos = i;
            }
        }
    }

    private void placementOfBombs(int x, int y){
        log.debug("Установка нужного количества мин");
        Random random = new Random();
        while (countMinesPlaced < minesCount){
            int xPosition = random.nextInt(widthMap);
            int yPosition = random.nextInt(heightMap);

            //  исключить создание мины в ячейке первого левого клика
            if (xPosition == x && yPosition == y) continue;
            if (!field[yPosition][xPosition].isContainsBomb){
                field[yPosition][xPosition].isContainsBomb = true;
                countMinesPlaced++;
            }
        }
    }

    private void findNeighborsCells(){
        log.debug("Поиск соседних ячеек");
        for (int i = 0; i < heightMap; i++) {
            for (int j = 0; j < widthMap; j++) {
                if(j > 0)
                    field[i][j].neighbors.add(field[i][j-1]);
                if(j < widthMap-1)
                    field[i][j].neighbors.add(field[i][j+1]);
                if(i > 0)
                    field[i][j].neighbors.add(field[i-1][j]);
                if(i < heightMap-1)
                    field[i][j].neighbors.add(field[i+1][j]);
                if (j > 0 && i > 0)
                    field[i][j].neighbors.add(field[i-1][j-1]);
                if (j < widthMap-1 && i < heightMap-1)
                    field[i][j].neighbors.add(field[i+1][j+1]);
                if (j > 0 && i < heightMap-1)
                    field[i][j].neighbors.add(field[i+1][j-1]);
                if (j < widthMap-1 && i > 0)
                    field[i][j].neighbors.add(field[i-1][j+1]);
            }
        }
    }

    private void countingNeighborhoodBombs(){
        log.debug("Подсчет количества соседних ячеек с минами");
        for (int i = 0; i < heightMap; i++) {
            for (int j = 0; j < widthMap; j++) {
                for (Cell cell: field[i][j].neighbors){
                    if (cell.isContainsBomb) field[i][j].countNeighborhoodBombs += 1;
                }
            }
        }
    }




}
