package main.java.controller;

/**
 * Contains all Handler objects.
 */
public class Controllers {

    public static SettingsHandler settingsHandler;
    public static DocumentHandler documentHandler;
    public static ViewHandler viewHandler;
    public static TagHandler tagHandler;

    /**
     * Initializes new handlers
     */
    public Controllers() {

        settingsHandler = new SettingsHandler();
        documentHandler = new DocumentHandler();
        viewHandler = new ViewHandler();
        tagHandler = new TagHandler();
    }
}
