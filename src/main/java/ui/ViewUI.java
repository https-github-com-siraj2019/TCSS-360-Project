package main.java.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import main.java.actions.ExportFileDialogAction;
import main.java.actions.ImportFileDialogAction;
import main.java.controller.Controllers;
import main.java.controller.DocumentHandler;
import main.java.model.Document;
import main.java.model.FileEntity;
import main.java.model.Setting;

import java.awt.*;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.Date;

/**
 * Main Document list UI
 * Displays file entities in the main window in a table.
 * Table columns are based on properties of the entities, such as name, file, etc.
 */
public class ViewUI{

    private DocumentHandler docHandler;
    private TableView<Document> viewList;
    private ObservableSet<TableColumn> colSet;
    private ObservableList<Document> selectedDocuments;
    private ContextMenu contextMenu;

    /**
     * Default constructor
     * @param rootPane link to the root pane where this table should be displayed.
     */
    public void start(BorderPane rootPane) {
        this.docHandler = Controllers.documentHandler;
        this.colSet = FXCollections.observableSet();
        this.selectedDocuments = FXCollections.observableArrayList();
        this.contextMenu = new ContextMenu();
        this.initDocList(rootPane);
    }

    /**
     * Updates and sets columns, sets initial parameters, and displays the table in the rootPane.
     * @param rootPane link to the root pane where this table should be displayed.
     */
    private void initDocList(BorderPane rootPane) {
        try{
            viewList = new TableView<Document>();

            rootPane.setCenter(viewList);

            // Sets Col names
            this.setColumns();
            this.updateColumns();

            viewList.setItems(docHandler.getDocuments());

            // Only select a single document at a time
            viewList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            // Be able to edit values
            viewList.setEditable(true);
            // Listen to changes on the selected document
            viewList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Document>() {
                @Override
                public void changed(ObservableValue<? extends Document> observableValue, Document document, Document t1) {

                }
            });
            // Binds the selected document when focused.
            viewList.getFocusModel().focusedItemProperty().addListener(new ChangeListener<Document>() {
                @Override
                public void changed(ObservableValue<? extends Document> observableValue, Document document, Document t1) {
                    selectedDocuments.removeAll();
                    selectedDocuments.add(t1);
                }
            });
            // Binds the list in the view handler to the selection.
            Controllers.viewHandler.setSelectedDocuments(this.selectedDocuments);

            // Adds right click menu
            viewList.setContextMenu(contextMenu);
            initContextMenu();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Document> getSelectedDocuments() {
        return selectedDocuments;
    }

    /**
     * Gets the list of tags to create columns with from the settings handler's "Columns" setting.
     * If no columns exist in the setting, update the setting with the default required tags from the TagHandler.
     * @throws InvalidClassException
     */
    private void setColumns() throws InvalidClassException {
        Setting<ObservableSet> settingColumns = Controllers.settingsHandler.getSetting("Columns");
        if (settingColumns.getValue().isEmpty()) {
            settingColumns.getValue().addAll(Controllers.tagHandler.getTagSetRequiredFileEntity());
        }
    }

    /**
     * Creates the columns from the column tags and binds them to the table.
     */
    private void updateColumns() {

        // Name Column
        TableColumn nameColumn = new TableColumn("Name");
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<FileEntity,String>("name")
        );
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FileEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FileEntity, String> cellEditEvent) {
                cellEditEvent.getTableView().getItems().get(
                        cellEditEvent.getTablePosition().getRow())
                        .setName(cellEditEvent.getNewValue());
            }
        });
        viewList.getColumns().add(nameColumn);

        // Date Column
        TableColumn dateColumn = new TableColumn("Date");
        dateColumn.setCellValueFactory(
                new PropertyValueFactory<FileEntity, Date>("date")
        );
        viewList.getColumns().add(dateColumn);

        // Project Column
        TableColumn projectColumn = new TableColumn("Project");
        projectColumn.setCellValueFactory(
                new PropertyValueFactory<FileEntity, String>("project")
        );

        projectColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        projectColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FileEntity, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FileEntity, String> cellEditEvent) {
                Document entity = (Document) cellEditEvent.getTableView().getItems().get(
                        cellEditEvent.getTablePosition().getRow());
                String newTag = entity.projectProperty().getValue();
                if (newTag != null) {
                    newTag = cellEditEvent.getNewValue();
                } else {
                    newTag = cellEditEvent.getNewValue();
                }

                entity.projectProperty().setValue(newTag);

            }
        });
        viewList.getColumns().add(projectColumn);

    }

    private void initContextMenu() {
        MenuItem importChoice = new MenuItem("Import...");
        MenuItem exportChoice = new MenuItem("Export...");
        MenuItem openChoice = new MenuItem("Open");

        importChoice.addEventHandler(ActionEvent.ACTION, new ImportFileDialogAction(null));

        exportChoice.addEventHandler(ActionEvent.ACTION, new ExportFileDialogAction());

        openChoice.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Desktop.getDesktop().open(viewList.getSelectionModel().getSelectedItem().getFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        this.contextMenu.getItems().addAll(importChoice, exportChoice, openChoice);
    }
}
