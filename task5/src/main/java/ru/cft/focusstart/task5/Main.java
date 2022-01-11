package ru.cft.focusstart.task5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.cft.focusstart.task5.app.Configurator;
import ru.cft.focusstart.task5.app.ProducerConsumerController;
import ru.cft.focusstart.task5.exceptions.IncorrectNumberException;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Configurator configurator = new Configurator();
            new ProducerConsumerController(configurator);
        } catch (IncorrectNumberException e) {
            log.error("Некорректные входные данные: {}", e.getMessage());
        }
    }
}
