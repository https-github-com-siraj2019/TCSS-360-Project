package main.java.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * The root UI element of the main application.
 */
public class MainUI extends Application {

    private Stage primaryStage;
    private BorderPane rootBorder;
    private String programTitle;
    private MainMenuUI mainMenu;

    /**
     * Launches UI
     * Sample taken from:
     * https://www.swtestacademy.com/getting-started-with-javafx/
     *
     */
    public void main(String[] args) {
        launch(args);
    }

    /**
     * Sets initial UI parameters not contained within the Settings controller.
     */
    private void initUIVariables() {
        // Sets program title
        this.programTitle = "Document Management";
    }

    @Override
    public void start(Stage primaryStage) {
        // initializes UI parameters
        initUIVariables();

        // Creates a new UI stage
        this.primaryStage = primaryStage;

        //Creates and initializes the main program window.
        initPrimaryStage();

        //Creates a frame in the primary stage
        initRootLayout();

        // Main doc view pane
        initDocumentView();


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

        // Initialize menus
        new HelpMenuUI(helpMenu);
        new FileMenuUI(fileMenu);



        primaryStage.show();
    }


    private void initPrimaryStage() {
        //Sets program title
        primaryStage.setTitle(programTitle);
    }

    /**
     * Loads root layout from rootBorder.fxml file.
     */
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

    /**
     * Adds main menu to root border top.
     */
    private void initMainMenu() {
        MainMenuUI menu = new MainMenuUI();
        menu.start(rootBorder);
        this.mainMenu = menu;
    }

    /**
     * Adds document view table to root border center.
     */
    private void initDocumentView() {
        ViewUI viewUI = new ViewUI();
        viewUI.start(this.rootBorder);
    }
}

