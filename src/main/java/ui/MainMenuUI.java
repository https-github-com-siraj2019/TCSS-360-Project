package main.java.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Objects;

/**
 * Contains the main menu bar items as well as methods to add menus to the menu bar.
 */
public class MainMenuUI {

    MenuBar menubar;
    private LinkedList<MenuItem> menuItemList;

    public void start(BorderPane primaryPane) {

        menubar = new MenuBar();
        menubar.prefWidthProperty().bind(primaryPane.widthProperty());
        VBox box = new VBox(menubar);
        box.prefWidthProperty().bind(primaryPane.widthProperty());
        primaryPane.setTop(box);
    }

    void addMenu(Menu menu){
        this.menubar.getMenus().add(Objects.requireNonNull(menu));
    }

}
