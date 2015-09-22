package IPSEN2.repositories;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mdbaz on 21-09-2015.
 */
public interface Crudable {
    Object all();
    Object find(int id);
    int create(HashMap data);
    void delete(int id);
    void update(int id, HashMap data);

}
