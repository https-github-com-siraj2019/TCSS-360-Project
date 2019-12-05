package main.java.actions;

import javafx.event.Event;
import javafx.event.EventHandler;
import main.java.controller.Controllers;
import main.java.model.Folder;

import javax.swing.*;
import java.io.File;

/**
 * Launches a file import dialog.
 * Can be called from multiple sources, in contrast to anon inner classes which need to be rewritten for each usage.
 *
 * Gets a file from a file chooser and sends the file to the document handler to import.
 */
public class ImportFileDialogAction implements EventHandler {


    File toRead;
    Folder destFolder;

    public ImportFileDialogAction(Folder destFolder) {
        this.destFolder = destFolder;
    }

    @Override
    public void handle(Event event) {
        JFileChooser fileToImportDialog = new JFileChooser();


        int openedFile = fileToImportDialog.showOpenDialog(null);
        if(openedFile == JFileChooser.APPROVE_OPTION) {
            toRead = fileToImportDialog.getSelectedFile();
        }
        Controllers.documentHandler.importDocument(toRead.getName(), toRead, destFolder);
    }
}
