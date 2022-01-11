package ru.cft.focusstart.task3.model;

import java.util.ArrayList;

public class Cell {
    public boolean isOpened;            //открыта или закрыта
    public boolean isFlagged;           //помечена флагом или нет
    public boolean isShowBombCount;     //показывать количество бомб по соседству
    public boolean isContainsBomb;      //содержит бомбу или нет
    public int countNeighborhoodBombs;  //количество бомб по соседству
    public int xPos;
    public int yPos;
    public ArrayList<Cell> neighbors = new ArrayList<>(8);

}
