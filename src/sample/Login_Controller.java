package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Login_Controller {

  @FXML
  public Button btn_employeeLogin;

  @FXML
  public Button btn_customerLogin;

  @FXML
  public void customerLogin(MouseEvent event) throws IOException {
    System.out.println("Customer Login Button Clicked.");
    Parent root2 = FXMLLoader.load(getClass().getResource("Customer.fxml"));
    Scene employeeView = new Scene(root2);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(employeeView);
    appStage.show();
  }

  @FXML
  public void employeeLogin(MouseEvent event) throws IOException {
    System.out.println("Employee Login Button Clicked.");
    Parent root3 = FXMLLoader.load(getClass().getResource("Employee.fxml"));
    Scene employeeView = new Scene(root3);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(employeeView);
    appStage.show();
    }
  }


