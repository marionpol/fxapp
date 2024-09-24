package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage window) {
        TextField leftText = new TextField();

        Button button = new Button("Update");

        Label textComponent = new Label("");

        button.setOnAction((event) -> {
            textComponent.setText(leftText.getText());
        });

        VBox componentGroup = new VBox();
        componentGroup.getChildren().addAll(leftText, button, textComponent);

        Scene scene = new Scene(componentGroup);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}