package IPSEN2.repositories.guest;

import IPSEN2.database.Database;
import IPSEN2.models.guest.Guest;
import IPSEN2.repositories.Crudable;
import java.util.ArrayList;
import java.util.HashMap;

public class GuestRepository implements Crudable {

    private Database databaseInstance;

    public GuestRepository() {
        this.databaseInstance = Database.getInstance();
    }

    public ArrayList<Guest> all() {
        return new ArrayList<Guest>();
    }

    public Guest find(int id) {
        return new Guest();
    }

    public void create(HashMap data) {

    }

    public void update(int id, HashMap data) {

    }

    public void delete(int id) {

    }
}
