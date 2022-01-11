package ru.cft.focusstart.task5.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Logger log = LoggerFactory.getLogger(Storage.class);

    private final int storageSize;
    private final List<Resource> storageResources = new ArrayList<>();

    public Storage(int storageSize) {
        this.storageSize = storageSize;
    }

    public synchronized void produce (Producer producer, Resource resource) {
        while (isFillStorage()){
            log.info("{}: \tСклад заполнен, невозможно поместить ресурс на склад,{}", Instant.now(),
                    returnDescriptionOfCountResources());
            try {
                log.info("{}: \tProducer {} {} переходит в режим ожидания", Instant.now(), producer.id(), Thread.currentThread());
                wait();
                log.info("{}: \tProducer {} {} возобновил работу", Instant.now(), producer.id(), Thread.currentThread());
            } catch (InterruptedException e) {
                log.error("Поток прерван", e.fillInStackTrace());
                Thread.currentThread().interrupt();
            }
        }
        storageResources.add(resource);
        log.info("{}: \tProducer {} {} поместил ресурс id.{} на склад, {}", Instant.now(), producer.id(),
                Thread.currentThread(), resource.getId(), returnDescriptionOfCountResources());
        notifyAll();
    }

    public synchronized void consume(Consumer consumer) {
        while (isEmptyStorage()){
            log.info("{}: \tСклад пуст, невозможно взять ресурс со склада, {}", Instant.now(),
                    returnDescriptionOfCountResources());
            try {
                log.info("{}: \tConsumer {} {} переходит в режим ожидания", Instant.now(), consumer.id(), Thread.currentThread());
                wait();
                log.info("{}: \tConsumer {} {} возобновил работу", Instant.now(), consumer.id(), Thread.currentThread());
            } catch (InterruptedException e) {
                log.error("Поток прерван", e.fillInStackTrace());
                Thread.currentThread().interrupt();
            }
        }

        String id = storageResources.get(0).getId();
        storageResources.remove(0);
        log.info( "{}: \tConsumer {} {} получил ресурс id.{} со склада, {}", Instant.now(), consumer.id(),
                Thread.currentThread(), id, returnDescriptionOfCountResources());
        notifyAll();
    }

    private boolean isEmptyStorage() {
        return storageResources.size() == 0;
    }

    private boolean isFillStorage() {
        return storageResources.size() == storageSize;
    }

    private String returnDescriptionOfCountResources(){
        return storageResources.size() + " единиц из " + storageSize + " возможных";
    }
}
