package main.java.controller;


import main.java.ui.MainUI;

public class Main {

    /**
     * Initializes the controllers, then launches the UI
     * @param args
     */
    public static void main(String[] args) {
        // Creates controller objects to be shared among different classes.
        Controllers controllers = new Controllers();

        // Creates a new UI and starts the UI main method.
        MainUI UI = new MainUI();
        UI.main(args);
    }
}
