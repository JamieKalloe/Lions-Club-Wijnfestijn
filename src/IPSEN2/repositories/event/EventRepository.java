package IPSEN2.repositories.event;

import IPSEN2.database.Database;
import IPSEN2.models.address.Address;
import IPSEN2.models.event.Event;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 08-10-2015.
 */
public class EventRepository implements Crudable {

    private Database databaseInstance;

    public EventRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<Event> all() {
        ArrayList<Event> eventList = new ArrayList<>();
        ResultSet queryResult = databaseInstance.select("event");
        try {
            while(queryResult.next()) {
                Event event = new Event();
                event.setId(queryResult.getInt("id"));
                event.setAddress(new Address(queryResult.getInt("address_id")));
                event.setName(queryResult.getString("name"));
                event.setStartDate(queryResult.getDate("start_date"));
                event.setEndDate(queryResult.getDate("end_date"));
                eventList.add(event);
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return eventList;
    }

    public Event find(int id) {
        ResultSet queryResult = databaseInstance.select("event", id);
        try {
            while(queryResult.next()){
                Event event = new Event();
                event.setId(queryResult.getInt("id"));
                event.setAddress(new Address(queryResult.getInt("address_id")));
                event.setName(queryResult.getString("name"));
                event.setStartDate(queryResult.getDate("start_date"));
                event.setEndDate(queryResult.getDate("end_date"));
                return event;
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
        return null;
    }

    public int create(HashMap data){
        HashMap dbData = new HashMap();
        dbData.put("address_id",  Integer.parseInt(data.get("addressID").toString()));
        dbData.put("name", data.get("name").toString());
        dbData.put("start_date", data.get("startDate"));
        dbData.put("end_date", data.get("endDate"));
        return databaseInstance.insertInto("event", dbData);
    }

    public void update(int id, HashMap data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap dbData = new HashMap();
        dbData.put("address_id",  Integer.parseInt(data.get("addressID").toString()));
        dbData.put("name", data.get("name").toString());
        dbData.put("start_date", sdf.format(data.get("startDate")));
        dbData.put("end_date", sdf.format(data.get("endDate")));

        databaseInstance.update("event", id, dbData);
    }

    public void delete(int id) {
        databaseInstance.delete("event", id);
    }

}
