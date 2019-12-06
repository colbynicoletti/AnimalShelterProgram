package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


/**
 * Login Class Controller that handles FXML functionality
 */
@SuppressWarnings("ALL")
public class Login_Controller {

    /**
     * SceneBuilder fields for FXML elements
     */
    @FXML
    public Button btn_employeeLogin;

    @FXML
    public Button btn_customerLogin;

    @FXML
    public Label loginLabel;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_employeePass;

    @FXML
    private TextArea ta_employeeAccount;

    @FXML
    private TextField tf_firstAndLastName;

    @FXML
    private Button btn_createAccount;

    @FXML
    private Button donateButton;

    private Employee myEmployee;

    /**
     * Class level fields
     */
    public static Connection conn;
    public static Statement stmt;

    /**
     * Method that Starts the call to class methods.
     *
     * @throws SQLException checks if sql statement is valid.
     */
    public void initialize() {
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/AnimalShelterDB";
        final String USER = "";
        final String PASS;

        System.out.println("Attempting to connect to database");
        try {
            Class.forName(JDBC_DRIVER);
            Properties prop = new Properties();
            prop.load(new FileInputStream("res/properties"));
            PASS = prop.getProperty("password");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            System.out.println("Successfully connected to Animal database!");
        } catch (Exception e) {
            e.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        }
        startTA();
    }

    /**
     * Method for customer login button.
     *
     * @param event MouseEvent
     * @throws IOException checks if IO statement is valid.
     */
    @FXML
    public void customerLogin(MouseEvent event) throws IOException {
        System.out.println("Customer Login Button Clicked.");
        Parent root2 = FXMLLoader.load(getClass().getResource("Customer.fxml"));
        Scene customerView = new Scene(root2, 700, 500);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(customerView);
        customerView.getStylesheets().add
                (getClass().getResource("Customer.css").toExternalForm());
        appStage.show();
    }


    /**
     * Method for employee login button also handles checks for employee info.
     *
     * @param event MouseEvent
     * @throws IOException  checks if IO statement is valid
     * @throws SQLException checks if sql statement is valid
     */
    @FXML
    public void employeeLogin(MouseEvent event) throws IOException, SQLException {
        String getUsername = tf_username.getText();
        String getPassword = tf_employeePass.getText();
        String sql = "SELECT * FROM EMPLOYEE";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String username = rs.getString("USERNAME");
            String password = rs.getString("PASSWORD");
            if ((username.equals(getUsername)) && (password.equals(getPassword))) {
                System.out.println("Employee Login Button Clicked.");
                Parent root3 = FXMLLoader.load(getClass().getResource("Employee.fxml"));
                Scene employeeView = new Scene(root3, 700, 500);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(employeeView);
                appStage.setScene(employeeView);
                employeeView.getStylesheets().add
                        (getClass().getResource("Customer.css").toExternalForm());
                appStage.show();
            } else {
                loginLabel.setText("Invalid account.");
            }
        }
        rs.close();
    }


    /**
     * Method to create employee account and insert employee values into database
     *
     * @throws SQLException checks if sql statement is valid
     */
    public void account() throws SQLException {
        String name = tf_firstAndLastName.getText();
        String password = tf_employeePass.getText();
        if ((!name.equals("")) && (!password.equals(""))) {
            myEmployee = new Employee(name, password);
            if ((!myEmployee.getUsername().equals("")) && (!myEmployee.getPassword().equals(""))) {
                String employeeCode = JOptionPane.showInputDialog("Input Employee Code");
                if (employeeCode.equals("AS123")) {
                    String productQuery = "INSERT INTO EMPLOYEE(NAME, USERNAME, PASSWORD, EMAIL) VALUES (?,?,?,?)";
                    PreparedStatement addEmployee = Login_Controller.conn.prepareStatement(productQuery);

                    addEmployee.setString(1, name);
                    addEmployee.setString(2, myEmployee.getUsername());
                    addEmployee.setString(3, password);
                    addEmployee.setString(4, myEmployee.getEmail());
                    addEmployee.executeUpdate();
                    addEmployee.close();

                    tf_firstAndLastName.clear();
                    tf_employeePass.clear();
                    ta_employeeAccount.appendText(myEmployee.toString() + "\n\n");

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid employee code. " +
                            "\nNote: Only employees need to create accounts and log in");
                }
            } else {
                JFrame frame = new JFrame("");
                JOptionPane.showMessageDialog(
                        frame.getContentPane(),
                        "Username or password has invalid format.\n",
                        "Invalid Format",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Name or password is empty.");
        }

    }

    /**
     * Method for create account button, calls account method.
     *
     * @param event MouseEvent
     * @throws SQLException checks if sql statement is valid
     */
    @FXML
    void createAccount(MouseEvent event) throws SQLException {
        ta_employeeAccount.clear();
        account();

    }


    /**
     * Method for employee name
     *
     * @param event MouseEvent
     */
    @FXML
    void firstAndLastName(MouseEvent event) {

    }

    /**
     * Method to initialize text area instructions
     */
    void startTA() {
        ta_employeeAccount.appendText("Are you an employee with the shelter? \n" +
                "enter your Last name and First Name \n" +
                "Then enter a Password to create an account \n\n" +
                "P.S dont forget the employee code given \n" +
                "by the shelter");
    }
}

