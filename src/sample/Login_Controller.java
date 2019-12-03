package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Login_Controller {

  @FXML
  public Button btn_employeeLogin;

  @FXML
  public Button btn_customerLogin;


  @FXML
  public void customerLogin (MouseEvent event) throws IOException {
    System.out.println("Customer Login Button Clicked.");
    Parent root2 = FXMLLoader.load(getClass().getResource("Customer.fxml"));
    Scene employeeView = new Scene(root2,700, 500);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(employeeView);
    employeeView.getStylesheets().add
            (getClass().getResource("Customer.css").toExternalForm());
    appStage.show();
  }

  @FXML
  public void employeeLogin (MouseEvent event) throws IOException {

    System.out.println("Employee Login Button Clicked.");
    Parent root3 = FXMLLoader.load(getClass().getResource("Employee.fxml"));
    Scene employeeView = new Scene(root3,700, 500);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(employeeView);
    appStage.setScene(employeeView);
    employeeView.getStylesheets().add
            (getClass().getResource("Employee.css").toExternalForm());
    appStage.show();
  }

  public static Connection conn;
  public static Statement stmt;

  public void initialize() {
      final String JDBC_DRIVER = "org.h2.Driver";
      final String DB_URL = "jdbc:h2:./res/AnimalShelterDB";
      final String USER = "";
      final String PASS;

      System.out.println("Attempting to connect to database");
      try {
        Class.forName(JDBC_DRIVER);
        Properties prop = new Properties();
        prop.load(new FileInputStream("res/properties"));
        PASS = prop.getProperty("password");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
        System.out.println("Successfully connected to Animal database!");
      } catch (Exception e) {
        e.printStackTrace();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.show();
      }

  }
}

