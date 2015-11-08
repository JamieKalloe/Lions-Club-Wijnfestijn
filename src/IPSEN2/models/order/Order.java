package IPSEN2.models.order;

import IPSEN2.models.TableViewItem;
import IPSEN2.models.event.Event;
import IPSEN2.models.guest.Guest;

import java.util.ArrayList;
import java.util.Date;

/**
 * The Order.
 * Date of creation: 12-09-15.
 *
 * @author Thomas Neuteboom
 */
public class Order extends TableViewItem {

    private Guest guest;

    private Event event;

    private OrderStatus status;

    private Date date;

    private ArrayList<WineOrder> wineOrders;

    private double totalAmount;

    /**
     * Instantiates a new Order.
     *
     * @param id            the id
     * @param guestId       the guest id
     * @param eventId       the event id
     * @param orderStatusId the order status id
     */
    public Order(int id, int guestId, int eventId, int orderStatusId)
    {
        this.id = id;
        this.guest = new Guest(guestId);
        this.event = new Event(eventId);
        this.status = new OrderStatus(orderStatusId);
        this.totalAmount = 0.0;
        this.wineOrders = null;
        this.date = new Date();
    }

    /**
     * Gets guest.
     *
     * @return the guest
     */
    public Guest getGuest()
    {
        return guest;
    }

    /**
     * Gets event.
     *
     * @return the event
     */
    public Event getEvent()
    {
        return event;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus()
    {
        return status;
    }

    /**
     * Gets wine orders.
     *
     * @return the wine orders
     */
    public ArrayList<WineOrder> getWineOrders()
    {
        return wineOrders;
    }

    /**
     * Gets total amount.
     *
     * @return the total amount
     */
    public double getTotalAmount()
    {
        return totalAmount;
    }

    public boolean getSelected()
    {
        return selected;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate()
    {
        return date;
    }

    /**
     * Sets guest.
     *
     * @param guest the guest
     */
    public void setGuest(Guest guest)
    {
        this.guest = guest;
    }

    /**
     * Sets event.
     *
     * @param event the event
     */
    public void setEvent(Event event)
    {
        this.event = event;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status)
    {
        this.status = status;
    }

    /**
     * Sets wine orders.
     *
     * @param wineOrders the wine orders
     */
    public void setWineOrders(ArrayList<WineOrder> wineOrders)
    {
        this.wineOrders = wineOrders;
        double total = 0.0;
        for (WineOrder order : wineOrders)
        {
            total += order.getAmount() * order.getWine().getPrice();
        }
        this.totalAmount = total;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date)
    {
        this.date = date;
    }

    /**
     * Sets total amount.
     *
     * @param totalAmount the total amount
     */
    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }

}