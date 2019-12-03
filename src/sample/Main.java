//package sample;
//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.geometry.Insets;
//import javafx.scene.Group;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.layout.GridPane;
//import javafx.scene.text.Text;
//import javafx.stage.Stage;
//
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//       // primaryStage.setTitle("Animal Program");
//        Text title = new Text("Animal Program");
//        title.setId("welcome-text");
//        Scene scene = new Scene(root, 600, 400);
//        primaryStage.setScene(scene);
//        //Calling CSS file
//        scene.getStylesheets().add(Main.class.getResource("Aesthetics.css").toExternalForm());
//        primaryStage.show();
//        //
//
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Animal Program");

        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
       // primaryStage.setTitle("Animal Program");
        Text title = new Text("Animal Program");
        title.setId("welcome-text");
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        scene.getStylesheets().add
                (Main.class.getResource("Aesthetics.css").toExternalForm());

        primaryStage.show();

    }



}