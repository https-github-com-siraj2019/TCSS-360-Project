package main.java.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import main.java.controller.Controllers;
import main.java.model.Setting;

import java.util.UUID;

/**
 * Represents a setting in the Setting UI as well as
 * methods to update setting values.
 */
public class SettingsUIElement {

    private UUID ID;
    private String name;
    private String value;
    private String description;
    private boolean readonly;
    private Setting setting;
    private GridPane pane;


    // https://stackoverflow.com/questions/43877557/the-correct-way-to-handle-and-extend-node-in-javafx
    public SettingsUIElement(String name, String value) {
        this.ID = UUID.randomUUID();
        this.name = name;
        this.value = value;
        initUIElement();
    }

    public SettingsUIElement(Setting setting) {
        this.ID = UUID.randomUUID();
        this.name = setting.getName();
        this.value = setting.getValue().toString();
        this.setting = setting;
        initUIElement();
    }

    private void initUIElement() {

        Label labelName = new Label(name);
        TextField labelValue = new TextField(value);

        GridPane grid = new GridPane();

        // Add format to columns
        //grid.setAlignment(Pos.CENTER);
        grid.setHgap(5);
        ColumnConstraints col0 = new ColumnConstraints();
        ColumnConstraints col1 = new ColumnConstraints();
        col0.setPrefWidth(100);
        col1.setFillWidth(true);
        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().addAll(col0,col1);



        // Add UI elements to grid
        GridPane.setConstraints(labelName,0,0);
        GridPane.setConstraints(labelValue,1,0);
        grid.getChildren().addAll(labelName, labelValue);


        this.pane = grid;

        /*
        labelValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(!(t1.equals(setting.getValue()))){
                    updateSetting(setting, t1);
                } else {
                    System.out.println("Already matched text:" + t1 + "=" + setting.getValue());
                }
            }
        });*/

        labelValue.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                String tempValue = labelValue.getText();

                if(aBoolean) {
                    System.out.println("unfocused");
                    if(!(tempValue.equals(setting.getValue()))){
                        updateSetting(setting, tempValue);
                    } else {
                        System.out.println("Already matched:" + tempValue + "=" + setting.getValue());
                    }
                }
            }
        });

        this.pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pane.setStyle("-fx-background-color:#dae7f3;");
            }
        }
        );

        this.pane.setOnMouseExited(new EventHandler<MouseEvent>() {
                                       @Override
                                       public void handle(MouseEvent mouseEvent) {
                                           pane.setStyle("-fx-background-color:transparent;");
                                       }
                                   }
        );
    }



    private void updateSetting(Setting setting, String newValue) {
        Controllers.settingsHandler.updateSetting(setting,newValue);
        Controllers.settingsHandler.getSettings().forEach(setting1 -> System.out.println(setting1.toString()));
    }

    public GridPane asPane() {
        return this.pane;
    }

    @Override
    public String toString() {
        return "Setting: "+this.name + "=" + this.value;
    }
}
