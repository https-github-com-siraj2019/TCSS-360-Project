package main.java.model;

import javafx.beans.InvalidationListener;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import java.util.UUID;

/**
 * Tags contain information about a file entity.
 * Tags can hold any type of value T.
 * Tags can be compared to each other or searched by name.
 * @param <T> Class of value the tag holds.
 */
public class Tag<T> implements Cloneable, Comparable<Tag> {

    private UUID id;
    private String name;
    private T value;

    /**
     * Default constructor.
     * Call constructor with data type: "Tag<datatype>"
     * @param name Display name of the tag.
     * @param value Object of type T to store as the tag value
     */
    public Tag(String name, T value) {
        this.generateID();
        this.name = name;
        this.value = value;
    }

    /**
     * Constructor with specific ID.
     * @param name
     * @param value
     * @param id
     */
    public Tag(String name, T value, UUID id) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    /**
     * Gets ID
     * @return UUID
     */
    public UUID getID() {
        return id;
    }

    /**
     * Sets an ID
     * @param ID UUID
     */
    public void setID(UUID ID) {
        this.id = ID;
    }

    /**
     * Generates a random UUID
     */
    public void generateID() {
        this.id = UUID.randomUUID();
    }

    /**
     * Gets the tag's display name.
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the tag's display name.
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets value of tag of type T
     * @return Object of class T
     */
    public T getValue() {
        return value;
    }

    /**
     * Sets the tag value.
     * @param value Object of class T
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Test to dynamically create properties from data in tag
     * Should update UI with tag value in UI column
     * @return "value" property
     */
    public Property<T> valueProperty() {
        return new ObjectProperty<T>() {
            @Override
            public void bind(ObservableValue<? extends T> observableValue) {

            }

            @Override
            public void unbind() {

            }

            @Override
            public boolean isBound() {
                return false;
            }

            @Override
            public Object getBean() {
                return null;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public T get() {
                return value;
            }

            @Override
            public void addListener(ChangeListener<? super T> changeListener) {

            }

            @Override
            public void removeListener(ChangeListener<? super T> changeListener) {

            }

            @Override
            public void addListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void removeListener(InvalidationListener invalidationListener) {

            }

            @Override
            public void set(T t) {

            }
        };
    }

    @Override
    public int compareTo(Tag o) {
        if(this.name.equals(o.getName()) &&
        this.value == o.getValue()) {
            return 0;
        }
        return -1;
    }
}
