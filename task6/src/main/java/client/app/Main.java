package client.app;


import client.controller.Controller;
import client.controller.ControllerImp;
import client.model.Model;
import client.model.ModelImpl;
import client.view.MainWindow;
import client.view.RegistrationWindow;
import client.view.ViewImpl;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Model model = new ModelImpl();
        Controller controllerImp = new ControllerImp(model);
        new ViewImpl(controllerImp, model);
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow();
            new RegistrationWindow(mainWindow);
        });
    }
}
