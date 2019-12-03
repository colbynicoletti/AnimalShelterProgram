package sample;

public class Events {
    private String animalID;
    private String events;
    private String date;
    private String time;

    Events(String animalID, String events, String date, String time){
        this.animalID = animalID;
        this.events = events;
        this.date = date;
        this.time = time;
    }

    public String getAnimalID(){
        return animalID;
    }
    public String getEvents(){
        return events;
    }
    public String getDate(){
        return date;
    }
    public String getTime(){
        return time;
    }
}
