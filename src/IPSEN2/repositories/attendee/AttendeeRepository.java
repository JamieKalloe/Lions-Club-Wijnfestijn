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

    public AttendeeRepository() {
        databaseInstance = Database.getInstance();
    }

    @Override
    public ArrayList<Attendee> all() {
        return new ArrayList<Attendee>();
    }

    @Override
    public Attendee find(int id) {
        ResultSet queryResult = databaseInstance.select("attendee", id);
        try {
            while (queryResult.next()) {
                Attendee attendee = new Attendee(queryResult.getInt("event_id"));
                attendee.setGuestID(queryResult.getInt("guest_id"));
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

        return databaseInstance.insertInto("attendee", databaseData);
    }

    @Override
    public void delete(int id) {

    }


    public void delete(int guestID, int eventID) {
        databaseInstance.delete("attendee", guestID, eventID);
    }

    @Override
    public void update(int id, HashMap data) {

    }
}

