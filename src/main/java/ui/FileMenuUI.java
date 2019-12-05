package main.java.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import main.java.actions.ExportFileDialogAction;
import main.java.actions.ImportFileDialogAction;

/**
 * Contains file menu UI elements.
 * Elements are initialized in the order in which they appear in the menu.
 */
public class FileMenuUI {
    public FileMenuUI(Menu rootMenu) {
        // Import Button
        MenuItem importButton = new MenuItem("Import File...");
        importButton.addEventHandler(ActionEvent.ACTION, new ImportFileDialogAction(null));
        rootMenu.getItems().add(importButton);

        // Export button
        MenuItem exportButton = new MenuItem("Export File...");
        exportButton.addEventHandler(ActionEvent.ACTION, new ExportFileDialogAction());
        rootMenu.getItems().add(exportButton);

        // Settings Button
        SettingsUI settings = new SettingsUI();
        settings.initMenu(rootMenu);
    }
}
