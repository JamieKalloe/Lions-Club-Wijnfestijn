package IPSEN2.models.order;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.event.Event;
import IPSEN2.models.guest.Guest;

import java.util.ArrayList;
import java.util.Date;

public class Order extends TableViewItem{

    private Guest guest;

    private Event event;

    private OrderStatus status;

    private Date date;

    private ArrayList<WineOrder> wineOrders;

    private double totalAmount;

    public Order (int id, int guestId, int eventId, int orderStatusId)
    {
        this.id = id;
        this.guest = new Guest(guestId);
        this.event = new Event(eventId);
        this.status = new OrderStatus(orderStatusId);
        this.totalAmount = 0.0;
        this.wineOrders = null;
        this.date = new Date();
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

    public ArrayList<WineOrder> getWineOrders() {
        return wineOrders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public boolean getSelected() {
        return selected;
    }

    public Date getDate() { return date;}

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setWineOrders(ArrayList<WineOrder> wineOrders) {
        this.wineOrders = wineOrders;
        double total = 0.0;
        for(WineOrder order: wineOrders) {
            total += order.getAmount() * order.getWine().getPrice();
        }
        this.totalAmount = total;
    }

    public void setDate(Date date) { this.date = date;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

}