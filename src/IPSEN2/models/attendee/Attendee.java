package IPSEN2.models.attendee;

/**
 * Created by Philip on 13-10-15.
 */
public class Attendee {

    private int guestID;
    private int eventID;
    private boolean attended;

    public Attendee(int eventID) {
        this.eventID = eventID;
    }

    public Attendee () {

    }

    public int getGuestID() {return guestID;}

    public int getEventID() {return eventID;}

    public boolean isAttended() {
        return attended;
    }

    public void setGuestID(int guestID) {this.guestID = guestID;}

    public void setEventID(int eventID) {this.eventID = eventID;}

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
