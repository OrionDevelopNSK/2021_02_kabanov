package client.model;

import dto.Message;

import java.util.function.Consumer;

public interface Model {
    void createMessageToSend(String name, String message);

    void createMessageToReading(String str);

    String getMessage();

    int getPortNumber();

    void setMessageSender(Consumer<Message> messageSender);

    void setUsersListSender(Consumer<String> usersListSender);

    void setUserConnectedSender(Consumer<String> userConnectedSender);

    void setUserDisconnectedSender(Consumer<String> userDisconnectedSender);

    void setUserNotConnectedSender(Consumer<String> userNotConnectedSender);

    void startClient(String name);

    void createPortNumber(int portNumber);

    void disconnect(String description);
}
