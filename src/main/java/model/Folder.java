package main.java.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

public class Folder extends FileEntity {
    private UUID ID;
    private String name;
    private HashSet<FileEntity> contents;

    public void add(FileEntity entity) {
        this.contents.add(Objects.requireNonNull(entity));
    }

    public void remove(FileEntity entity) {
        this.contents.remove(entity);
    }
}
