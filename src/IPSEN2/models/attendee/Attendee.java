package IPSEN2.models.attendee;

/**
 * Created by Philip on 13-10-15.
 */
public class Attendee {

    private int guestID;
    private int eventID;
    private boolean attended;

    /**
     * Instantiates a new Attendee.
     *
     * @param eventID the event id
     */
    public Attendee(int eventID) {
        this.eventID = eventID;
    }

    /**
     * Instantiates a new Attendee.
     */
    public Attendee () {

    }

    /**
     * Gets guest id.
     *
     * @return the guest id
     */
    public int getGuestID() {return guestID;}

    /**
     * Gets event id.
     *
     * @return the event id
     */
    public int getEventID() {return eventID;}

    /**
     * Is attended boolean.
     *
     * @return the boolean
     */
    public boolean isAttended() {
        return attended;
    }

    /**
     * Sets guest id.
     *
     * @param guestID the guest id
     */
    public void setGuestID(int guestID) {this.guestID = guestID;}

    /**
     * Sets event id.
     *
     * @param eventID the event id
     */
    public void setEventID(int eventID) {this.eventID = eventID;}

    /**
     * Sets attended.
     *
     * @param attended the attended
     */
    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
