package IPSEN2.repositories.guest;

import IPSEN2.database.Database;
import IPSEN2.models.address.Address;
import IPSEN2.models.guest.Guest;
import IPSEN2.models.referral.Referral;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestRepository implements Crudable {

    private Database databaseInstance;

    public GuestRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<Guest> all() {
        ArrayList<Guest> guestList = new ArrayList<Guest>();
        ResultSet queryResult = databaseInstance.select("guest");
        try {
            while(queryResult.next()) {
                Guest guest = new Guest();
                guest.setGuestID(queryResult.getInt("id"));
                guest.setFirstName(queryResult.getString("first_name"));
                guest.setPrefix(queryResult.getString("prefix_last_name"));
                guest.setLastName(queryResult.getString("last_name"));
                guest.setEmail(queryResult.getString("email"));
                guest.setNotes(queryResult.getString("notes"));
                guest.setGender(queryResult.getString("gender"));
                guest.setAddress(new Address(queryResult.getInt("address_id")));
                guest.setReferral(new Referral(queryResult.getInt("referral_id")));

                guestList.add(guest);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return guestList;
    }

    public Guest find(int id) {
        ResultSet queryResult = databaseInstance.select("guest", id);
        try {
            while(queryResult.next()) {
                Guest guest = new Guest();
                guest.setGuestID(queryResult.getInt("id"));
                guest.setFirstName(queryResult.getString("first_name"));
                guest.setPrefix(queryResult.getString("prefix_last_name"));
                guest.setLastName(queryResult.getString("last_name"));
                guest.setEmail(queryResult.getString("email"));
                guest.setNotes(queryResult.getString("notes"));
                guest.setGender(queryResult.getString("gender"));
                guest.setAddress(new Address(queryResult.getInt("address_id")));
                guest.setReferral(new Referral(queryResult.getInt("referral_id")));

                return guest;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println(sqle.getSQLState());
            return null;
        }
        return null;
    }

    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("address_id", Integer.parseInt(data.get("addressID").toString()));
        databaseData.put("referral_id", Integer.parseInt(data.get("referralID").toString()));
        databaseData.put("email", data.get("email").toString());
        databaseData.put("first_name", data.get("firstname").toString());
        databaseData.put("last_name", data.get("lastname").toString());
        databaseData.put("prefix_last_name", data.get("prefix").toString());
        databaseData.put("gender", data.get("gender").toString());
        databaseData.put("notes", data.get("notes").toString());

       return databaseInstance.insertInto("guest", databaseData);
    }

    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("address_id", Integer.parseInt(data.get("addressID").toString()));
        databaseData.put("referral_id", Integer.parseInt(data.get("referralID").toString()));
        databaseData.put("email", data.get("email").toString());
        databaseData.put("first_name", data.get("firstname").toString());
        databaseData.put("last_name", data.get("lastname").toString());
        databaseData.put("prefix_last_name", data.get("prefix").toString());
        databaseData.put("gender", data.get("gender").toString());
        databaseData.put("notes", data.get("notes").toString());

        databaseInstance.update("guest", id, databaseData);
    }

    public void delete(int id) {
        databaseInstance.delete("guest", id);
    }
}
