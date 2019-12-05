package main.java.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;

import java.util.Objects;

/**
 * Folders are a file entity.
 * They contain documents and other folders.
 */
public class Folder extends FileEntity {
    private ObservableSet<FileEntity> contents;

    /**
     * Basic Constructor. Creates an empty folder
     * @param name Display name
     * @param parent parent folder, or null if creating the root folder.
     */
    public Folder(String name, Folder parent) {
        super(name, parent);
        this.contents = FXCollections.observableSet();
    }

    /**
     * Adds a file entity to this folder's contents
     * @param entity
     */
    public void add(FileEntity entity) {
        this.contents.add(Objects.requireNonNull(entity));
    }

    /**
     * Removes a file entity from this folder if the entity is in the folder.
     * @param entity
     */
    public void remove(FileEntity entity) {
        this.contents.remove(entity);
    }

    /**
     * Moves a file from this folder to another folder.
     * @param entity the entity to move
     * @param destination the destination folder.
     */
    public void move(FileEntity entity, Folder destination) {
        //TODO: Check for existence of entity and dest.
        destination.add(entity);
        this.remove(entity);
    }
}
