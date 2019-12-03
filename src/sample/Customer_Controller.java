package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.time.LocalDate;

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
    private TextArea selectedAnimal;
    @FXML
    private DatePicker dateAndTime;
    @FXML
    private TableColumn<?, ?> tbc_species;
    @FXML
    private TableColumn<?, ?> tbc_breed;
    @FXML
    private TableColumn<?, ?> tbv_petName;
    @FXML
    private ComboBox<String> timeCombo;
    @FXML
    private TableColumn<?, ?> tbc_animalID;
    @FXML
    private TableView<AnimalType> tv_animalAdopt;
    @FXML
    private Button donateButton;
    @FXML
    private TextArea appTxt;

    @FXML
    private Button okButton;

    /**
     * ObservableList
     */
    private ObservableList<String> animalSpecies = FXCollections.observableArrayList("Dogs", "Cats", "Monkey", "Rabbit");
    private ObservableList<String> dogBreeds = FXCollections.observableArrayList("Husky", "Chihuahua", "Beagle", "Pug", "Boston Terrier", "GreyHound", "Pomeranian", "Maltese", "Poodle", "Mixed","Other");
    private ObservableList<String> catBreeds = FXCollections.observableArrayList("Persion Cat", "Russian Blue", "Bengal Cat", "British Shorthair", "Munchkin", "Siamese Cat", "Ragdoll", "Mixed","Other");
    private ObservableList<String> monkeyBreeds = FXCollections.observableArrayList("Capuchin", "Guenon", "Macaque", "Tamarin", "Marmosets","Mixed", "Other");
    private ObservableList<String> rabbitBreeds = FXCollections.observableArrayList("Holland Lop", "Netherland Dwarf", "Flemish Giant", "Lionhead", "Rex", "Angora","Mixed", "Other");
    private ObservableList<String> appointmentTime = FXCollections.observableArrayList("8:00am", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm");


    private Connection conn;
    private Statement stmt;

    /**
     * Initialize method
     */
    public void initialize() {
//        initializeChoiceBox2();
        populateAdoptTable();
        speciesCombo.setValue("Species");
        speciesCombo.setItems(animalSpecies);
        breedCombo.setValue("Breed");
        timeCombo.setItems(appointmentTime);

    }

    /**
     * Method used to select breed
     * @param event
     */
    @FXML
    void breedChoice(ActionEvent event) {
        breedCombo.setValue("Select Breeds");
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

    /**
     * Adopt Button method
     * Gets selected animal for adoption
     * @param event
     * @throws SQLException
     */
    @FXML
    void adoptButton(ActionEvent event) throws SQLException {
        System.out.println("Adopted");
        AnimalType am = tv_animalAdopt.getSelectionModel().getSelectedItem();
        selectedAnimal.setText(am.toString());
        Alert b = new Alert(Alert.AlertType.INFORMATION);
        b.setAlertType(Alert.AlertType.CONFIRMATION);
        b.setContentText("ANIMAL ADOPTED: \n" + am + "\nPlease schedule an appointment");
        b.show();
    }

    /**
     * Submit appointment button method
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    void submitAppointment(ActionEvent event) throws SQLException, IOException {
        System.out.println("Thank you for your interest in Adopting");
        System.out.println("Below is your appointment information");
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
        System.out.println("Date and time: ");
        System.out.println(dateAndTime.getValue());
        System.out.println(timeCombo.getValue());
        loadAdoption();
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.CONFIRMATION);
        a.setContentText("THANK YOU FOR FOR ADOPTING" + "\nBelow is your information: " + "\n" + "Name: " + nameField.getText() + "\n" + "Number: " + numberField.getText() + "\n" + "Date: " + dateAndTime.getValue() + "\n" + "Time: " + timeCombo.getValue() );
        a.show();

    }

    /**
     * Donate button method
     * Takes you to a go fund me page to donate
     * @param event
     * @throws URISyntaxException
     * @throws IOException
     */
    @FXML
    void donate(MouseEvent event) throws URISyntaxException, IOException {
        Desktop d = Desktop.getDesktop();
        d.browse(new URI("https://www.gofundme.com/f/zxf5zc-animal-shelter?utm_source=customer&utm_medium=copy_link&utm_campaign=p_cf+share-flow-1"));
    }

    /**
     * Search button method
     * Allows you to search through species and breed
     * @param event
     */
    @FXML
    void search_Btn(ActionEvent event) {
        tv_animalAdopt.getItems().clear();
        String selectionSpecies = speciesCombo.getValue();
        String selectionBreed = breedCombo.getValue();
        tv_animalAdopt.setItems(animalObservableList);

            try {
                //Searching the data bas for dogs
                if(selectionBreed == "Other") {
                    String sql = "SELECT * FROM ANIMALS WHERE SPECIES='" + selectionSpecies+"'" ;
                    ResultSet rs = Login_Controller.stmt.executeQuery(sql);
                    while (rs.next()) {
                        // these lines correspond to the database table columns
                        animalObservableList.add(
                                new Widget(Species.valueOf(rs.getString("species")), rs.getString("breed"), rs.getString("petName"), rs.getString("animalID")));
                    }
                }

                    String sql = "SELECT * FROM ANIMALS WHERE SPECIES='" + selectionSpecies + "' AND BREED ='" + selectionBreed + "'";
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

    /**
     * load adopted animal into database
     * @throws SQLException
     */
    @FXML
    void loadAdoption() throws SQLException {
        AnimalType am = tv_animalAdopt.getSelectionModel().getSelectedItem();
        String name = nameField.getText();
        String animalID = am.getAnimalID();
        LocalDate date = dateAndTime.getValue();
        String time = timeCombo.getValue();
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

    /**
     * Method used to populate the adoption table
     */
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

    /**
     * Takes you back to the login page
     * @param event
     * @throws IOException
     */
    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}

