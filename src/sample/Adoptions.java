package sample;

public class Adoptions {
    String animalID;
    String customerName;
    String phoneNumber;
    String date;
    String time;


    public Adoptions(String animalID, String customerName, String phoneNumber, String date, String time) {
        this.animalID = animalID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;

    }

    public String getAnimalID() {
        return animalID;
    }

    public void setAnimalID(String animalID) {
        this.animalID = animalID;
    }

    public String toString() {

        return String.format(
                "Animal_ID: %s\n Customer_Name: %s, Phone_Number: %s\n Date: %s, Time: %s",
                animalID, customerName, phoneNumber, date, time);
    }
}
