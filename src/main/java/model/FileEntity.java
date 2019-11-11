package main.java.model;

import java.util.UUID;

public class FileEntity {
    private UUID ID;
    private String name;

    public UUID getID() {
        return ID;
    }

    public void generateID() {
        this.ID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
