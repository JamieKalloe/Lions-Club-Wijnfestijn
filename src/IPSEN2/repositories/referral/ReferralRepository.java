package IPSEN2.repositories.referral;

import IPSEN2.database.Database;
import IPSEN2.models.referral.Referral;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 22-09-2015.
 */
public class ReferralRepository implements Crudable{
    private Database databaseInstance;

    /**
     * Instantiates a new Referral repository.
     */
    public ReferralRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<Referral> all() {
        return new ArrayList<>();
    }

    public Referral find(int id) {
        ResultSet resultSet = databaseInstance.select("referral", id);
        try {
            while(resultSet.next()) {
                return new Referral(resultSet.getInt("id"), resultSet.getString("name"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public int create(HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("name", data.get("referralName").toString());

        return databaseInstance.insertInto("referral", databaseData);
    }

    public void delete(int id) {
        databaseInstance.delete("referral", id);
    }

    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("name", data.get("referralName").toString());

        databaseInstance.update("referral", id, databaseData);
    }

    /**
     * Exists int.
     *
     * @param data the data
     * @return the int
     */
    public int exists(HashMap data) {
        String where = "name = '" + data.get("referralName")+"'";
        ResultSet resultSet = databaseInstance.select("referral", where);
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
