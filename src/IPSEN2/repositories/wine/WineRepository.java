package IPSEN2.repositories.wine;

import IPSEN2.database.Database;
import IPSEN2.models.wine.Wine;
import IPSEN2.models.wine.WineType;
import IPSEN2.repositories.Crudable;
import IPSEN2.repositories.guest.GuestRepository;

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
                wine.setWineID(queryResult.getInt("wine_id"));
                wine.setType(new WineType(queryResult.getInt("type_id")));
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



    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("wine_id", Integer.parseInt(data.get("ID").toString()));
        databaseData.put("t")

    }
}
