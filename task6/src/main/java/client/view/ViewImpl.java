package client.view;

import client.controller.Controller;
import client.model.Model;
import dto.Message;

import java.util.function.Consumer;

public class ViewImpl implements View{
    private final MainWindow mainWindow;
    private final RegistrationWindow registrationWindow;
    private final DisconnectWindow disconnectWindow;
    private final Model model;
    private final Controller controller;

    public ViewImpl(Controller controller, Model model) {
        this.controller = controller;
        this.model = model;
        mainWindow = new MainWindow();
        registrationWindow = new RegistrationWindow(mainWindow);
        disconnectWindow = new DisconnectWindow(mainWindow);
        subscribe();
        mainWindow.setVisible(true);
        registrationWindow.setVisible(true);
    }

    private void subscribe(){
        model.setMessageSender(this::setMessageTextMainWindow);
        model.setUsersListSender(this::setUsersTextMainWindow);
        model.setUserConnectedSender(t -> closeRegistrationWindow());
        model.setUserDisconnectedSender(this::openDisconnectWindow);
        model.setUserNotConnectedSender(this::setTextToLabelRegistrationWindow);
        setUserNameListenerRegistrationWindow(controller::startClient);
        setPortNumberListenerRegistrationWindow(controller::createPortNumber);
        setMessageListenerMainWindow(controller::createMessage);
    }

    @Override
    public void setMessageListenerMainWindow(MessageListener messageListener) {
        mainWindow.setMessageListenerMainWindow(messageListener);
    }

    @Override
    public void setMessageTextMainWindow(Message message) {
        mainWindow.setMessageTextMainWindow(message);
    }

    @Override
    public void setUsersTextMainWindow(String users) {
        mainWindow.setUsersTextMainWindow(users);
    }

    @Override
    public void setTextToLabelRegistrationWindow(String message) {
        registrationWindow.setTextToLabelRegistrationWindow(message);
    }

    @Override
    public void setUserNameListenerRegistrationWindow(Consumer<String> userNameListener) {
        registrationWindow.setUserNameListenerRegistrationWindow(userNameListener);
    }

    @Override
    public void setPortNumberListenerRegistrationWindow(Consumer<Integer> portNumberListener) {
        registrationWindow.setPortNumberListenerRegistrationWindow(portNumberListener);
    }

    @Override
    public void setTextToLabelDisconnectWindow(String message) {
        disconnectWindow.setTextToLabelDisconnectWindow(message);
    }

    @Override
    public void openDisconnectWindow(String description) {
        disconnectWindow.openDisconnectWindow(description);
    }

    @Override
    public void closeRegistrationWindow(){registrationWindow.closeRegistrationWindow();}
}
