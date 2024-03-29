package IPSEN2.repositories.attendee;

import IPSEN2.database.Database;
import IPSEN2.models.attendee.Attendee;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philip on 13-10-15.
 */
public class AttendeeRepository implements Crudable {
    private Database databaseInstance;

    /**
     * Instantiates a new Attendee repository.
     */
    public AttendeeRepository() {
        databaseInstance = Database.getInstance();
    }

    @Override
    public ArrayList<Attendee> all() {
        ArrayList<Attendee> eventList = new ArrayList<>();
        ResultSet queryResult = databaseInstance.select("attendee");
        try {
            while(queryResult.next()) {
                Attendee attendee = new Attendee();
                attendee.setGuestID(queryResult.getInt("guest_id"));
                attendee.setEventID(queryResult.getInt("event_id"));
                eventList.add(attendee);
            }
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return eventList;
    }

    @Override
    public Attendee find(int id) {
        ResultSet queryResult = databaseInstance.select("attendee", "guest_id=" + id);
        try {
            while (queryResult.next()) {
                Attendee attendee = new Attendee(queryResult.getInt("event_id"));
                attendee.setGuestID(queryResult.getInt("guest_id"));
                if (queryResult.getInt("attended") == 1){
                    attendee.setAttended(true);
                } else {
                attendee.setAttended(false);}

                return attendee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("guest_id", data.get("guestID").toString());
        databaseData.put("event_id", data.get("eventID").toString());
        databaseData.put("attended", "0");
        return databaseInstance.insertInto("attendee", databaseData);
    }

    @Override
    public void delete(int id) {
        databaseInstance.delete("attendee" , "guest_id=" + id);
    }


    /**
     * Exists int.
     *
     * @param data the data
     * @return the int
     */
    public int exists(HashMap data) {
        String where = "";
        where += "guest_id = '"+data.get("guestID")+"' AND ";
        where += "event_id = '"+data.get("eventID") + "';";

        ResultSet resultSet = databaseInstance.select("attendee", where);
        try{
            if(resultSet.isBeforeFirst()) {
                while(resultSet.next()) {
                    return resultSet.getInt("guest_id");
                }
            }
            else {
                return 0;
            }
        }catch(SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }


/*
    public void delete(int guestID, int eventID) {
        databaseInstance.delete("attendee", guestID, eventID);
    }
*/

    /**
     * Update.
     *
     * @param data the data
     */
    public void update(HashMap data) {
        databaseInstance.update("attendee", "guest_id=" + data.get("guest_id") , data);
    }

    @Override
    public void update(int id, HashMap data) {

    }
}

