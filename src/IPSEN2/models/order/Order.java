package IPSEN2.models.order;

import IPSEN2.generators.pdf.InvoiceGenerator;
import IPSEN2.models.event.Event;
import IPSEN2.models.guest.Guest;

import java.util.ArrayList;

public class Order {

    private int id;

//    private String invoice;

    private Guest guest;

    private Event event;

    private OrderStatus status;

    private ArrayList<WineOrder> wineOrders;

    private double totalAmount;

    private String invoicePath;

    public Order (int id, int guestId, int eventId, int orderStatusId)
    {
        this.id = id;
        this.guest = new Guest(guestId);
        this.event = new Event(eventId);
        this.status = new OrderStatus(orderStatusId);
        this.totalAmount = 0.0;
        this.wineOrders = null;
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

    public ArrayList<WineOrder> getWineOrders() {
        return wineOrders;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getInvoicePath() {
        return invoicePath;
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

    public void setWineOrders(ArrayList<WineOrder> wineOrders) {
        this.wineOrders = wineOrders;
        double total = 0.0;
        for(WineOrder order: wineOrders) {
            total += order.getAmount() * order.getWine().getPrice();
        }
        this.totalAmount = total;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setInvoicePath(String invoicePath) {
        this.invoicePath = invoicePath;
    }
}