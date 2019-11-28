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
import java.sql.Connection;

public class Employee_Controller extends Main {

    @FXML
    private ComboBox<Species> cb_species;

    @FXML
    private ComboBox<DogBreeds> cb_breed;

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

    @FXML
    private TableColumn<?, ?> colSpecies;

    @FXML
    private TableColumn<?, ?> colBreed;

    @FXML
    private TableColumn<?, ?> colPetName;

    @FXML
    private TableColumn<?, ?> colAnimalID;

    @FXML
    private TableView<Animal> tvDisplay;

    @FXML
    private Button btn_updateSpecies;

    private Connection conn = null;

    ObservableList<Animal> observableAnimal;


    public void initialize() {
//        btn_checkIn.setOnAction(this::handleButtonAction);
        cb_species();
        checkInMethod();
    }


//    private void handleButtonAction(javafx.event.ActionEvent actionEvent) {
//        Species species = cb_species.getValue();
//        Breeds breeds = cb_breed.getValue();
//        String petName = tf_petName.getText();
//        String petID = tf_animalID.getText();
//
//        try {
//            String productQuery = "INSERT INTO ANIMAL(SPECIES,BREED,PET_NAME,ANIMAL_ID) VALUES (?,?,?,?)";
//            PreparedStatement addAnimal = conn.prepareStatement(productQuery);
//            addAnimal.setString(1, species.toString());
//            addAnimal.setString(2, breeds.toString());
//            addAnimal.setString(3, petName);
//            addAnimal.setInt(4, Integer.parseInt(petID));
//            addAnimal.executeUpdate();
//
//            tf_petName.clear();
//            tf_animalID.clear();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//
//    } //end check in animal button

    private void cb_species() {

        cb_species.getItems().addAll(Species.Dogs);
        cb_species.getItems().addAll(Species.Cats);
        cb_species.getItems().addAll(Species.Rabbits);
        cb_species.getItems().addAll(Species.Monkeys);
        cb_species.getItems().addAll(Species.Ferrets);

    }

    @FXML
    void updateSpecies(MouseEvent event) {
        System.out.println(cb_species.getValue());
        System.out.println("Dogs");
        System.out.println(cb_species.getValue().equals("Dogs"));

        if (cb_species.getValue().equals("Dogs")){
            System.out.println("Dogs are selected.");
            addDogBreeds();
        }
    }

    public void addDogBreeds(){
        for (DogBreeds it : DogBreeds.values()) {
            cb_breed.getItems().add(it);
        }
    }

    private static void cb_breed() {

    }

    private void checkInMethod(){
        observableAnimal = FXCollections.observableArrayList();
        colSpecies.setCellValueFactory(new PropertyValueFactory("species"));
        colBreed.setCellValueFactory(new PropertyValueFactory("breeds"));
        colPetName.setCellValueFactory(new PropertyValueFactory("petName"));
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

    @FXML
    void checkIn(MouseEvent event) {
        Species species = cb_species.getValue();
        DogBreeds breeds = cb_breed.getValue();
        String petName = tf_petName.getText();
        tf_petName.clear();
        String animalID = tf_animalID.getText();

        observableAnimal.add(new Widget(species, breeds, petName, animalID));
        tvDisplay.getItems().add((Animal) observableAnimal);
    }

}
