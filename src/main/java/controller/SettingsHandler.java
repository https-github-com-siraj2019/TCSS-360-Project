package main.java.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import main.java.model.Setting;
import main.java.model.Tag;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 *  Contains a master list of settings as well as
 *  methods to add, remove, import, and export settings.
 */
public class SettingsHandler {

    private ObservableSet<Setting> settingList ;

    /**
     * Creates new list of settings.
     * Currently creates default settings with empty values:
     * "Username"
     * "Email"
     * "Columns"
     */
    public SettingsHandler() {
        this.settingList = FXCollections.observableSet();
        addSetting(new Setting<String>("Username", ""));
        addSetting(new Setting<String>("Email", ""));
        addSetting(new Setting<ObservableSet<Tag>>("Columns", FXCollections.observableSet()) {});
    }

    private void initSettings() {
        ObservableSet<Tag> tagSetting = (ObservableSet<Tag>) this.getSetting("Columns");
        for (Tag t: Controllers.tagHandler.getTags()
             ) {
            tagSetting.add(t);
        }
    }


    /**
     * REturns the master settings list.
     * @return
     */
    public ObservableSet<Setting> getSettings() {
        return settingList;
    }

    /**
     * Gets a particular setting from the lsit by setting name
     * @param name Name to search for
     * @return Setting if a match exists, null otherwise.
     */
    public Setting getSetting(String name) {
        for(Setting s : this.settingList) {
            if (s.compareByName(name) == 0) {
                return s;
            }
        }
        return null;
    }

    /**
     * Adds a setting to the master list
     * @param setting Setting to add.
     */
    public void addSetting(Setting setting) {
        settingList.add(setting);
    }

    /**
     * Writes a new value to a setting if the new value matches the existing setting's data type.
     * @param setting Setting to update
     * @param newValue Value to update to
     */
    public void updateSetting(Setting setting, Object newValue) {
        for (Setting settingInSet: this.settingList
             ) {
            if (setting.equals(settingInSet)) {
                try {
                    settingInSet.setValue(setting.getValue().getClass().cast(newValue));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }


    /**
     * Creates a JSON file from settings in setting list.
     * @param fileToExport
     */
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

    /**
     * Reads all settings from a JSON file and saves the settings to the master list.
     * @param inputJSONFile
     */
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
            productList.forEach( pro -> {
                try {
                    parseJSONSetting( (JSONObject) pro );
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });


        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a setting from a JSON object line.
     * @param product
     * @throws ClassNotFoundException
     */
    private void parseJSONSetting(JSONObject product) throws ClassNotFoundException {
        //Get product object within list
        JSONObject productObject = (JSONObject) product.get("product");

        //Get product id name
        String id = (String) productObject.get("ID");
        System.out.println(id);

        //Get product name
        String Name = (String) productObject.get("name");
        System.out.println(Name);

        //Get product value name
        Object value = (String) productObject.get("value");
        System.out.println(value);

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
