package IPSEN2.repositories.wine;

import IPSEN2.database.Database;
import IPSEN2.models.winetype.WineType;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bernd on 7-10-2015.
 */
public class WineTypeRepository implements Crudable{
    private Database databaseInstance;

    public WineTypeRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<WineType> all() {
        return new ArrayList<>();
    }

    public WineType find(int id) {
        ResultSet resultSet = databaseInstance.select("wine_type", id);
        try {
            while (resultSet.next()) {
                return new WineType(resultSet.getInt("id"), resultSet.getString("name"));
            }

        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("name", data.get("typeName").toString());

        return databaseInstance.insertInto("wine_type", databaseData);

    }

    public void delete(int id) {
        databaseInstance.delete("wine_type", id);
    }

    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("name", data.get("typeName").toString());

        databaseInstance.update("wine_type", id, databaseData);
    }

    public int exists(HashMap data) {
        String where = "name = '" + data.get("typeName")+"'";
        ResultSet resultSet = databaseInstance.select("wine_type", where);
        try{
            if(resultSet.isBeforeFirst()) {
            while(resultSet.next()) {
                    return resultSet.getInt("id");
                }
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;

    }

}
