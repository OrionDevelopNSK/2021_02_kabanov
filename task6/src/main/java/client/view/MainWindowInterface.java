package client.view;

import dto.Message;

public interface MainWindowInterface {
    void setMessageListenerMainWindow(MessageListener messageListener);

    void setMessageTextMainWindow(Message message);

    void setUsersTextMainWindow(String users);
}
