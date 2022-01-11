package ru.cft.focusstart.task5.app;

import java.util.UUID;

public final class Resource {
    private final String  id;

    public Resource() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return id;
    }
}
