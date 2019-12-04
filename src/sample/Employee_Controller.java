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
import java.sql.*;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 */

public class Employee_Controller extends Main {

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

    public ObservableList<AnimalType> animalObservableList = FXCollections.observableArrayList();
    private ObservableList<Events> eventsObservableList;
    private ArrayList<Adoptions> adoptRecordArray;


    public void initialize() throws SQLException {
        populateEvents();
        populateTime();
        setupTableView();
        setTvDisplay();
        populateSpeciesCb();
        addToObservableList();
        loadAdoptionLog();
        showAdoptions();
        btn_checkIn.setOnMouseClicked(this::addToAnimalTable);
        btn_submitEvent.setOnMouseClicked(this::addEvent);
    }

    private void populateSpeciesCb() {
        ch_species.getItems().addAll(Species.Dogs);
        ch_species.getItems().addAll(Species.Cats);
        ch_species.getItems().addAll(Species.Rabbits);
        ch_species.getItems().addAll(Species.Monkeys);
    }

    private void addToAnimalTable(MouseEvent event) {
        Species species = ch_species.getValue();
        String breeds = tf_breed.getText();
        String petName = tf_petName.getText();
        String animalID = tf_animalID.getText();
        try {
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
        }
    }

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
    public void setLv_displayAnimal(){
        for (AnimalType animal : animalObservableList) {
            lv_displayAnimal.getItems().add(animal);
        }
    }

    private void addEvent(MouseEvent event) {
        String animalId = lv_displayAnimal.getSelectionModel().getSelectedItem().getAnimalID();
        EventList events = ch_event.getSelectionModel().getSelectedItem();
        String date = datePicker.getValue().toString();
        String time = cb_time.getSelectionModel().getSelectedItem().toString();
        try {
            String adoptionQuery = "INSERT INTO EVENT(ANIMAL_ID, EVENTS, DATE, TIME) VALUES (?,?,?,?)";
            PreparedStatement addEvent = Login_Controller.conn.prepareStatement(adoptionQuery);
            addEvent.setString(1, animalId);
            addEvent.setString(2, events.toString());
            addEvent.setString(3, date);
            addEvent.setString(4, time);
            addEvent.executeUpdate();

            eventsObservableList.clear();
            setTvDisplay();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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

    /**'
     *
     * @throws SQLException
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
            String date =(rs.getString(4));
            String time = rs.getString(5);
            // create object
            Adoptions adoptionDB =
                    new Adoptions(animalID, customerName, phoneNumber, date, time);
            // save to observable list
            adoptRecordArray.add(adoptionDB);
        }
        rs.close();
    }

    public void showAdoptions() {
        for (int i = 0; i < adoptRecordArray.size(); i++) {
            ta_adoptionRecord.appendText(adoptRecordArray.get(i).toString() + "\n");
        }
    }

    public void populateTime() {
        for(int i = 1; i <= 24; i++) {
            cb_time.getItems().add(i + ":" + "00 hrs");
        }
    }

  public void populateEvents() {
        ch_event.getItems().add(EventList.Vet_Checkup);
        ch_event.getItems().add(EventList.Kennel_Cleaning);
        ch_event.getItems().add(EventList.Animal_Washing);
        ch_event.getItems().add(EventList.Give_Medication);
        ch_event.getItems().add(EventList.Feed_Animal);
    }

    private void setupTableView() {
        eventsObservableList = FXCollections.observableArrayList();
        colAnimalID.setCellValueFactory(new PropertyValueFactory("animalID"));
        colEvent.setCellValueFactory(new PropertyValueFactory("events"));
        colDate.setCellValueFactory(new PropertyValueFactory("date"));
        colTime.setCellValueFactory(new PropertyValueFactory("time"));
        tvDisplay.setItems(eventsObservableList);
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
