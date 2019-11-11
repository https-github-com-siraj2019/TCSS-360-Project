package main.java.controller;


import main.java.ui.MainUI;

public class Main {

    private String programTitle = "Document Management Software";


    public static void main(String[] args) {
        // Creates controller objects to be shared among different classes.
        Controllers controllers = new Controllers();

        // Creates a new UI and starts the UI main method.
        MainUI UI = new MainUI();
        UI.main(args);
    }
}
