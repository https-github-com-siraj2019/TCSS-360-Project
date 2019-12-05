package main.java.actions;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import main.java.controller.Controllers;
import main.java.model.Document;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Launches a file import dialog.
 * Can be called from multiple sources, in contrast to anon inner classes which need to be rewritten for each usage.
 *
 * Gets a file from a file chooser and sends the file to the document handler to import.
 */
public class ExportFileDialogAction implements EventHandler {

    ObservableList<Document> docList;
    Document docToExport;
    File toWrite;

    public ExportFileDialogAction() {
        try {
            this.docList = Controllers.viewHandler.getSelectedDocuments();
        }   catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(Event event) {
        try {
            this.docToExport = docList.get(0);

            JFileChooser fileToExportDialog = new JFileChooser();
            fileToExportDialog.setSelectedFile(new File(docToExport.getName()));

            int openedFile = fileToExportDialog.showSaveDialog(null);
            if(openedFile == JFileChooser.APPROVE_OPTION) {
                toWrite = fileToExportDialog.getSelectedFile();
                Controllers.documentHandler.exportDocument(docToExport, toWrite);
            }
        } catch (IndexOutOfBoundsException e) {
            new Alert(Alert.AlertType.ERROR, "No file selected").show();
        }

        catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
