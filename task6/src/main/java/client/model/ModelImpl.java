package client.model;

import client.app.Client;
import dto.Deserializer;
import dto.Message;
import dto.Serializer;
import dto.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

public class ModelImpl implements Model{
    private static final Logger log = LoggerFactory.getLogger(ModelImpl.class);
    private static final String DESCRIPTION_NAME_NOT_AVAILABLE = "Имя занято, выберите другое имя";
    private static final String CLIENT_STATUS = "подключен";

    private final Serializer serializer = new Serializer();
    private final Deserializer deserializer = new Deserializer();
    private Consumer<Message> messageSender;
    private Consumer<String> usersListSender;
    private Consumer<String> userConnectedSender;
    private Consumer<String> userDisconnectedSender;
    private Consumer<String> userNotConnectedSender;
    public boolean isMessageReady;
    private String message;
    private int portNumber = 7777;

    @Override
    public void createMessageToSend(String name, String message) {
        this.message = serializer.serializeMessage(name, message);
        isMessageReady = true;
    }

    @Override
    public void createMessageToReading(String str) {
        log.debug("Создание сообщения для чтения");
        Message message = deserializer.deserializeMessage(str);
        if (message.getStatusCode() == null) {
            messageSender.accept(message);
        } else if (message.getStatusCode().equals(StatusCode.CODE_NAME_UNAVAILABLE.name())) {
            userNotConnectedSender.accept(DESCRIPTION_NAME_NOT_AVAILABLE);
        } else if (message.getStatusCode().equals(StatusCode.CODE_UPDATE_USER_COUNT.name())) {
            userConnectedSender.accept("");
            messageSender.accept(message);
            usersListSender.accept(message.getData());
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getPortNumber() {
        return portNumber;
    }

    @Override
    public void setMessageSender(Consumer<Message> messageSender) {
        this.messageSender = messageSender;
    }

    @Override
    public void setUsersListSender(Consumer<String> usersListSender) {
        this.usersListSender = usersListSender;
    }

    @Override
    public void setUserConnectedSender(Consumer<String> userConnectedSender) {
        this.userConnectedSender = userConnectedSender;
    }

    @Override
    public void setUserDisconnectedSender(Consumer<String> userDisconnectedSender) {
        this.userDisconnectedSender = userDisconnectedSender;
    }

    @Override
    public void setUserNotConnectedSender(Consumer<String> userNotConnectedSender) {
        this.userNotConnectedSender = userNotConnectedSender;
    }

    @Override
    public void startClient(String name) {
        createMessageToSend(name, CLIENT_STATUS);
        Client client = new Client(this);
        client.createClient();
    }

    @Override
    public void createPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    @Override
    public void disconnect(String description) {
        userDisconnectedSender.accept(description);
    }
}
