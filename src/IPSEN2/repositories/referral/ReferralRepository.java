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

    public void create(HashMap data) {

    }

    public void delete(int id) {

    }

    public void update(int id, HashMap data) {

    }
}
