package ru.cft.focusstart.task1.view;

public class ViewerToConsole extends Viewer {
    public ViewerToConsole(StringBuilder data) {
        super(data);
        dataOutput();
    }

    @Override
    public void dataOutput() {
        System.out.println(data);
    }
}
