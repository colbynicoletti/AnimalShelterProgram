package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class Employee_Controller {

  @FXML
  private ComboBox<Species> cb_species;

  @FXML
  private ComboBox<?> cb_breed;

  @FXML
  private Button btn_checkIn;

  @FXML
  private ComboBox<?> cb_event;

  @FXML
  private ComboBox<?> cb_date;

  public void initialize() {
    btn_checkIn.setOnAction(this::handleButtonAction);
    cb_species();
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

}
