package IPSEN2.database;

import java.util.HashMap;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    private static Database databaseInstance ;

    public static Database getInstance() {
        if(databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    private Database() {
        /*String url = "jdbc:mysql://localhost:3306/";
        String user = "lions_club";
        String password = "root";
        String dbName = "lions_club";*/

        String url = "";
        String user = "";
        String password = "";
        String dbName = "";

        try {
            this.connection = DriverManager.getConnection(url+dbName, user, password);
        } catch(SQLException e) {
            System.out.println(e);
        }
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
