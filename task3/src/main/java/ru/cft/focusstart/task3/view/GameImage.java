package ru.cft.focusstart.task3.view;

import javax.swing.*;

public enum GameImage {
    NUM_1("1.png"),
    NUM_2("2.png"),
    NUM_3("3.png"),
    NUM_4("4.png"),
    NUM_5("5.png"),
    NUM_6("6.png"),
    NUM_7("7.png"),
    NUM_8("8.png"),
    BOMB("mine.png"),
    TIMER("timer.png"),
    CLOSED("cell.png"),
    MARKED("flag.png"),
    EMPTY("empty.png"),
    BOMB_ICON("mineImage.png"),
    ;

    private final String fileName;
    private ImageIcon imageIcon;
    private static final GameImage[] gameImages = GameImage.values();

    GameImage(String fileName) {
        this.fileName = fileName;
    }

    public static GameImage getGameImage(int position){
        return gameImages[position - 1];
    }

    public synchronized ImageIcon getImageIcon() {
        if (imageIcon == null) {
            imageIcon = new ImageIcon(ClassLoader.getSystemResource(fileName));
        }
        return imageIcon;
    }
}
