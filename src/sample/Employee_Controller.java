package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Employee_Controller extends Main {

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


    private Connection conn = null;

    public void initialize() {
        btn_checkIn.setOnAction(this::handleButtonAction);
        setCb_breed();
        setCb_species();
    }


    private void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        Species species = cb_species.getSelectionModel().getSelectedItem();
        Breeds breeds = cb_breed.getSelectionModel().getSelectedItem();
        String petName = tf_petName.getText();
        String animalID = tf_animalID.getText();

        try {
            String productQuery = "INSERT INTO ANIMALS(SPECIES,BREED,PETNAME,ANIMALID) VALUES (?,?,?,?)";
            PreparedStatement addAnimal = Login_Controller.conn.prepareStatement(productQuery);
            addAnimal.setString(1, species.toString());
            addAnimal.setString(2, breeds.toString());
            addAnimal.setString(3, petName);
            addAnimal.setString(4, animalID);
            addAnimal.executeUpdate();
            System.out.println("Data inserted into database. Close program and refresh database");
            tf_petName.clear();
            tf_animalID.clear();
            cb_species.getSelectionModel().clearSelection();
            cb_breed.getSelectionModel().clearSelection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void setCb_species() {
        cb_species.getItems().addAll(Species.Dogs);
        cb_species.getItems().addAll(Species.Cats);
        cb_species.getItems().addAll(Species.Rabbits);
        cb_species.getItems().addAll(Species.Monkeys);
        cb_species.getItems().addAll(Species.Ferrets);

    }

    private void setCb_breed() {
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
