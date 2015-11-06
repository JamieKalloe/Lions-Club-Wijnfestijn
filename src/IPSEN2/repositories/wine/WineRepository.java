package IPSEN2.repositories.wine;

import IPSEN2.database.Database;
import IPSEN2.models.wine.Wine;
import IPSEN2.models.wine.WineMerchant;
import IPSEN2.models.wine.WineType;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 24-9-2015.
 */
public class WineRepository implements Crudable {

    private Database databaseInstance;

    // Constructor creates a DB instance
    public WineRepository() {
        this.databaseInstance = Database.getInstance();
    }

    /**
     * aanmaken van een nieuwe wijn in de database
     *
     * @return
     */
    // ArrayList with all Wine entries
    public ArrayList<Wine> all() {
        ArrayList<Wine> wineList = new ArrayList<Wine>();
        // MySQL Query
        ResultSet queryResult = databaseInstance.select("wine");

        try {
            while(queryResult.next()) {
                // Creates a new wine object
                Wine wine = new Wine();

                // Fill the wine object with data from the query
                wine.setWineID(queryResult.getInt("id"));
                wine.setType(new WineType(queryResult.getInt("type_id")));
                wine.setMerchant(new WineMerchant(queryResult.getInt("merchant_id")));
                wine.setName(queryResult.getString("name"));
                wine.setCountry(queryResult.getString("country"));
                wine.setRegion(queryResult.getString("region"));
                wine.setYear(queryResult.getInt("year"));
                wine.setPrice(queryResult.getDouble("price"));

                // Add the wine object in the ArrayList
                wineList.add(wine);

            }
        // Error handling
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wineList;
    }

    // Searching for a wine based on a ID
    public Wine find(int wine_id) {
        // MySQL query
        ResultSet queryResult = databaseInstance.select("wine", wine_id);
        try {
            while(queryResult.next()) {
               // If he finds a wine, create a new object.
                Wine wine = new Wine();

                // Fill this object with data from the query
                wine.setWineID(queryResult.getInt("id"));
                wine.setType(new WineType(queryResult.getInt("type_id")));
                wine.setName(queryResult.getString("name"));
                wine.setCountry(queryResult.getString("country"));
                wine.setRegion(queryResult.getString("region"));
                wine.setYear(queryResult.getInt("year"));
                wine.setPrice(queryResult.getDouble("price"));

                // Return the wine object.
                return wine;

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;

    }

    /**
     * Aanmaken hashmap, hier worden de kolommen uit de database
     * omgezet naar java variabelen.
     *
     * @param data
     * @return
     */

    public int create(HashMap data) {
        HashMap databaseData = new HashMap();

        databaseData.put("type_id", Integer.parseInt(data.get("typeId").toString()));
        databaseData.put("merchant_id", Integer.parseInt(data.get("merchantId").toString()));
        databaseData.put("name", data.get("name").toString());
        databaseData.put("region", data.get("region").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("year", Integer.parseInt(data.get("year").toString()));
        databaseData.put("price", Double.parseDouble(data.get("price").toString()));

        return databaseInstance.insertInto("wine", databaseData);
    }

    public void update(int id, HashMap data) {
        // Create new DB ready HashMap object.
        HashMap databaseData = new HashMap();

        // Fill the HashMap with data from the WineService
        databaseData.put("type_id", data.get("typeId"));
        databaseData.put("name", data.get("name").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("region", data.get("region"));
        databaseData.put("year", Integer.parseInt(data.get("year").toString()));
        databaseData.put("price", Double.parseDouble(data.get("price").toString()));

        // Update the wine table based on the given HashMap
        databaseInstance.update("wine", id, databaseData);
    }
    // If delete is called, delete the chosen wine based on its ID.
    public void delete(int id) { databaseInstance.delete("wine", id);}
}
