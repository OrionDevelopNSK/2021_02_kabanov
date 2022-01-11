package server.app;

import dto.Deserializer;
import dto.Message;
import dto.Serializer;
import dto.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Server {
    private static final Logger log = LoggerFactory.getLogger(Server.class);
    private static final int CONNECTION_VERIFICATION_PERIOD = 1000;

    private final List<Socket> clients;
    private final HashMap<Integer, String> clientNames;
    private final int port;
    private boolean isCheckingConnection;
    private final Deserializer deserializer = new Deserializer();
    private final Serializer serializer = new Serializer();

    public Server() {
        clients = new ArrayList<>();
        clientNames = new HashMap<>();
        port = new Configurator().getPort();
    }

    @SuppressWarnings("InfiniteLoopStatement")
    public void startServer() throws IOException {
        log.info("Запуск сервера");
        Thread clientProcessingThread = new Thread(this::processClient);
        clientProcessingThread.setDaemon(true);
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            clientProcessingThread.start();
            while (true) {
                Socket socket = serverSocket.accept();
                log.debug("Подключение нового клиента");
                new Thread(()-> authentication(socket)).start();
            }
        }
    }

    private void authentication(Socket client) {
        log.info("Аутентификация");
        while (true) {
            try {
                InputStream inputStream = client.getInputStream();
                int available = inputStream.available();
                if (available > 0) {
                    byte[] bytes = inputStream.readNBytes(available);
                    Message message = deserializer.deserializeMessage(new String(bytes, StandardCharsets.UTF_8));
                    String nameSender = message.getNameSender();
                    if (clientNames.containsValue(nameSender)) {
                        log.debug("Имя занято");
                        message.setStatusCode(StatusCode.CODE_NAME_UNAVAILABLE.name());
                        outputStreamWrite(client, serializer.serializeMessage(message).getBytes(StandardCharsets.UTF_8));
                    } else {
                        log.debug("Имя доступно");
                        message.setLocalDateTime("");
                        addNewClient(client, message, nameSender);
                        break;
                    }
                }
            } catch (IOException e) {
                log.error("Ошибка ввода/вывода", e.fillInStackTrace());
            }
        }
    }

    private synchronized void addNewClient(Socket client, Message message, String nameSender) {
        log.info("Добавление нового клиента");
        clientNames.put(client.hashCode(), nameSender);
        clients.add(client);
        sendListUserName(message, "вошел в чат");
    }

    private void sendListUserName(Message message, String notice) {
        log.info("Оповещение об изменении количества подключенных клиентов");
        Optional<String> data = clientNames.values().
                stream().
                sorted().
                reduce((s1, s2) -> s1 + "\n" + s2);
        message.setMessage(notice);
        message.setData(data.orElse(null));
        message.setStatusCode(StatusCode.CODE_UPDATE_USER_COUNT.name());
        clients.forEach(cl -> outputStreamWrite(cl, serializer.serializeMessage(message).getBytes(StandardCharsets.UTF_8)));
    }

    private void processClient() {
        log.debug("Рассылка сообщений подключенным клиентам");
        Message mes = new Message();
        mes.setStatusCode(StatusCode.COME_IS_CONNECTION.name());
        Thread timeThread = new Thread(this::checkingConnection);
        timeThread.setDaemon(true);
        timeThread.start();
        while (true) {
            List<Socket> clients;
            synchronized (this.clients) {
                clients = new ArrayList<>(this.clients);
            }
            if (isCheckingConnection && clients.size() > 0){
                clients.forEach(cl -> outputStreamWrite(cl, serializer.serializeMessage(mes).getBytes(StandardCharsets.UTF_8)));
                isCheckingConnection = false;
            }
            for (Socket client : clients) {
                try {
                    InputStream inputStream = client.getInputStream();
                    int available = inputStream.available();
                    if (available > 0) {
                        byte[] bytes = inputStream.readNBytes(available);
                        clients.forEach(cl -> outputStreamWrite(cl, bytes));
                    }
                } catch (IOException e) {
                    log.error("Ошибка ввода/вывода", e.fillInStackTrace());
                }
            }
        }
    }

    private void outputStreamWrite(Socket client, byte[] bytes) {
        OutputStream outputStream;
        try {
            outputStream = client.getOutputStream();
            outputStream.write(bytes);
            outputStream.flush();
        } catch (SocketException e) {
            removeClient(client);
        } catch (IOException e) {
            log.error("Ошибка ввода/вывода", e.fillInStackTrace());
        }
    }

    private synchronized void removeClient(Socket client) {
        log.info("Удаление отключившегося клиента");
        String name = this.clientNames.get(client.hashCode());
        this.clientNames.remove(client.hashCode());
        this.clients.remove(client);
        Message message = new Message();
        message.setNameSender(name);
        sendListUserName(message, " покинул чат");
    }

    private void checkingConnection(){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                isCheckingConnection = true;
            }
        };
        Timer timer = new Timer("Timer");
        timer.scheduleAtFixedRate(timerTask,CONNECTION_VERIFICATION_PERIOD, CONNECTION_VERIFICATION_PERIOD);
    }

}
