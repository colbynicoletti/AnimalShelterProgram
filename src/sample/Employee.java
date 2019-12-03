package sample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/** Employee class allows the user to create an employee account. @Author: Nickolas Gadomski */
@SuppressWarnings("ALL")
public class Employee {
    /** Class level fields to be used throughout the code. */
    private StringBuilder name;

    String username;
    private String password;
    String email;

    /**
     * Employee class constructor for local fields.
     *
     * @param name local String name
     * @param password local String password
     */
    Employee(String name, String password) {
        StringBuilder sbName = new StringBuilder(name);
        StringBuilder defaultUsername = new StringBuilder("default");
        StringBuilder defaultEmail = new StringBuilder("user");
        this.name = sbName;
        this.password = password;
        if (checkName(sbName)) {
            setUsername(sbName);
            setEmail(sbName);
        } else {
            JFrame frame = new JFrame("");
            JOptionPane.showMessageDialog(
                    frame.getContentPane(),
                    "Input First name & Last name to create custom account\n" + "Default account is set.",
                    "Default Username",
                    JOptionPane.ERROR_MESSAGE);
            setUsername(defaultUsername);
            setEmail(defaultEmail);
        }
        if (isValidPassword(password)) {
            this.password = password;
        } else {
            this.password = "pw";
            JFrame frame = new JFrame("");
            JOptionPane.showMessageDialog(
                    frame.getContentPane(),
                    "Password must contain Upper and Lower case letter & Special Character\n"
                            + "Default password is set.",
                    "Default Password",
                    JOptionPane.ERROR_MESSAGE);
            setUsername(defaultUsername);
        }
    }

    /**
     * Method to check if the name entered by the user is valid.
     *
     * @param name local StringBuilder name
     * @return true
     */
    private boolean checkName(StringBuilder name) {
        String[] splitArray = name.toString().split(" ");
        return splitArray.length != 1;
    }

    /**
     * Method to set the username from the entered name by the user.
     *
     * @param name local StringBuilder name
     */
    private void setUsername(StringBuilder name) {
        String[] splitArray = name.toString().split(" ");
        if (splitArray.length > 1) {
            this.username = (splitArray[0].substring(0, 1) + splitArray[1]).toLowerCase();
        } else {
            this.username = (splitArray[0]).toLowerCase();
        }
    }

    /**
     * Method to set the email from the entered name by the user.
     *
     * @param name local StringBuilder name
     */
    private void setEmail(StringBuilder name) {
        String[] splitArray = name.toString().split(" ");
        if (splitArray.length > 1) {
            this.email = (splitArray[0] + "." + splitArray[1]).toLowerCase() + "@animalShelter.Test";
        } else {
            this.email = (splitArray[0]).toLowerCase() + "@animalShelter.Test";
        }
    }

    /**
     * Method to check if the password created by the user is valid.
     *
     * @param password local String password
     * @return matcher
     */
    private boolean isValidPassword(String password) {
        final String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*$";
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * Accessor for local name field.
     *
     * @return StringBuilder name
     */
    public StringBuilder getName() {
        return name;
    }

    /**
     * Accessor for local password field.
     *
     * @return String password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Accessor for local user name field.
     *
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Format String Method.
     *
     * @return formatted String
     */
    public String toString() {
        return String.format(
                "Employee Details"
                        + "\nName : "
                        + name
                        + "\nUsername : "
                        + username
                        + "\nEmail : "
                        + email
                        + "\nInitial Password : "
                        + password);
    }
}
