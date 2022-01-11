package ru.cft.focusstart.task5.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class ProducerConsumerController {
    private static final Logger log = LoggerFactory.getLogger(ProducerConsumerController.class);

    private final int producerCount;
    private final int consumerCount;
    private final int producerTime;
    private final int consumerTime;
    private final Storage storage;

    public ProducerConsumerController(Configurator configurator) {
        producerCount = configurator.getProducerCount();
        consumerCount = configurator.getConsumerCount();
        producerTime = configurator.getProducerTime();
        consumerTime = configurator.getConsumerTime();
        storage = new Storage(configurator.getStorageSize());
        createProducers();
        createConsumers();
    }

    private void createProducers() {
        log.info("Создание производителей\n");
        for (int i = 0; i < producerCount; i++) {
            Producer producer = new Producer(i);
            new Thread(() -> {
                try {
                    while (true) {
                        log.info("{}: \tProducer {} {} начал работу", Instant.now(), producer.id(), Thread.currentThread());
                        storage.produce(producer, new Resource());
                        log.info("{}: \tProducer {} {} переходит в состояние сна",
                                Instant.now(), producer.id(), Thread.currentThread());
                        Thread.sleep(producerTime);
                    }
                } catch (InterruptedException e) {
                    log.error("Поток прерван", e.fillInStackTrace());
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private void createConsumers() {
        log.info("Создание потребителей\n");
        for (int i = 0; i < consumerCount; i++) {
            Consumer consumer = new Consumer(i);
            new Thread(() -> {
                try {
                    while (true) {
                        log.info("{}: \tConsumer {} {} начал работу", Instant.now(), consumer.id(), Thread.currentThread());
                        storage.consume(consumer);
                        log.info("{}: \tConsumer {} {} переходит в состояние сна",
                                Instant.now(), consumer.id(), Thread.currentThread());
                        Thread.sleep(consumerTime);
                    }
                } catch (InterruptedException e) {
                    log.error("Поток прерван", e.fillInStackTrace());
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }
}
