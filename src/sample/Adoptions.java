package sample;

/**
 * Class to work with the Adoption table in the Database.
 */
public class Adoptions {
    String animalID;
    String customerName;
    String phoneNumber;
    String date;
    String time;
    String petName;

    /**
     * Constructor used to implement the Adoption Record.
     *
     * @param animalID     String
     * @param customerName String
     * @param phoneNumber  String
     * @param date         String
     * @param time         String
     */
    public Adoptions(String animalID, String customerName, String phoneNumber, String date, String time, String petName) {
        this.animalID = animalID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;
        this.petName = petName;

    }

    /**
     * Accessor to get Animal ID.
     *
     * @return String animalID
     */
    public String getAnimalID() {
        return animalID;
    }

    /**
     * Mutator to set Animal ID.
     *
     * @param animalID String animalID
     */
    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    /**
     * toString Method used to print record order.
     *
     * @return String toString
     */
    @SuppressWarnings("Annotator")
    public String toString() {

        return String.format(
                "Animal ID: %s Pet Name: %s\n Customer Name: %s, Phone Number: %s\n Date: %s, Time: %s" + "\n**********************************************************",
                animalID, petName, customerName, phoneNumber, date, time);
    }
}
