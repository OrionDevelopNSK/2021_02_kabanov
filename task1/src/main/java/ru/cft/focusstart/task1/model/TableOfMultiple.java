package ru.cft.focusstart.task1.model;

import ru.cft.focusstart.task1.controller.Validator;
import ru.cft.focusstart.task1.view.ViewerToConsole;


public class TableOfMultiple {
    public static void main(String[] args) {
        Validator validator = new Validator();
        TableCreator tableCreator = new TableCreator(validator.getSizeTable());
        StringBuilder tableOfMultiply = tableCreator.getTable();
        new ViewerToConsole(tableOfMultiply);
    }
}
