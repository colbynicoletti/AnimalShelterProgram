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

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Employee Class Controller that handles FXML functionality
 */
@SuppressWarnings("ALL")
public class Employee_Controller extends Main {

    /**
     * SceneBuilder fields for FXML elements
     */
    @FXML
    public TextField tf_breed;

    @FXML
    public Button btn_generateId;

    @FXML
    public Button btn_submitEvent;

    @FXML
    public ChoiceBox<EventList> ch_event;

    @FXML
    public DatePicker datePicker;

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
    private TableColumn<?, ?> colEvent;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableColumn<?, ?> colAnimalID;

    @FXML
    private TableView<Events> tvDisplay;

    @FXML
    private ListView<AnimalType> lv_displayAnimal;

    @FXML
    private ComboBox<String> cb_time;

    @FXML
    private TextArea ta_adoptionRecord;

    /**
     * Class level fields.
     */
    public ObservableList<AnimalType> animalObservableList = FXCollections.observableArrayList();
    private ObservableList<Events> eventsObservableList;
    private ArrayList<Adoptions> adoptRecordArray;

    /**
     * Method that Starts the call to class methods.
     *
     * @throws SQLException checks if sql statement is valid.
     */
    public void initialize() throws SQLException {
        populateEventsCh();
        populateTime();
        setupTableView();
        setTvDisplay();
        populateSpeciesCh();
        addToObservableList();
        loadAdoptionLog();
        showAdoptions();
        btn_checkIn.setOnMouseClicked(this::addToAnimalTable);
        btn_submitEvent.setOnMouseClicked(this::addEvent);
    }

    /**
     * Method to set Species ChoiceBox.
     */
    private void populateSpeciesCh() {
        ch_species.getItems().addAll(Species.Dogs);
        ch_species.getItems().addAll(Species.Cats);
        ch_species.getItems().addAll(Species.Rabbits);
        ch_species.getItems().addAll(Species.Monkeys);
    }

    /**
     * Method to set Event ChoiceBox.
     */
    public void populateEventsCh() {
        ch_event.getItems().add(EventList.Vet_Checkup);
        ch_event.getItems().add(EventList.Kennel_Cleaning);
        ch_event.getItems().add(EventList.Animal_Washing);
        ch_event.getItems().add(EventList.Give_Medication);
        ch_event.getItems().add(EventList.Feed_Animal);

    }

    /**
     * Method to display animal observable list into List view.
     */
    public void setLv_displayAnimal() {
        for (AnimalType animal : animalObservableList) {
            lv_displayAnimal.getItems().add(animal);
        }
    }

    /**
     * Method to set Event table View.
     */
    private void setupTableView() {
        eventsObservableList = FXCollections.observableArrayList();
        colAnimalID.setCellValueFactory(new PropertyValueFactory("animalID"));
        colEvent.setCellValueFactory(new PropertyValueFactory("events"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        tvDisplay.setItems(eventsObservableList);
    }

    /**
     * Method to show adoption record in text area.
     */
    public void showAdoptions() {
        for (int i = 0; i < adoptRecordArray.size(); i++) {
            ta_adoptionRecord.appendText(adoptRecordArray.get(i).toString() + "\n");
        }
    }

    /**
     * Method for time comboBox.
     */
    public void populateTime() {
        for (int i = 1; i <= 24; i++) {
            cb_time.getItems().add(i + ":" + "00 hrs");
        }
    }
//*****************************************************************************************************************

    /**
     * Method for button to insert Animal Values into the Animal table.
     *
     * @param event MouseEvent
     */
    private void addToAnimalTable(MouseEvent event) {
        try {
            Species species = ch_species.getValue();
            String breeds = tf_breed.getText();
            String petName = tf_petName.getText();
            String animalID = tf_animalID.getText();

            String animalQuery = "INSERT INTO ANIMALS(SPECIES,BREED,PETNAME,ANIMALID) VALUES (?,?,?,?)";
            PreparedStatement addAnimal = Login_Controller.conn.prepareStatement(animalQuery);
            addAnimal.setString(1, species.toString());
            addAnimal.setString(2, breeds);
            addAnimal.setString(3, petName);
            addAnimal.setString(4, animalID);
            addAnimal.executeUpdate();

            ch_species.getSelectionModel().clearSelection();
            tf_breed.clear();
            tf_petName.clear();
            tf_animalID.clear();
            animalObservableList.clear();
            addToObservableList();
            addAnimal.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "All fields must be filled " +
                    "in before adding animal.");
        }
    }

    /**
     * Method for button to display the Animal Table and add values to animal observable list.
     *
     * @throws SQLException checks if sql statement is valid
     */
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
            animalObservableList.add(animalFromDB);
            lv_displayAnimal.getItems().clear();
            setLv_displayAnimal();
        }
        rs.close();
    }

    /**
     * Method for button to insert into Event table calls event observable list
     *
     * @param event MouseEvent
     */
    private void addEvent(MouseEvent event) {
        try {
            String animalId = lv_displayAnimal.getSelectionModel().getSelectedItem().getAnimalID();
            EventList events = ch_event.getSelectionModel().getSelectedItem();
            String date = datePicker.getValue().toString();
            String time = cb_time.getSelectionModel().getSelectedItem().toString();

            String adoptionQuery = "INSERT INTO EVENT(ANIMAL_ID, EVENTS, DATE, TIME) VALUES (?,?,?,?)";
            PreparedStatement addEvent = Login_Controller.conn.prepareStatement(adoptionQuery);
            addEvent.setString(1, animalId);
            addEvent.setString(2, events.toString());
            addEvent.setString(3, date);
            addEvent.setString(4, time);
            addEvent.executeUpdate();

            Alert a = new Alert(Alert.AlertType.NONE);
            a.setAlertType(Alert.AlertType.CONFIRMATION);
            a.setContentText("Animal has been successfully added to the database.");
            a.show();
            eventsObservableList.clear();
            lv_displayAnimal.getSelectionModel().clearSelection();
            ch_event.getSelectionModel().clearSelection();
            datePicker.getEditor().clear();
            cb_time.getSelectionModel().clearSelection();
            setTvDisplay();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "All fields must be filled " +
                    "in before adding an event.");
        }
    }

    /**
     * Method to display Event table view and add to event observable list.
     *
     * @throws SQLException checks if sql statement is valid.
     */
    public void setTvDisplay() throws SQLException {
        String sql = "SELECT * FROM EVENT";
        ResultSet rs = Login_Controller.stmt.executeQuery(sql);
        while (rs.next()) {
            // these lines correspond to the database table columns
            String animal = (rs.getString(1));
            String events = rs.getString(2);
            String date = rs.getString(3);
            String time = rs.getString(4);
            // create object
            Events eventsFromDb = new Events(animal, events, date, time);
            // save to observable list
            eventsObservableList.add(eventsFromDb);
        }
        rs.close();
    }

    /**
     * '
     * Method to display Adoptions made by customer
     *
     * @throws SQLException checks if sql statement is valid
     */
    public void loadAdoptionLog() throws SQLException {

        adoptRecordArray = new ArrayList<>();
        String sql = "SELECT * FROM ADOPTION_TABLE";
        ResultSet rs = Login_Controller.stmt.executeQuery(sql);
        while (rs.next()) {
            // corresponds to database table columns
            String animalID = rs.getString(1);
            String customerName = rs.getString(2);
            String phoneNumber = rs.getString(3);
            String date = rs.getString(4);
            String time = rs.getString(5);
            String petName = rs.getString(6);
            // create object
            Adoptions adoptionDB =
                    new Adoptions(animalID, customerName, phoneNumber, date, time, petName);
            // save to observable list
            adoptRecordArray.add(adoptionDB);
        }
        rs.close();
    }

    /**
     * Method used to generate an animal ID.
     */
    public void generateId() {
        tf_animalID.clear();
        String breed = tf_breed.getText();
        String petName = tf_petName.getText();
        Random rdmNumber = new Random();
        String animalId = String.format("%04d", rdmNumber.nextInt(1000));
        tf_animalID.appendText(breed.substring(0, 3).toUpperCase() + petName.substring(0, 3).toLowerCase() + animalId);
    }

    /**
     * Method used to make home button.
     *
     * @param event MouseEvent
     * @throws IOException checks if IO statement is valid
     */
    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}



