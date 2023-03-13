package ru.job4.xinst;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Caty extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new StackPane(new Text("Hello")), 300, 300));
        primaryStage.setTitle("Title");
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.show();
    }

}