package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Arrays;


public class Main extends Application {

    @Override
    public void start(Stage window) {
        BorderPane layout = new BorderPane();

        TextArea leftText = new TextArea();

        HBox texts = new HBox();
        texts.setSpacing(10);

        Label characterCountLabel = new Label("Letters: 0");
        texts.getChildren().add(characterCountLabel);

        Label wordCountLabel = new Label("Words: 0");
        texts.getChildren().add(wordCountLabel);

        Label longestWordLabel = new Label("Longest Word Is: ");
        texts.getChildren().add(longestWordLabel);

        layout.setBottom(texts);
        layout.setCenter(leftText);

        Scene view = new Scene(layout);

        window.setScene(view);
        window.show();

        leftText.textProperty().addListener((change, oldValue, newValue) -> {
            int characters = newValue.length();
            String[] parts = newValue.split(" ");
            int words = parts.length;
            String longest = Arrays.stream(parts)
                    .sorted((s1, s2) -> s2.length() - s1.length())
                    .findFirst()
                    .get();

            characterCountLabel.setText(String.valueOf("Letters: " + characters));
            wordCountLabel.setText(String.valueOf("Words: " + words));
            longestWordLabel.setText("Longest Word Is: " + longest);
        });
    }




    public static void main(String[] args) {
        launch(Main.class);
    }
}