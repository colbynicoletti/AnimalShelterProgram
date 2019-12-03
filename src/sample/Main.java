
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
                (Main.class.getResource("Login.css").toExternalForm());

        primaryStage.show();

    }



}