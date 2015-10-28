package IPSEN2.models.order;

import IPSEN2.models.wine.Wine;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by mdbaz on 15-10-2015.
 */
public class WineOrder {
    private int orderID;
    private Wine wine;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private int amount;

    public WineOrder(int wineID, int amount) {
        this.wine = new Wine(wineID);
        this.amount = amount;
    }

    public WineOrder(int orderID, int wineID, int amount) {
        this.wine = new Wine(wineID);
        this.amount = amount;
    }


    //GETTERS
    public int getOrderID() {
        return orderID;
    }

    public Wine getWine() {
        return wine;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {return wine.getName();}

    //SETTERS
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
