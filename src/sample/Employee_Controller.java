package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class Employee_Controller extends Main {

    @FXML
    public TextField tf_breed;

    @FXML
    public Button btn_generateId;

    @FXML
    private ChoiceBox<Species> ch_species;

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
    private TableColumn<?, ?> colEvent;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colAnimalID;

    @FXML
    private TableView<AnimalType> tvDisplay;

    @FXML
    private ListView<AnimalType> lv_displayAnimal;

    @FXML
    private TextField tf_eventDate;

    @FXML
    private ComboBox<?> cb_time;

    private ObservableList<AnimalType> observableAnimal;


    public void initialize() throws SQLException {
        setupTableView();
        populateSpeciesCb();
        addToObservableList();
        btn_checkIn.setOnAction(this::handleButtonAction);
    }


    private void handleButtonAction(javafx.event.ActionEvent actionEvent) {
        Species species = ch_species.getValue();
        String breeds = tf_breed.getText();
        String petName = tf_petName.getText();
        String animalID = tf_animalID.getText();
        try {
            String productQuery = "INSERT INTO ANIMALS(SPECIES,BREED,PETNAME,ANIMALID) VALUES (?,?,?,?)";
            PreparedStatement addAnimal = Login_Controller.conn.prepareStatement(productQuery);
            addAnimal.setString(1, species.toString());
            addAnimal.setString(2, breeds);
            addAnimal.setString(3, petName);
            addAnimal.setString(4, animalID);
            addAnimal.executeUpdate();

            ch_species.getItems().clear();
            tf_breed.clear();
            tf_petName.clear();
            tf_animalID.clear();
            observableAnimal.clear();
            addToObservableList();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    } //end check in animal button

    public void addToObservableList() throws SQLException {
        String sql = "SELECT * FROM ANIMALS";
        ResultSet rs = Login_Controller.stmt.executeQuery(sql);
        while (rs.next()) {
            // these lines correspond to the database table columns
            Species species = Species.valueOf(rs.getString(1));
            String breed = rs.getString(2);
            String petName = rs.getString(3);
            String animalId = rs.getString(4);
            // create object
            AnimalType animalFromDB = new Widget(species, breed, petName, animalId);
            // save to observable list
            observableAnimal.add(animalFromDB);
            lv_displayAnimal.getItems().clear();
            for (AnimalType  animal : observableAnimal) {
                lv_displayAnimal.getItems().add(animal);
            }
        }
    }

    private void populateSpeciesCb() {
        ch_species.getItems().addAll(Species.Dogs);
        ch_species.getItems().addAll(Species.Cats);
        ch_species.getItems().addAll(Species.Rabbits);
        ch_species.getItems().addAll(Species.Monkeys);
        ch_species.getItems().addAll(Species.Ferrets);

    }

//    private void cb_breed() {
//        cb_breed.getItems().addAll(Breeds.Husky);
//        cb_breed.getItems().addAll(Breeds.Black_Sable);
//        cb_breed.getItems().addAll(Breeds.Capuchin);
//        cb_breed.getItems().addAll(Breeds.French_Lop);
//        cb_breed.getItems().addAll(Breeds.Munchkin);
//    }

    private void setupTableView() {
        observableAnimal = FXCollections.observableArrayList();
        colEvent.setCellValueFactory(new PropertyValueFactory("Event"));
        colDate.setCellValueFactory(new PropertyValueFactory("Date"));
        colTime.setCellValueFactory(new PropertyValueFactory("Time"));
        colAnimalID.setCellValueFactory(new PropertyValueFactory("animalID"));
        tvDisplay.setItems(observableAnimal);
    }

    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }

//    @FXML
//    public void checkIn(MouseEvent event) {
//        observableAnimal.add(new Widget(species, breeds, petName, animalID));
//        tvDisplay.getItems().add((Animal) observableAnimal);
//    }

    public void generateId() {
        tf_animalID.clear();
        String breed = tf_breed.getText();
        String petName = tf_petName.getText();
        Random rdmNumber = new Random();
        String animalId = String.format("%04d", rdmNumber.nextInt(1000));
        tf_animalID.appendText(breed.substring(0, 3).toUpperCase() + petName.substring(0, 3).toLowerCase() + animalId);
    }
}
