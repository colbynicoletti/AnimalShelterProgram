package sample;

import java.util.Date;

public class Adoptions {
String animalID;
String customerName;
String phoneNumber;
Date date;
String time;


    public Adoptions(String animalID, String customerName, String phoneNumber, Date date, String time) {
        this.animalID = animalID;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.time = time;

    }

    /**
     * Accessor for local ProduceDate field.
     *
     * @return Date produceDate
     */
    public Date getTimeStamp() {
        return date;
    }

    /**
     * Mutator sets local produce Date field.
     *
     * @param produceDate Date produceDate
     */
    public void setProduceDate(Date produceDate) {
        this.date = new Date(produceDate.getTime());
    }

    public String toString() {

        return String.format(
                "Animal_ID: %s\n Customer_Name: %s, Phone_Number: %s\n Date: %s, Time: %s",
                animalID, customerName, phoneNumber, date, time);
    }
}
