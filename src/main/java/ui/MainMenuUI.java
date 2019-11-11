package main.java.ui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.LinkedList;
import java.util.Objects;

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

    public void addMenu(Menu menu){
        this.menubar.getMenus().add(Objects.requireNonNull(menu));
    }

    public void addMenuItem(Menu menu, MenuItem item) {
        try {
            menu.getItems().add(Objects.requireNonNull(item));
        } catch (Exception e) {
            //TODO: Create error logging
        }
    }
}
