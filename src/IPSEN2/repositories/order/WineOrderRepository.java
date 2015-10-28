package IPSEN2.repositories.order;

import IPSEN2.database.Database;
import IPSEN2.models.order.WineOrder;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 15-10-2015.
 */
public class WineOrderRepository implements Crudable {
    private Database databaseInstance;

    public WineOrderRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<WineOrder> all() {
        ArrayList<WineOrder> wineOrders = new ArrayList<WineOrder>();
        ResultSet queryResult = databaseInstance.select("wine_order");
        try {
            while(queryResult.next()) {
                WineOrder order = new WineOrder(queryResult.getInt("order_id"), queryResult.getInt("wine_id"), queryResult.getInt("total_boxes"));
                wineOrders.add(order);
            }
        } catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return wineOrders;
    }

    //This will return an ArrayList with WineOrder objects that belong to @param orderID
    public ArrayList<WineOrder> find(int orderID) {
        ArrayList<WineOrder> wineOrders = new ArrayList<WineOrder>();
        ResultSet queryResult = databaseInstance.select("wine_order", "order_id="+orderID);
        try {
            while(queryResult.next()) {
                WineOrder order = new WineOrder(orderID, queryResult.getInt("wine_id"), queryResult.getInt("total_boxes"));
                wineOrders.add(order);
            }
        } catch(SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return wineOrders;
    }

    public int create(HashMap data) {
        HashMap dbData = new HashMap();
        dbData.put("order_id", data.get("orderID"));
        dbData.put("wine_id", data.get("wineID"));
        dbData.put("total_boxes", data.get("amount"));
        return databaseInstance.insertInto("wine_order", dbData);
    }

    public void update(int id, HashMap data) {

    }

    public void update(int orderID, int wineID, HashMap data) {

    }

    public void delete(int id) {

    }

    public void delete(int orderID, int wineID) {

    }
}
