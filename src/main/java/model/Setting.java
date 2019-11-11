package main.java.model;

import java.util.UUID;

public class Setting implements Comparable<Setting> {
    private UUID ID;
    private String name;
    private String value;
    private boolean exportable;

    public Setting(String name, String value) {
        generateID();
        this.name = name;
        this.value = value;
        this.exportable = true;
    }

    public Setting(String name, String value, boolean exportable) {
        generateID();
        this.name = name;
        this.value = value;
        this.exportable = exportable;
    }

    public Setting(UUID id, String name, String value, boolean exportable) {
        this.ID = id;
        this.name = name;
        this.value = value;
        this.exportable = exportable;
    }

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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isExportable() {
        return exportable;
    }

    public void setExportable(boolean exportable) {
        this.exportable = exportable;
    }

    @Override
    public int compareTo(Setting o) {
        return this.ID.compareTo(o.getID());
    }

    @Override
    public String toString() {
        return "Setting:" + this.name + "=" + this.value;
    }
}
