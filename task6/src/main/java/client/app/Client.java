package client.app;

import client.model.ModelImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class Client {
    private static final Logger log = LoggerFactory.getLogger(Client.class);
    public static final String HOST = "127.0.0.1";

    private final ModelImpl model;

    public Client(ModelImpl model) {
        this.model = model;
    }

    public void createClient() {
        Thread clientThread = new Thread(this::run);
        clientThread.start();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        try (Socket socket = new Socket(HOST, model.getPortNumber())) {
            while (true) {
                int available = socket.getInputStream().available();
                if (available > 0) {
                    log.debug("Передача сообщения с сервера");
                    byte[] bytes = socket.getInputStream().readNBytes(available);
                    model.createMessageToReading(new String(bytes, StandardCharsets.UTF_8));
                }
                if (model.isMessageReady) {
                    log.debug("Передача сообщения на сервер");
                    model.isMessageReady = false;
                    OutputStream outputStream = socket.getOutputStream();
                    outputStream.write(model.getMessage().getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                }
            }
        } catch (SocketException e) {
            model.disconnect("Сервер недоступен");
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода", e.fillInStackTrace());
        }
    }


}
