package main.java.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import main.java.model.Version;


public class AboutUI extends Application implements MenuItemInterface {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("About");
        TilePane mainPane = new TilePane();

        Label label = new Label("This is the About section.");

        Popup aboutPopup = new Popup();

        aboutPopup.getContent().add(label);

        label.setPrefHeight(200);
        label.setPrefWidth(150);

        Scene scene = new Scene(mainPane, 300, 200);

        // Add About popup to main scene.
        initAboutText(mainPane);

        // Launches main UI screen
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void initAboutText(Pane rootPane) {
        Text textBlock = new Text();
        try {
            String dataText = new String();
            for(String s : new Version().get()) {
                dataText += s;
                dataText += System.lineSeparator();
            }

            textBlock.setText(dataText);

            VBox box = new VBox();
            box.setPadding(new Insets(10,20,50,50));
            box.getChildren().add(textBlock);

            rootPane.getChildren().add(box);
        } catch (Exception e) {
            textBlock.setText(e.getMessage());
            rootPane.getChildren().add(textBlock);
        }
    }

    public void initMenu(Menu rootMenu) {
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(new Stage());
            }
        });
        rootMenu.getItems().add(aboutMenuItem);
    }
}
