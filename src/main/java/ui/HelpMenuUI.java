package main.java.ui;

import javafx.scene.control.Menu;

/**
 * Contains help menu UI elements.
 * Elements are initialized in the order in which they appear in the menu.
 */
public class HelpMenuUI {
    public HelpMenuUI(Menu rootMenu) {

        // Creates About Popup.
        AboutUI about = new AboutUI();
        about.initMenu(rootMenu);
    }
}
