package main.java.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import main.java.controller.Controllers;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * A file entity is a master object for both folders and documents.
 * File entities contain common properties and tags, such as name, parent folder, and ID.
 * File entities are displayed in the ViewUI table
 */
public class FileEntity {
    private UUID id;
    private String name;
    private Folder parentFolder;
    private Date createdDate;
    protected ObservableSet<Tag> tags;

    /**
     * Basic Constructor.
     * @param name Display name of entity
     * @param parent Parent folder, or null if parent is the root folder.
     */
    public FileEntity(String name, Folder parent) {
        this.generateId();
        this.name = name;
        this.parentFolder = parent;
        this.tags = FXCollections.observableSet();
        this.setTags(Controllers.tagHandler.getTagSetRequiredFileEntity());
        this.createdDate = new Date(System.currentTimeMillis());
    }

    /**
     * Constructor with specific ID.
     * Id-specific construcotr is typically called when loading from a database.
     * @param name
     * @param parent
     * @param id
     */
    public FileEntity(String name, Folder parent, UUID id) {
        this.id = id;
        this.name = name;
        this.parentFolder = parent;
        this.tags = FXCollections.observableSet();
        // Sets initial tags from tag handler required tags.
        this.setTags(Controllers.tagHandler.getTagSetRequiredFileEntity());
    }

    /**
     * Sets initial tag values.
     */
    protected void initTags() {
        this.getTag("Name").setValue(this.name);
        this.getTag("Root Folder").setValue(this.parentFolder);
    }

    /**
     * Gets the entity's tags
     * @return ObservableSet of Tags
     */
    public ObservableSet<Tag> getTags() {
        return tags;
    }

    /**
     * Adds a tag to this entity.
     * Not checked to see if entity ought to have a particular tag.
     * @param tag Tag to add
     */
    public void addTag(Tag tag) {
        this.tags.add(Objects.requireNonNull(tag));
    }

    /**
     * Removes a tag from the entity if the entity has this tag.
     * @param tag Tag to remove
     */
    public void removeTag(Tag tag) {
        this.tags.remove(Objects.requireNonNull(tag));
    }

    /**
     * Gets a tag object from a tag's name if the entity has a tag with that name.
     * @param name Name to search for
     * @return Matching Tag
     */
    public Tag getTag(String name){
        Stream<Tag> filter = this.tags.stream().filter(t -> t.getName().compareToIgnoreCase(name)==0);
        return filter.findFirst().get();
    }

    /**
     * Batch overwrite tags from a given set.
     * Tags that are not in the new set will be lost.
     * @param tags Tag set to overwrite this entity's tags with.
     */
    public void setTags(Set<Tag> tags) {
        this.tags = FXCollections.observableSet();
        this.tags.addAll(tags);
    }

    /**
     * Gets the unique ID.
     * @return
     */
    public UUID getId() {
        return id;
    }

    /**
     * Generates a random unique ID
     */
    public void generateId() {
        this.id = UUID.randomUUID();
    }

    /**
     * Sets a unique ID.
     * Unique UUID objects can be generated from strings with UUID.fromString().
     * @param id UUID object to set
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Gets the entity's display name
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the entity's display name
     * @param name new display name
     */
    public void setName(String name) {
        this.name = name;
        this.getTag("Name").setValue(this.name);
    }

    /**
     * Gets the display name property.
     * For use in JavaFX ui's where changes to properties are automatically updated in the UI.
     * @return "name" property
     */
    public Property nameProperty() {
        return new Property() {
            @Override
            public void bind(ObservableValue observableValue) {

            }

            @Override
            public void unbind() {

            }

            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public void bindBidirectional(Property property) {

            }

            @Override
            public void unbindBidirectional(Property property) {

            }

            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return "name";
            }

            @Override
            public void addListener(ChangeListener changeListener) {

            }

            @Override
            public void removeListener(ChangeListener changeListener) {

            }

            @Override
            public Object getValue() {
                return name;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void setValue(Object o) {
                this.setValue(o);
            }
        };
    }

    public Property tagsProperty() {
        return new Property() {
            @Override
            public void bind(ObservableValue observableValue) {
                this.setValue(observableValue);
            }

            @Override
            public void unbind() {

            }

            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public void bindBidirectional(Property property) {

            }

            @Override
            public void unbindBidirectional(Property property) {

            }

            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return "tags";
            }

            @Override
            public void addListener(ChangeListener changeListener) {

            }

            @Override
            public void removeListener(ChangeListener changeListener) {

            }

            @Override
            public Object getValue() {
                return tags;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void setValue(Object o) {

            }
        };
    }

    public Property dateProperty() {
        return new Property() {
            @Override
            public void bind(ObservableValue observableValue) {

            }

            @Override
            public void unbind() {

            }

            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public void bindBidirectional(Property property) {

            }

            @Override
            public void unbindBidirectional(Property property) {

            }

            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return "date";
            }

            @Override
            public void addListener(ChangeListener changeListener) {

            }

            @Override
            public void removeListener(ChangeListener changeListener) {

            }

            @Override
            public Object getValue() {
                return createdDate;
            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void setValue(Object o) {

            }
        };
    }

    @Override
    public String toString() {
        return "Entity" + this.getName();
    }

}
