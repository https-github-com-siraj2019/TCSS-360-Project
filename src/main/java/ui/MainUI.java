package main.java.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainUI extends Application {

    private Stage primaryStage;
    private BorderPane rootBorder;
    private String programTitle;
    private MainMenuUI mainMenu;

    /**
     * Sample taken from:
     * https://www.swtestacademy.com/getting-started-with-javafx/
     *
     *
     * Args expected:
     * 0    Program Title
     * 1
     */
    public void main(String[] args) {

        launch(args);
    }

    private void initUIVariables() {
        // Sets program title
        this.programTitle = "Document Management";
    }

    @Override
    public void start(Stage primaryStage) {
        initUIVariables();

        this.primaryStage = primaryStage;

        //Creates and initializes the main program window.
        initPrimaryStage();

        //Creates a frame in the primary stage
        initRootLayout();


        // Main Menu.
        // Must be before any element that adds a menu item.
        initMainMenu();

        ArrayList<Menu> rootMenus = new ArrayList<Menu>();

        // File Menu
        Menu fileMenu = new Menu("File");
        rootMenus.add(fileMenu);

        // Help Menu
        Menu helpMenu = new Menu("Help");
        rootMenus.add(helpMenu);


        // Adds all root menus to main menu bar.
        for (Menu menu: rootMenus
             ) {
            this.mainMenu.addMenu(menu);
        }


        // Creates About Popup.
        AboutUI about = new AboutUI();
        about.initMenu(helpMenu);

        // Creates Settings Popup.
        SettingsUI settings = new SettingsUI();
        settings.initMenu(fileMenu);


        primaryStage.show();
    }


    private void initPrimaryStage() {
        //Sets program title
        primaryStage.setTitle(programTitle);
    }


    private void initRootLayout() {
        try {
            //Load xml layout into rootLayout pane

            FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(getClass().getResource("/resources/fxml/MainScreen.fxml"));
            loader.setLocation(getClass().getResource("/resources/fxml/rootBorder.fxml"));
            rootBorder = loader.load();
            //rootBorder = new BorderPane();


            // Creates a scene from the root layout pane and add it to the main scene
            Scene scene = new Scene(rootBorder);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    private void initRootBorder() {
        this.rootBorder.setPrefHeight(600);
        this.rootBorder.setPrefWidth(800);
        this.rootBorder.setMinHeight(600);
        this.rootBorder.setMinWidth(800);
    }

    private void initMainMenu() {
        MainMenuUI menu = new MainMenuUI();
        menu.start(rootBorder);
        this.mainMenu = menu;
    }

    private void initMenu(Menu rootMenu) {

    }
}

