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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;

import java.io.IOException;

public class Customer_Controller extends Employee_Controller {

    @FXML
    private ComboBox<String> speciesCombo;

    @FXML
    private ComboBox<String> breedCombo;
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
    @FXML
    private TableColumn<?, ?> tbc_species;

    @FXML
    private TableColumn<?, ?> tbc_breed;

    @FXML
    private TableColumn<?, ?> tbv_petName;

    @FXML
    private TableColumn<?, ?> tbc_animalID;
    @FXML
    private TableView<AnimalType> tv_animalAdopt;

    private ObservableList<String> animalSpecies = FXCollections.observableArrayList("Dogs", "Cats", "Monkey", "Rabbit");
    private ObservableList<String> dogBreeds = FXCollections.observableArrayList("Husky", "Chihuahua", "Beagle", "Pug", "Boston Terrier", "GreyHound", "Pomeranian", "Maltese", "Poodle", "Mix");
    private ObservableList<String> catBreeds = FXCollections.observableArrayList("Persion Cat", "Russian Blue", "Bengal Cat", "British Shorthair", "Munchkin", "Siamese Cat", "Ragdoll", "Mix");
    private ObservableList<String> monkeyBreeds = FXCollections.observableArrayList("Capuchin", "Guenon", "Macaque", "Tamarin", "Marmosets", "Other");
    private ObservableList<String> rabbitBreeds = FXCollections.observableArrayList("Holland Lop", "Netherland Dwarf", "Flemish Giant", "Lionhead", "Rex", "Angora", "Other");


    private Connection conn;
    private Statement stmt;

    public void initialize() {
//        initializeChoiceBox2();
        populateAdoptTable();
        speciesCombo.setValue("Species");
        speciesCombo.setItems(animalSpecies);
        breedCombo.setValue("Breed");

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

    @FXML
    void adoptButton(ActionEvent event) throws SQLException {
        System.out.println("Adopted");
        AnimalType am = tv_animalAdopt.getSelectionModel().getSelectedItem();
        selectedAnimal.setText(am.toString());
    }

    @FXML
    void submitAppointment(ActionEvent event) throws SQLException {
        System.out.println("Thank you for your interest in Adopting");
        System.out.println("Below is your appointment information");
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
        System.out.println("Date and time: ");
        System.out.println(dateAndTime.getValue());
        System.out.println(timeField.getText());
        loadAdoption();
    }

    @FXML
    void loadAdoption() throws SQLException {
        AnimalType am = tv_animalAdopt.getSelectionModel().getSelectedItem();
        String name = nameField.getText();
        String animalID = am.getAnimalID();
        LocalDate date = dateAndTime.getValue();
        String time = timeField.getText();
        String phone = numberField.getText();
        String adoptionQuery = "INSERT INTO ADOPTION_TABLE (ANIMAL_ID, CUSTOMER_NAME, PHONE_NUMBER, DATE, TIME) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement adoptionDB = Login_Controller.conn.prepareStatement(adoptionQuery);
        adoptionDB.setString(1, animalID);
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

    void populateAdoptTable() {
        ObservableList<String> animalList = FXCollections.observableArrayList();

        tbc_species.setCellValueFactory(new PropertyValueFactory<>("species"));
        tbc_breed.setCellValueFactory(new PropertyValueFactory<>("breeds"));
        tbv_petName.setCellValueFactory(new PropertyValueFactory<>("petName"));
        tbc_animalID.setCellValueFactory(new PropertyValueFactory<>("animalID"));
        tv_animalAdopt.setItems(animalObservableList);
        try {
            String sql = "SELECT * FROM ANIMALS";
            ResultSet rs = Login_Controller.stmt.executeQuery(sql);

            while (rs.next()) {
                // these lines correspond to the database table columns
                animalObservableList.add(
                        new Widget(Species.valueOf(rs.getString("species")), rs.getString("breed"), rs.getString("petName"), rs.getString("animalID")));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
//


    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}

