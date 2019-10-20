package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.event.RowSorterEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Employee_Controller {

  @FXML
  private ComboBox<Species> cb_species;

  @FXML
  private ComboBox<Breeds> cb_breed;

  @FXML
  private Button btn_checkIn;
  @FXML
  private Button goBack1;
  @FXML
  private Button goBack2;

  @FXML
  private ComboBox<?> cb_event;

  @FXML
  private ComboBox<?> cb_date;

  public void initialize() {
    btn_checkIn.setOnAction(this::handleButtonAction);
    cb_species();
    cb_breed();
  }

  private void handleButtonAction(javafx.event.ActionEvent actionEvent) {
    System.out.println("Checked in");
  }

  private void cb_species(){
    cb_species.getItems().addAll(Species.Dogs);
    cb_species.getItems().addAll(Species.Cats);
    cb_species.getItems().addAll(Species.Rabbits);
    cb_species.getItems().addAll(Species.Monkeys);
    cb_species.getItems().addAll(Species.Ferrets);

  }
  private void cb_breed(){
    cb_breed.getItems().addAll(Breeds.Husky);
    cb_breed.getItems().addAll(Breeds.Black_Sable);
    cb_breed.getItems().addAll(Breeds.Capuchin);
    cb_breed.getItems().addAll(Breeds.French_Lop);
    cb_breed.getItems().addAll(Breeds.Munchkin);
  }

  public void previous(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
    Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene homePage = new Scene(newRoot);
    Stage window = new Stage();
    window.setScene(homePage);
    window.show();
  }
}
