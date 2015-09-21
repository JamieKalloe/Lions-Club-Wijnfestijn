package IPSEN2.database;

import java.util.HashMap;
import com.mysql.jdbc.*;

public class Database {

    private static Database ourInstance = new Database();
    private static String databaseURL = "";
    private static String databaseUser = "";
    private static String databasePassword = "";

    public static Database getInstance() {
        return ourInstance;
    }

    private Database() {

    }

    public Object select(String from) {
        return "";
    }

    public Object select(String from, String where) {
        return "";
    }

    public boolean insertInto(String table, HashMap data) {
        return true;
    }

    public boolean delete(String from, int id) {
        return true;
    }

}
