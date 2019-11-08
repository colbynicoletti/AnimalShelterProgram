package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Employee_Controller extends Main {

    @FXML
    private ComboBox<Species> cb_species;

    @FXML
    private ComboBox<Breeds> cb_breed;

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
        cb_species();
        cb_breed();
    }


    private void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        Species species = cb_species.getValue();
        Breeds breeds = cb_breed.getValue();
        String petName = tf_petName.getText();
        String petID = tf_animalID.getText();

        try {
            String productQuery = "INSERT INTO ANIMAL(SPECIES,BREED,PET_NAME,ANIMAL_ID) VALUES (?,?,?,?)";
            PreparedStatement addAnimal = conn.prepareStatement(productQuery);
            addAnimal.setString(1, species.toString());
            addAnimal.setString(2, breeds.toString());
            addAnimal.setString(3, petName);
            addAnimal.setInt(4, Integer.parseInt(petID));
            addAnimal.executeUpdate();

            tf_petName.clear();
            tf_animalID.clear();
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
