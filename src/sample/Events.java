package sample;

/**
 * Event Class
 */
public class Events {
    private String animalID;
    private String events;
    private String date;
    private String time;

    Events(String animalID, String events, String date, String time) {
        this.animalID = animalID;
        this.events = events;
        this.date = date;
        this.time = time;
    }


    /**
     * Accessor to get ID field.
     *
     * @return String animalID
     */
    public String getAnimalID() {
        return animalID;
    }

    /**
     * Accessor to get Event field.
     *
     * @return String
     */
    public String getEvents() {
        return events;
    }

    /**
     * Accessor to get Date field.
     *
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Accessor to get Time field.
     *
     * @return String
     */
    public String getTime() {
        return time;
    }
}
