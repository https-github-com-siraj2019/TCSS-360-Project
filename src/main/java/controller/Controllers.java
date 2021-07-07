package main.java.controller;

import main.db.DataSourceHandler;

/**
 * Contains all Handler objects.
 */
public class Controllers {

    public static DataSourceHandler dataSourceHandler;
    public static SettingsHandler settingsHandler;
    public static TagHandler tagHandler;
    public static DocumentHandler documentHandler;
    public static ViewHandler viewHandler;


    /**
     * Initializes new handlers
     */
    public Controllers() {

        dataSourceHandler = new DataSourceHandler();
        settingsHandler = new SettingsHandler();
        tagHandler = new TagHandler();
        documentHandler = new DocumentHandler();
        viewHandler = new ViewHandler();

    }
}
