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

    public WineRepository() {
        this.databaseInstance = Database.getInstance();
    }

    /**
     * aanmaken van een nieuwe wijn in de database
     *
     * @return
     */
    public ArrayList<Wine> all() {
        ArrayList<Wine> wineList = new ArrayList<Wine>();
        ResultSet queryResult = databaseInstance.select("wine");

        try {
            while(queryResult.next()) {
                Wine wine = new Wine();
                wine.setWineID(queryResult.getInt("id"));
                wine.setType(new WineType(queryResult.getInt("type_id")));
                wine.setMerchant(new WineMerchant(queryResult.getInt("merchant_id")));
                wine.setName(queryResult.getString("name"));
                wine.setCountry(queryResult.getString("country"));
                wine.setRegion(queryResult.getString("region"));
                wine.setYear(queryResult.getInt("year"));
                wine.setPurchasePrice(queryResult.getDouble("purchase_price"));
                wine.setPrice(queryResult.getDouble("price"));

                wineList.add(wine);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wineList;
    }

    /**
     * zoeken van een wijn in de database
     * aan de hand van een id.
     *
     * @param wine_id
     * @return
     */
    public Wine find(int wine_id) {
        ResultSet queryResult = databaseInstance.select("wine", wine_id);
        try {
            while(queryResult.next()) {
                Wine wine = new Wine();

                wine.setWineID(queryResult.getInt("id"));
                wine.setType(new WineType(queryResult.getInt("type_id")));
                wine.setName(queryResult.getString("name"));
                wine.setCountry(queryResult.getString("country"));
                wine.setRegion(queryResult.getString("region"));
                wine.setYear(queryResult.getInt("year"));
                wine.setPurchasePrice(queryResult.getDouble("purchase_price"));
                wine.setPrice(queryResult.getDouble("price"));

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
        databaseData.put("type_id", Integer.parseInt(data.get("type_id").toString()));
        databaseData.put("merchant_id", 1);
        databaseData.put("name", data.get("name").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("region", data.get("region"));
        databaseData.put("year", Integer.parseInt(data.get("year").toString()));
        databaseData.put("purchase_price", 12.0);
        databaseData.put("price", Double.parseDouble(data.get("price").toString()));

        return databaseInstance.insertInto("wine", databaseData);

    }

    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("type_id", Integer.parseInt(data.get("type").toString()));
        databaseData.put("name", data.get("name").toString());
        databaseData.put("country", data.get("country").toString());
        databaseData.put("region", data.get("region"));
        databaseData.put("year", Integer.parseInt(data.get("year").toString()));
        databaseData.put("purchase_price", Double.parseDouble(data.get("purchasePrice").toString()));
        databaseData.put("price", Double.parseDouble(data.get("price").toString()));

        databaseInstance.update("wine", id, databaseData);

    }
    public void delete(int id) { databaseInstance.delete("guest", id);}
}
