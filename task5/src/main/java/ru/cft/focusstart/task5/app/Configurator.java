package ru.cft.focusstart.task5.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task5.exceptions.IncorrectNumberException;
import ru.cft.focusstart.task5.exceptions.ReadFilePropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Configurator {
    private final static Logger log = LoggerFactory.getLogger(Configurator.class);
    private final static String PRODUCER_COUNT_PROPERTY_NAME = "producerCount";
    private final static String CONSUMER_COUNT_PROPERTY_NAME = "consumerCount";
    private final static String STORAGE_SIZE_PROPERTY_NAME = "storageSize";
    private final static String PRODUCER_TIME_PROPERTY_NAME = "producerTime";
    private final static String CONSUMER_TIME_PROPERTY_NAME = "consumerTime";
    private final static String PATH = "configuration.properties";

    private final Properties prop = new Properties();
    private final int producerCount;
    private final int consumerCount;
    private final int storageSize;
    private final int producerTime;
    private final int consumerTime;

    public Configurator() {
        readProperties();
        producerCount = returnProperty(PRODUCER_COUNT_PROPERTY_NAME);
        consumerCount = returnProperty(CONSUMER_COUNT_PROPERTY_NAME);
        storageSize = returnProperty(STORAGE_SIZE_PROPERTY_NAME);
        producerTime = returnProperty(PRODUCER_TIME_PROPERTY_NAME);
        consumerTime = returnProperty(CONSUMER_TIME_PROPERTY_NAME);
    }

    private void readProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(PATH)) {
            prop.load(input);
        } catch (IOException e) {
            throw new ReadFilePropertiesException(e);
        }
    }

    public int getProducerCount() {
        return producerCount;
    }

    public int getConsumerCount() {
        return consumerCount;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public int getProducerTime() {
        return producerTime;
    }

    public int getConsumerTime() {
        return consumerTime;
    }

    private int returnProperty(String propertyName) throws NoSuchElementException, NumberFormatException {
        log.info("Возврат свойства {} из файла свойств\n", propertyName);
        int tmp;
        try {
            tmp = Integer.parseInt(prop.getProperty(propertyName));
        } catch (NoSuchElementException | NumberFormatException e) {
            throw new IncorrectNumberException("Указано не число, либо число слишком большого размера", e);
        }
        if (tmp < 0) {
            throw new IncorrectNumberException("Число не может быть отрицательным");
        }
        if (tmp == 0) {
            throw new IncorrectNumberException("Число не может быть нулевым");
        }
        return tmp;
    }
}
