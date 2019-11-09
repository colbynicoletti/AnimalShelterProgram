package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Customer_Controller {

    @FXML
    private Button btn_adopt;
    @FXML
    private ComboBox<String> cb_specieSearch;
    @FXML
    private ComboBox<String> cb_searchBreed;
    @FXML
    private ComboBox<String> cb_animal_ID;
    @FXML
    private ComboBox<String> cb_dateTime;
    @FXML
    private Button btn_Appointment;
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


    public void initialize() {
        initializeComboBox();
        initializeComboBox1();
        initializeComboBox2();
        //initializeComboBox3();
    }

    private void initializeComboBox() {
        ObservableList<String> data = cb_specieSearch.getItems(); // Get item value input
        data.clear();
        data.add("CAT");
        data.add("Wildcat");
        data.add("African Golden Cat");
        data.add("Leopard Cat");
        data.add("African Wildcat");
    }

    private void initializeComboBox1() {
        ObservableList<String> data = cb_searchBreed.getItems(); // Get item value input
        data.clear();
        data.add("DOG");
        data.add("Pug");
        data.add("Bulldog");
        data.add("Beagle");
        data.add("Chihuahua");
        data.add("CAT");
        data.add("Persian Cat");
        data.add("Bengal Cat");
        data.add("Sphynx");
        data.add("Manx");
    }

    private void initializeComboBox2() {
        //ObservableList<String> data = null;
        try {
            ObservableList<String> data = cb_animal_ID.getItems(); // Get item value input

            //data.clear();
            data.add("DO12");
            data.add("DO34");
            data.add("CA12");
            data.add("CA34");
            data.add("A12");
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }



    @FXML
    void adoptButton(ActionEvent event) {
        System.out.println(cb_specieSearch.getValue());
        System.out.println(cb_searchBreed.getValue());
        System.out.println(cb_animal_ID.getValue());

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
        System.out.println(nameField.getText());
        System.out.println(numberField.getText());
    }


    public void previous(MouseEvent event) throws IOException {
        Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene homePage = new Scene(newRoot);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(homePage);
        appStage.show();
    }
}