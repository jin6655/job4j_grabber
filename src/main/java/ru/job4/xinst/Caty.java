package ru.job4.xinst;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.lang.ref.SoftReference;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.TimeUnit;

//public class Caty extends Application {
public class Caty {

    private final Map<String, String> mapCat = new HashMap<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        Map<String, SoftReference<String>> map = new HashMap<>();
        map.put("1", new SoftReference<>("11"));
        String rsl = map.getOrDefault("2", new SoftReference<>(null)).get();
        System.out.println(rsl);
        //launch(args);
    }
/*
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new StackPane(new Text("Hello")), 300, 300));
        primaryStage.setTitle("Title");
        primaryStage.setMaxWidth(600);
        primaryStage.setMaxHeight(600);
        primaryStage.show();
    }

 */

}