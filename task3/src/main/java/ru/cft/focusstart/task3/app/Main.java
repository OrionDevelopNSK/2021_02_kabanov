package ru.cft.focusstart.task3.app;

import ru.cft.focusstart.task3.controller.Controller;
import ru.cft.focusstart.task3.controller.ControllerImpl;
import ru.cft.focusstart.task3.model.Model;
import ru.cft.focusstart.task3.model.ModelImpl;
import ru.cft.focusstart.task3.timers.AbstractTimer;
import ru.cft.focusstart.task3.timers.Timer;
import ru.cft.focusstart.task3.view.ViewImpl;

public class Main {
    public static void main(String[] args) {
        AbstractTimer timer = new Timer();
        Model model = new ModelImpl(timer);
        Controller controller = new ControllerImpl(model);
        new ViewImpl(controller, model, timer);
    }
}



