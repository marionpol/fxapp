package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();
        layout.setTop(new Label("North"));
        layout.setRight(new Label("East"));
        layout.setBottom(new Label("South"));

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}