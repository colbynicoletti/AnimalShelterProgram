package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Employee_Controller {

    @FXML
    private ChoiceBox<Species> cb_species;

    @FXML
    private ChoiceBox<Breeds> cb_breed;

    @FXML
    TextField tf_petName;

    @FXML
    TextField tf_animalID;

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

    Species species;
    Breeds breed;
    String petName;
    String animalID;


    public void initialize() {
        btn_checkIn.setOnAction(this::handleButtonAction);
        cb_species();
        cb_breed();
        saveUserInput();
    }

    public void saveUserInput() {
        species = cb_species.getValue();
        breed = cb_breed.getValue();
        petName = tf_petName.getText();
        animalID = tf_animalID.getText();
    }

    private void handleButtonAction(javafx.event.ActionEvent actionEvent) {

        try {
            String productQuery = "INSERT INTO ANIMALS(SPECIES, BREED, PETNAME, ANIMALID)" + "VALUES (?,?,?,?)";
            PreparedStatement addAnimal = Login_Controller.conn.prepareStatement(productQuery);
            addAnimal.setString(1, String.valueOf(species));
            addAnimal.setString(2, String.valueOf(breed));
            addAnimal.setString(3, petName);
            addAnimal.setString(4, animalID);
            addAnimal.executeUpdate();
            tf_petName.clear();
            tf_animalID.clear();
            cb_breed.getSelectionModel().clearSelection();
            cb_species.getSelectionModel().clearSelection();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void cb_species() {
        cb_species.getItems().addAll(Species.Dogs);
        cb_species.getItems().addAll(Species.Cats);
        cb_species.getItems().addAll(Species.Rabbits);
        cb_species.getItems().addAll(Species.Monkeys);
        cb_species.getItems().addAll(Species.Ferrets);

    }

    private void cb_breed() {
        cb_breed.getItems().addAll(Breeds.Husky);
        cb_breed.getItems().addAll(Breeds.Black_Sable);
        cb_breed.getItems().addAll(Breeds.Capuchin);
        cb_breed.getItems().addAll(Breeds.French_Lop);
        cb_breed.getItems().addAll(Breeds.Munchkin);
    }

    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }

}
