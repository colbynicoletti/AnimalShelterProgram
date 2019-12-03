package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.Properties;

public class Login_Controller {

  @FXML
  public Button btn_employeeLogin;

  @FXML
  public Button btn_customerLogin;

  @FXML
  private TextField tf_username;


  @FXML
  private PasswordField tf_employeepass;

  @FXML
  private TextArea ta_employeeAccount;

  @FXML
  private TextField tf_firstAndLastName;

  @FXML
  private Button btn_createAccount;

  @FXML
  private Button donateButton;

  private Employee myEmployee;

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
  public void employeeLogin (MouseEvent event) throws IOException, SQLException {
    String getUsername = tf_username.getText();
    String getPassword = tf_employeepass.getText();
    String sql = "SELECT * FROM EMPLOYEE";
    ResultSet rs = Login_Controller.stmt.executeQuery(sql);
    while(rs.next()){
      String username = rs.getString("USERNAME");
      String password = rs.getString("PASSWORD");
      if(username.equals(getUsername) && password.equals(getPassword)){
        System.out.println("Employee Login Button Clicked.");
        Parent root3 = FXMLLoader.load(getClass().getResource("Employee.fxml"));
        Scene employeeView = new Scene(root3,700, 500);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(employeeView);
        appStage.setScene(employeeView);
        employeeView.getStylesheets().add
                (getClass().getResource("Customer.css").toExternalForm());
        appStage.show();
      }
    }
//
  }

  public static Connection conn;
  public static Statement stmt;

  public void initialize(){
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
//      btn_createAccount.setOnMouseClicked(this::account);

  }
  public void account() throws SQLException {
    String name = tf_firstAndLastName.getText();
    String password = tf_employeepass.getText();
    myEmployee = new Employee(name, password);

    String productQuery = "INSERT INTO EMPLOYEE(NAME, USERNAME, PASSWORD, EMAIL) VALUES (?,?,?,?)";
    PreparedStatement addEmployee = Login_Controller.conn.prepareStatement(productQuery);
    addEmployee.setString(1, name);
    addEmployee.setString(2, myEmployee.username);
    addEmployee.setString(3, password);
    addEmployee.setString(4, myEmployee.email);
    addEmployee.executeUpdate();
    addEmployee.close();

    tf_firstAndLastName.clear();
    tf_employeepass.clear();
    ta_employeeAccount.appendText(myEmployee.toString() + "\n\n");
  }

  @FXML
  void createAccount(MouseEvent event) throws SQLException {
    account();
  }

  @FXML
  void donate(MouseEvent event) throws URISyntaxException, IOException {
    Desktop d = Desktop.getDesktop();
    d.browse(new URI("https://www.gofundme.com/f/y7wce7-animal-shelter?utm_source=customer&utm_medium=copy_link&utm_campaign=p_cf+share-flow-1"));
  }


  @FXML
  void firstAndLastName(MouseEvent event) {

  }
}

