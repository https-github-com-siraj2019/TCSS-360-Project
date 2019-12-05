package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import main.java.model.Document;
import main.java.model.FileEntity;
import main.java.model.Setting;
import main.java.model.Tag;

/**
 * Contains a live list of file entities to feed to the view UI as well as
 * methods to change the entities and columns displayed.
 */
public class ViewHandler {
    private ObservableSet<FileEntity> entitySet;
    private ObservableSet<Tag> columnTagSet;
    private ObservableList<Document> selectedDocuments;

    public ViewHandler() {
        this.entitySet = FXCollections.observableSet();
        this.columnTagSet = FXCollections.observableSet();
        Setting colSetting = Controllers.settingsHandler.getSetting("Columns");
        //if(colSetting.getValue().getClass() == ObservableSet.class) {
            this.columnTagSet = (ObservableSet<Tag>) colSetting.getValue();
        //}

    }

    public ObservableList<Document> getSelectedDocuments() {
        return selectedDocuments;
    }

    public void setSelectedDocuments(ObservableList<Document> selectedDocuments) {
        this.selectedDocuments = selectedDocuments;
    }

    public ObservableSet<FileEntity> getEntitySet() {
        return entitySet;
    }

    public ObservableSet<Tag> getColumnTagSet() {
        return columnTagSet;
    }

    //TODO: update current folder in folder tree


    //TODO: Update view when folder entered

    //TODO: update entity list when filter or current folder changes

    //TODO: update/delete/add documents from UI actions, including batch actions.


}
