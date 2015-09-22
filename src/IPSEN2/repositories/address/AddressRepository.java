package IPSEN2.repositories.address;

import IPSEN2.database.Database;
import IPSEN2.models.address.Address;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class AddressRepository implements Crudable {
    private Database databaseInstance;

    public AddressRepository() {
        databaseInstance = Database.getInstance();
    }

    public ArrayList<Address> all() {
        return new ArrayList<Address>();
    }
    public Address find(int id) {
        ResultSet queryResult = databaseInstance.select("address", id);
        try {
            while(queryResult.next()) {
                Address address = new Address(queryResult.getInt("id"));
                address.setStreet(queryResult.getString("street"));
                address.setHouseNumber(queryResult.getString("house_number"));
                address.setZipCode(queryResult.getString("zipcode"));
                address.setCity(queryResult.getString("city"));
                address.setCountry(queryResult.getString("country"));
                return address;
            }
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void create(HashMap data) {

    }

    public void update(int id, HashMap data) {

    }

    public void delete(int id) {

    }
}
