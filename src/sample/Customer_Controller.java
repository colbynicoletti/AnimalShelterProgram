package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

import java.io.IOException;

public class Customer_Controller {

    @FXML
    private ComboBox<String> speciesCombo;

    @FXML
    private ComboBox<String> breedCombo;

    @FXML
    private ComboBox<String> idCombo;
    @FXML
    private ListView<String> displayAnimal;
    @FXML
    private ComboBox<String> cb_dateTime;
    @FXML
    private ComboBox<String> genderCombo;

    @FXML
    private ComboBox<String> spaNeuCombo;

    @FXML
    private ComboBox<String> ageCombo;

    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField timeField;
    @FXML
    private TextArea selectedAnimal;

    @FXML
    private TextArea donationTxtArea;
    @FXML
    private TextField amountField;
    @FXML
    private DatePicker dateAndTime;

    private ObservableList<String> animalSpecies = FXCollections.observableArrayList("Dogs", "Cats", "Monkey", "Rabbit");
    private ObservableList<String> dogBreeds = FXCollections.observableArrayList("Husky", "Chihuahua", "Beagle", "Pug", "Boston Terrier", "GreyHound", "Pomeranian", "Maltese", "Poodle", "Other");
    private ObservableList<String> catBreeds = FXCollections.observableArrayList("Persion Cat", "Russian Blue", "Bengal Cat", "British Shorthair", "Munchkin", "Siamese Cat", "Ragdoll", "Other");
    private ObservableList<String> monkeyBreeds = FXCollections.observableArrayList("Capuchin", "Guenon", "Macaque", "Tamarin", "Marmosets", "Other");
    private ObservableList<String> rabbitBreeds = FXCollections.observableArrayList("Holland Lop", "Netherland Dwarf", "Flemish Giant", "Lionhead", "Rex", "Angora", "Other");
    private ObservableList<String> dataAge = FXCollections.observableArrayList("Any age", "Adult", "Senior", "Young Adult");
    private ObservableList<String> dataGender = FXCollections.observableArrayList("Any Gender", "Female", "Male");
    private ObservableList<String> dataSorN = FXCollections.observableArrayList("Spayed", "Neutered");

    private Connection conn;
    private Statement stmt;

    public void initialize() {
        initializeChoiceBox2();
        speciesCombo.setValue("Species");
        speciesCombo.setItems(animalSpecies);
        breedCombo.setValue("Breed");
        ageCombo.setItems(dataAge);
        genderCombo.setItems(dataGender);
        spaNeuCombo.setItems(dataSorN);
    }

    @FXML
    void breedChoice(ActionEvent event) {
        breedCombo.setValue("Breeds");
        if (speciesCombo.getValue().equals("Dogs")) {
            breedCombo.setItems(dogBreeds);
            System.out.println("print dog");
        } else if (speciesCombo.getValue().equals("Cats")) {
            breedCombo.setItems(catBreeds);
            System.out.println("Print cat");
        } else if (speciesCombo.getValue().equals("Monkey")) {
            breedCombo.setItems(monkeyBreeds);
            System.out.println("Print monkey");
        } else if (speciesCombo.getValue().equals("Rabbit")) {
            breedCombo.setItems(rabbitBreeds);
            System.out.println("print rabbit");
        } else {
            System.out.println("Error");
        }
    }

    private void initializeChoiceBox2() {

        ObservableList<String> data = idCombo.getItems(); // Get item value input

        //data.clear();
        data.add("CA12");
        data.add("CA34");
        data.add("DO12");
        data.add("DO34");
        data.add("MO12");
        data.add("MO34");
        data.add("RA12");
        data.add("RA34");
//
        idCombo.getSelectionModel().selectFirst();
    }


    @FXML
    void adoptButton(ActionEvent event) throws SQLException {

        displayAnimal.getItems().add(speciesCombo.getValue() + "\n" + breedCombo.getValue() + "\n" + idCombo.getValue() + "\n" + ageCombo.getValue() + "\n" + genderCombo.getValue() + "\n" + spaNeuCombo.getValue());

        System.out.println("Adopted");
        selectedAnimal.appendText(speciesCombo.getValue() + "\n" + breedCombo.getValue() + "\n" + idCombo.getValue() + "\n" + ageCombo.getValue() + "\n" + genderCombo.getValue() + "\n" + spaNeuCombo.getValue());

        //loadAnimal();
    }

//    @FXML
//    void loadAnimal() throws SQLException {
//        String breed = breedCombo.getValue();
//        String animalid = idCombo.getValue();
//        String specie = speciesCombo.getValue();
//        String animalQuery = "INSERT INTO ANIMALS (SPECIES, BREED, PETNAME, ANIMALID) VALUES(?, ?, ?, ?)";
//        PreparedStatement animalDB = conn.prepareStatement(animalQuery);
//        animalDB.setString(1, specie);
//        animalDB.setString(2, breed);
//        animalDB.setString(4, animalid);
//        animalDB.executeUpdate();
//    }


    @FXML
    void submitAppointment(ActionEvent event) throws SQLException {
        System.out.println("Thank you for your interest in Adopting");
        System.out.println("Below is your appointment information");
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
        System.out.println("Date and time: ");
        System.out.println(dateAndTime.getValue());
        System.out.println(timeField.getText());
//        System.out.println("Animal for adoption: ");
//        selectedAnimal.appendText(speciesCombo.getValue() + "\n" + breedCombo.getValue() + "\n" + idCombo.getValue() + "\n" + ageCombo.getValue() + "\n" + genderCombo.getValue() + "\n" + spaNeuCombo.getValue());
        loadAdoption();
    }
    @FXML
    void loadAdoption() throws SQLException {

        String name = nameField.getText();
        String animalid = idCombo.getValue();
        LocalDate date = dateAndTime.getValue();
        String time = timeField.getText();
        String phone = numberField.getText();
        String adoptionQuery = "INSERT INTO ADOPTION_TABLE (ANIMAL_ID, CUSTOMER_NAME, PHONE_NUMBER, DATE, TIME) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement adoptionDB = conn.prepareStatement(adoptionQuery);
        adoptionDB.setString(1, animalid);
        adoptionDB.setString(2, name);
        adoptionDB.setString(3, phone);
        adoptionDB.setString(4, String.valueOf(date));
        adoptionDB.setString(5, time);
        adoptionDB.executeUpdate();
    }
    @FXML
    void donateBtn(ActionEvent event) {
        donationTxtArea.appendText("THANK YOU FOR YOUR DONATION OF: \n");
        donationTxtArea.appendText(amountField.getText() + "\n");
        donationTxtArea.appendText("Have a good day");

    }

    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}

