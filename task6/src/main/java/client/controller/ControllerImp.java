package client.controller;

import client.model.Model;

public class ControllerImp implements Controller {
    private final Model model;
    private String userName;

    public ControllerImp(Model model) {
        this.model = model;
    }

    @Override
    public void createMessage(String message){
        model.createMessageToSend(userName, message);
    }

    @Override
    public void startClient(String name) {
        userName = name;
        model.startClient(name);
    }

    @Override
    public void createPortNumber(int portNumber){
        model.createPortNumber(portNumber);
    }
}
