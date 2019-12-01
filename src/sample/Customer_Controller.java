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

import java.io.IOException;

public class Customer_Controller {

    @FXML
    private ComboBox<String> speciesCombo;

    @FXML
    private ComboBox<String> breedCombo;

    @FXML
    private ComboBox<String> idCombo;
    @FXML
    private ListView<AnimalType> displayAnimal;
    @FXML
    private ComboBox<String> cb_dateTime;
    @FXML
    private Button btn_donate;
    @FXML
    private Button goBack1;
    @FXML
    private Button goBack2;
    @FXML
    private TextField numberField;
    @FXML
    private TextField nameField;
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


    public void initialize() {
        //initializeChoiceBox();
        //initializeChoiceBox1();
        initializeChoiceBox2();
        //initializeComboBox3();
        //speciesChoice();
        speciesCombo.setValue("Species");
        speciesCombo.setItems(animalSpecies);
        breedCombo.setValue("Select Species");


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
//    private void initializeChoiceBox() {
//
//        speciesCombo.getItems().addAll(Species.values());
//        speciesCombo.getSelectionModel().selectFirst();
//
//    }
//
//    private void initializeChoiceBox1() {
//        breedCombo.getItems().addAll(Breeds.values());
//        breedCombo.getSelectionModel().selectFirst();
//    }

    private void initializeChoiceBox2() {

        ObservableList<String> data = idCombo.getItems(); // Get item value input

        //data.clear();
        data.add("DO12");
        data.add("DO34");
        data.add("CA12");
        data.add("CA34");
        data.add("A12");
//
        idCombo.getSelectionModel().selectFirst();
    }


    @FXML
    void adoptButton(ActionEvent event) {
        System.out.println("Adopted");
        // selectedAnimal.appendText(String.valueOf(displayAnimal.getSelectionModel().getSelectedItem()));

    }


    @FXML
    void submitAppointment(ActionEvent event) {
        System.out.println("Thank you for your interest in Adopting");
        System.out.println("Below is your appointment information");
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
        System.out.println(displayAnimal.getSelectionModel().getSelectedItem());
//        System.out.println(dateAndTime.getValue());


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

