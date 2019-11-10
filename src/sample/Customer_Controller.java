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
import java.util.ArrayList;

public class Customer_Controller {

    @FXML
    private ChoiceBox<Species> speciesChoice;

    @FXML
    private ChoiceBox<Breeds> breedChoice;

    @FXML
    private ChoiceBox<String> idChoice;
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
    private TableColumn<?, ?> speciesCol;

    @FXML
    private TableColumn<?, ?> breedCol;

    @FXML
    private TableColumn<?, ?> petNameCol;

    @FXML
    private TableColumn<?, ?> idCol;
    @FXML
    private TextArea selectedAnimal;



    public void initialize() {
        initializeChoiceBox();
        initializeChoiceBox1();
        initializeChoiceBox2();
        //initializeComboBox3();
    }

    private void initializeChoiceBox() {

        speciesChoice.getItems().addAll(Species.values());
        speciesChoice.getSelectionModel().selectFirst();

    }
    private void initializeChoiceBox1() {
        breedChoice.getItems().addAll(Breeds.values());
        breedChoice.getSelectionModel().selectFirst();
    }

    private void initializeChoiceBox2() {

        ObservableList<String> data = idChoice.getItems(); // Get item value input

        //data.clear();
        data.add("DO12");
        data.add("DO34");
        data.add("CA12");
        data.add("CA34");
        data.add("A12");
//
        idChoice.getSelectionModel().selectFirst();
    }

    @FXML
    void searchButton(ActionEvent event) {
        ObservableList <AnimalType> items = FXCollections.observableArrayList();

        displayAnimal.setItems(items);
        items.add(new AnimalType(speciesChoice.getValue(), breedChoice.getValue(), idChoice.getValue()) {
            @Override
            public void setBreed(Breeds breed) {

            }

            @Override
            public Breeds getBreed() {
                return null;
            }

            @Override
            public void setName(String petName) {

            }

            @Override
            public String getName() {
                return null;
            }


            public void setAnimalID(int animalID) {

            }
        });

//        items.add(speciesChoice.getValue());
//        displayAnimal.getItems().add(String.valueOf( speciesChoice.getValue()));
//        displayAnimal.getItems().add(String.valueOf(breedChoice.getValue()));
//        displayAnimal.getItems().add(String.valueOf(idChoice.getValue()));

    }
    @FXML
    void adoptButton(ActionEvent event) {

        selectedAnimal.appendText(String.valueOf(displayAnimal.getSelectionModel().getSelectedItem()));

    }

//    private void initializeComboBox3() {
////        Calendar cal = new GregorianCalendar();
////        int month =cal.get(Calendar.MONTH);
////        int year =cal.get(Calendar.YEAR);
////        int day =cal.get(Calendar.DAY_OF_MONTH);
////        cb_dateTime.add(+year+"-"+(month+1)+"-"+day);
////        cmb_date.addItem(+year+"-"+(month+1)+"-"+(day+1));
//    }

    @FXML
    void submitAppointment(ActionEvent event) {
        System.out.println("Thank you for your interest in Adopting");
        System.out.println("Below is your appointment information");
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
        System.out.println(displayAnimal.getSelectionModel().getSelectedItem());

    }


    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}