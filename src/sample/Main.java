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

    public static class databaseConnection {

        private static final String jdbc_driver = "org.h2.Driver";
        private static final String DB_url = "jdbc:h2:./res/AnimalShelter";

        private static final String user = "";
        private static final String pass = "";
        private Connection conn = null;
        private Statement stmt = null;

        /**
         * Method that starts the connection between the controller and the database.
         */
        public void connDatabase() {
            try {
                Class.forName(jdbc_driver);
                conn = DriverManager.getConnection(DB_url, user, pass);
                stmt = conn.createStatement();
                System.out.println("Database Connection Established.");

                stmt.close();
                conn.close();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public static void main(String[] args) {
            launch(args);

        }
    }
}