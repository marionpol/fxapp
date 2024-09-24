package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {
    private static String title;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title: ");
        title = scanner.nextLine();

        scanner.close();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle(title);

        Label titleLabel = new Label(title);

        VBox layout = new VBox(20);
        layout.getChildren().add(titleLabel);

        Scene scene = new Scene(layout, 300, 100);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}