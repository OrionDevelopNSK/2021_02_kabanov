package ru.cft.focusstart.task1.view;

public abstract class Viewer {
    StringBuilder data;

    public Viewer(StringBuilder data) {
        this.data = data;
    }

    public abstract void dataOutput();
}
