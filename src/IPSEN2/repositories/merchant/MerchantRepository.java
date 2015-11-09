package IPSEN2.repositories.merchant;

import IPSEN2.database.Database;
import IPSEN2.models.merchant.Merchant;
import IPSEN2.repositories.Crudable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Philip on 02-11-15.
 */
public class MerchantRepository implements Crudable {

    private Database databaseInstance;

    /**
     * Instantiates a new Merchant repository.
     */
    public MerchantRepository()
    {
        this.databaseInstance = Database.getInstance();
    }

    @Override
    public ArrayList<Merchant> all() {
        ArrayList<Merchant> merchants = new ArrayList<>();

        ResultSet queryResult = databaseInstance.select("wine_merchant");

        try
        {
            while (queryResult.next())
            {
                Merchant merchant = new Merchant(
                        queryResult.getInt("id"),
                        queryResult.getString("name"),
                        queryResult.getString("email"),
                        queryResult.getInt("address_id")

                );

                merchants.add(merchant);
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return merchants;
    }

    @Override
    public Merchant find(int id) {
        ResultSet queryResult = databaseInstance.select("wine_merchant", id);

        try {
            while (queryResult.next())
            {
                Merchant merchant = new Merchant(
                        queryResult.getInt("id"),
                        queryResult.getString("name"),
                        queryResult.getString("email"),
                        queryResult.getInt("address_id")
                );

                return merchant;
            }
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }

        return null;
    }

    @Override
    public int create(HashMap data) {
        HashMap databaseData = new HashMap();

        databaseData.put("address_id", Integer.parseInt(data.get("addressID").toString()));
        databaseData.put("name", data.get("name").toString());
        databaseData.put("email", data.get("email").toString());

        return databaseInstance.insertInto("wine_merchant", databaseData);
    }

    @Override
    public void delete(int id) {
        databaseInstance.delete("wine_merchant", id);
    }

    @Override
    public void update(int id, HashMap data) {
        HashMap databaseData = new HashMap();
        databaseData.put("address_id", Integer.parseInt(data.get("addressID").toString()));
        databaseData.put("name", data.get("name"));
        databaseData.put("email", data.get("email"));

        databaseInstance.update("wine_merchant", id, databaseData);
    }
}
