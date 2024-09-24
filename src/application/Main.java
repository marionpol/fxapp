package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

    @Override
    public void start(Stage window) {

        Label firstView = new Label("First View");
        BorderPane layout = new BorderPane();

        Button first = new Button("To the second view");
        layout.setTop(firstView);
        layout.setCenter(first);

        VBox secondVbox = new VBox();
        Button second = new Button("To the third view");
        Label secondView = new Label("Second View");
        secondVbox.getChildren().add(second);
        secondVbox.getChildren().add(secondView);

        GridPane grid = new GridPane();
        Label thirdView = new Label("Third View");
        grid.add(thirdView, 0, 0);
        Button third = new Button("To the first view");
        grid.add(third, 1, 1);


        Scene firstV = new Scene(layout);
        Scene secondV = new Scene(secondVbox);
        Scene thirdV = new Scene(grid);

        first.setOnAction((event) -> {
            window.setScene(secondV);
        });

        second.setOnAction((event) -> {
            window.setScene(thirdV);
        });

        third.setOnAction((event) -> {
            window.setScene(firstV);
        });



        window.setScene(firstV);
        window.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}