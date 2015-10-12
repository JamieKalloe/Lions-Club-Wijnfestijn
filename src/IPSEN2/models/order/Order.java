package IPSEN2.models.order;

import IPSEN2.models.event.Event;
import IPSEN2.models.guest.Guest;

import java.util.ArrayList;

public class Order {

    private int id;

    private Guest guest;

    private Event event;

    private OrderStatus status;

    private ArrayList<IPSEN2.models.wine.Wine> wines;

    public Order (int id, int guestId, int eventId, int orderStatusId)
    {
        this.id = id;
        this.guest = new Guest(guestId);
        this.event = new Event(eventId);
        this.status = new OrderStatus(orderStatusId);
    }

    public int getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Event getEvent() {
        return event;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<IPSEN2.models.wine.Wine> getWines() {
        return wines;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setWines(ArrayList<IPSEN2.models.wine.Wine> wines) {
        this.wines = wines;
    }

}