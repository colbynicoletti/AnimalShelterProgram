package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Animal Program");

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        //Calling CSS file
        scene.getStylesheets().add(getClass().getResource("Aesthetics .css").toExternalForm());
        primaryStage.show();
        //
//        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(60));
    }
}