package server.app;

import server.app.exceptions.ReadFilePropertiesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configurator {
    private final static Logger log = LoggerFactory.getLogger(Configurator.class);
    private final static String PORT = "port";
    private final static String PATH = "configuration.properties";

    private final Properties prop = new Properties();
    private final int port;

    public Configurator() {
        readProperties();
        port = returnProperty();
    }

    private void readProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PATH)) {
            prop.load(input);

        } catch (IOException e) {
            throw new ReadFilePropertiesException(e);
        }
    }

    public int getPort() {
        return port;
    }

    private int returnProperty() {
        log.info("Возврат свойства {} из файла свойств", Configurator.PORT);
        return Integer.parseInt(prop.getProperty(Configurator.PORT));
    }
}
