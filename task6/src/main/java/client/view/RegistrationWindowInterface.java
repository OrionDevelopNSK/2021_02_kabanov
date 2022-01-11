package client.view;

import java.util.function.Consumer;

public interface RegistrationWindowInterface {
    void setTextToLabelRegistrationWindow(String message);

    void closeRegistrationWindow();

    void setUserNameListenerRegistrationWindow(Consumer<String> userNameListener);

    void setPortNumberListenerRegistrationWindow(Consumer<Integer> portNumberListener);
}
