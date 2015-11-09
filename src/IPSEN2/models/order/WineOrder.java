package IPSEN2.models.order;

import IPSEN2.models.wine.Wine;
import javafx.beans.property.SimpleStringProperty;

/**
 * The Order status.
 * Date of creation: 15-10-15.
 *
 * @author Mike Bazuin
 */
public class WineOrder {

    private int orderID;

    private Wine wine;

    private SimpleStringProperty name = new SimpleStringProperty("");

    private int amount;

    /**
     * Instantiates a new Wine order.
     *
     * @param wineID the wine id
     * @param amount the amount
     */
    public WineOrder(int wineID, int amount)
    {
        this.wine = new Wine(wineID);
        this.amount = amount;
    }

    /**
     * Instantiates a new Wine order.
     *
     * @param orderID the order id
     * @param wineID  the wine id
     * @param amount  the amount
     */
    public WineOrder(int orderID, int wineID, int amount)
    {
        this.wine = new Wine(wineID);
        this.amount = amount;
    }

    /**
     * Gets order id.
     *
     * @return the order id
     */
    public int getOrderID()
    {
        return orderID;
    }

    /**
     * Gets wine.
     *
     * @return the wine
     */
    public Wine getWine()
    {
        return wine;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount()
    {
        return amount;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName()
    {
        return wine.getName();
    }

    /**
     * Sets order id.
     *
     * @param orderID the order id
     */
    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }

    /**
     * Sets wine.
     *
     * @param wine the wine
     */
    public void setWine(Wine wine)
    {
        this.wine = wine;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount)
    {
        this.amount = amount;
    }

}
