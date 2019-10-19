package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class Controller {

  @FXML private Button adopt_button;

  public void initialize(){
  adopt_button.setOnAction(this::handleButtonAction);
  }

  @FXML private void handleButtonAction(ActionEvent event){
    System.out.println("Pet Adopted.");
  }
}


