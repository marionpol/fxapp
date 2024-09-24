package application;

import javafx.application.Application;

public class app {

    public static void main(String[] args) {
        Application.launch(Main.class,
                "--organization=Once upon a time",
                "--course=Title");
    }
}