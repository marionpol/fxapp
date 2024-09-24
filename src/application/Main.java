package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Main extends Application {

    @Override
    public void start(Stage window) {
        TextField textComponent = new TextField("This is a textfield");
        Button buttonComponent = new Button("This is a button");

        FlowPane componentGroup = new FlowPane();

        componentGroup.getChildren().add(textComponent);
        componentGroup.getChildren().add(buttonComponent);


        Scene view = new Scene(componentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}