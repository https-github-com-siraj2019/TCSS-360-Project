package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import main.java.model.Setting;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class SettingsHandler {

    private ObservableSet<Setting> settingList ;

    public SettingsHandler() {
        this.settingList = FXCollections.observableSet();
        addSetting(new Setting("Username", ""));
        addSetting(new Setting("Email", ""));
    }

    //https://www.tutorialspoint.com/java/util/observable_addobserver.htm
    public void addSetting(Setting setting) {
        settingList.add(setting);
    }

    public void updateSetting(Setting setting, String newValue) {
        for (Setting settingInSet: this.settingList
             ) {
            if (setting.equals(settingInSet)) {
                settingInSet.setValue(newValue);
            }
        }
    }

    // Maintains a list of all settings.
    public ObservableSet<Setting> getSettings() {
        return settingList;
    }

    //TODO: Create method to export settingsList to a JSON file.

    public void writeSettings(File fileToExport) {
        //Add Product to list
        JSONArray settingsToExport = new JSONArray();
        for (Setting s: this.settingList
             ) {
            JSONObject exportSetting = new JSONObject();
            exportSetting.put("ID",s.getID());
            exportSetting.put("Name",s.getName());
            exportSetting.put("Value",s.getValue());
            settingsToExport.add(exportSetting);
        }

        //Write JSON file
        try (FileWriter file = new FileWriter(fileToExport)) {

            file.write(settingsToExport.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //TODO: Create method to overwrite settingsList with settings from JSON file.
    public void readSettings(File inputJSONFile)
    {

        try (FileReader reader = new FileReader(inputJSONFile))
        {

            //Read JSON file
            JSONParser jsonParser = new JSONParser();
            Object obj =  jsonParser.parse(reader);

            JSONArray productList = (JSONArray) obj;
            System.out.println(productList);

            //Iterate over product array
            this.settingList.clear();
            productList.forEach( pro -> parseJSONSetting( (JSONObject) pro ) );


        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    private void parseJSONSetting(JSONObject product)
    {
        //Get product object within list
        JSONObject productObject = (JSONObject) product.get("product");

        //Get product id name
        String id = (String) productObject.get("ID");
        System.out.println(id);

        //Get product name
        String Name = (String) productObject.get("name");
        System.out.println(Name);

        //Get product value name
        String value = (String) productObject.get("value");
        System.out.println(value);

//        //Get product description name
//        String desc = (String) productObject.get("description");
//        System.out.println(desc);

        //Get product export name
        String export = (String) productObject.get("exportable");
        boolean exportBool = export.compareToIgnoreCase("true") == 0;
        System.out.println(export);

        try {
            this.addSetting(new Setting(UUID.fromString(id), Name, value, exportBool));
        } catch (IllegalArgumentException e) {
            this.addSetting(new Setting(Name, value, exportBool));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
