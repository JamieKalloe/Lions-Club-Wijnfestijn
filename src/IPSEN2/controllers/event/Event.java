package IPSEN2.controllers.event;

/**
 * Created by Philip on 09-10-15.
 */
public class Event{

    public  static Integer eventID = 0;
    public String eventName;
    public String eventPlace;
    public String eventAddress;
    public String eventDate;

    public Event(String eventName, String eventPlace, String eventAddress, String eventDate) {
        this.eventName = eventName;
        this.eventPlace = eventPlace;
        this.eventAddress = eventAddress;
        this.eventDate = eventDate;
        eventID += 1;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public static Integer getEventID() {
        return eventID;
    }

    public static void setEventID(int eventID) {
        Event.eventID = eventID;
    }

    public String getEventPlace() {
        return eventPlace;
    }

    public void setEventPlace(String eventPlace) {
        this.eventPlace = eventPlace;
    }

    public String getEventAddress() {
        return eventAddress;
    }

    public void setEventAddress(String eventAdress) {
        this.eventAddress = eventAdress;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }
}