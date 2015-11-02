package IPSEN2.repositories.order;

import IPSEN2.database.Database;
import IPSEN2.models.order.Order;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrderRepository implements Crudable {

    private Database databaseInstance;

    public OrderRepository()
    {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<Order> all()
    {
        ArrayList<Order> orders = new ArrayList<>();

        ResultSet queryResult = databaseInstance.select("order");

        try
        {
            while (queryResult.next())
            {
                Order order = new Order(
                        queryResult.getInt("id"),
                        queryResult.getInt("guest_id"),
                        queryResult.getInt("event_id"),
                        queryResult.getInt("status_id")
                );

                orders.add(order);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return orders;
    }

    public Order find(int id)
    {
        ResultSet queryResult = databaseInstance.select("order", id);

        try {
            while (queryResult.next())
            {
                Order order = new Order(
                        queryResult.getInt("id"),
                        queryResult.getInt("guest_id"),
                        queryResult.getInt("event_id"),
                        queryResult.getInt("status_id")
                );

                return order;
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return null;
    }

    public int create(HashMap data)
    {
        HashMap databaseData = new HashMap();

        databaseData.put("guest_id", Integer.parseInt(data.get("guestId").toString()));
        databaseData.put("event_id", Integer.parseInt(data.get("eventId").toString()));
        databaseData.put("status_id", Integer.parseInt(data.get("orderStatusId").toString()));

        return databaseInstance.insertInto("order", databaseData);
    }

    public void update(int id, HashMap data)
    {
        HashMap databaseData = new HashMap();
        databaseData.put("guest_id", Integer.parseInt(data.get("guestId").toString()));
        databaseData.put("event_id", Integer.parseInt(data.get("eventId").toString()));
        databaseData.put("status_id", Integer.parseInt(data.get("orderStatusId").toString()));

        databaseInstance.update("order", id, databaseData);
    }

    public void delete(int id)
    {
        databaseInstance.delete("Order", id);
    }

}
