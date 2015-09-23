package IPSEN2.repositories.address;

import IPSEN2.database.Database;
import IPSEN2.models.address.Address;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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

    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("zipcode",data.get("zipCode").toString());
        databaseData.put("street", data.get("street").toString());
        databaseData.put("house_number", data.get("houseNumber").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("city", data.get("city").toString());

       return databaseInstance.insertInto("address", databaseData);
    }

    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("zipcode",data.get("zipCode").toString());
        databaseData.put("street", data.get("street").toString());
        databaseData.put("house_number", data.get("houseNumber").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("city", data.get("city").toString());

        databaseInstance.update("address", id, databaseData);
    }

    public void delete(int id) {
        databaseInstance.delete("address", id);
    }

    public int exists(HashMap data) {
        String where = "";
        where += "zipcode = '"+data.get("zipCode")+"' AND ";
        where += "street = '"+data.get("street")+"' AND ";
        where += "house_number = '"+data.get("houseNumber")+"' AND ";
        where += "country = '"+data.get("country")+"' AND ";
        where += "city = '"+data.get("city")+"'";
        ResultSet resultSet = databaseInstance.select("address", where);
        try{
            if(resultSet.isBeforeFirst()) {
                while(resultSet.next()) {
                    return resultSet.getInt("id");
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
}
