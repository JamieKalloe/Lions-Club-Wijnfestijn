package IPSEN2.repositories.order;

import IPSEN2.database.Database;
import IPSEN2.models.order.OrderStatus;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Thomas on 12-10-15.
 */
public class OrderStatusRepository implements Crudable {

    private Database databaseInstance;

    public OrderStatusRepository()
    {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<OrderStatus> all()
    {
        ArrayList<OrderStatus> orderStatuses = new ArrayList<>();

        ResultSet queryResult = databaseInstance.select("order_status");

        try
        {
            while (queryResult.next())
            {
                OrderStatus orderStatus = new OrderStatus(
                        queryResult.getInt("id"),
                        queryResult.getString("name")
                );

                orderStatuses.add(orderStatus);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return orderStatuses;
    }

    public OrderStatus find(int id)
    {
        ResultSet queryResult = databaseInstance.select("order_status", id);

        try
        {
            while (queryResult.next())
            {
                return new OrderStatus(
                        queryResult.getInt("id"),
                        queryResult.getString("name")
                );
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

        databaseData.put("name", data.get("name"));

        return databaseInstance.insertInto("order_status", databaseData);
    }

    public void update(int id, HashMap data)
    {
        HashMap databaseData = new HashMap();

        databaseData.put("name", data.get("name"));

        databaseInstance.update("order_status", id, databaseData);
    }

    public void delete(int id)
    {
        databaseInstance.delete("Order", id);
    }

}
