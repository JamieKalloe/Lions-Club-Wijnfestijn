package IPSEN2.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Set;

public class Database {


    private Connection connection;
    private Statement statement;

    private static Database databaseInstance ;

    public static synchronized Database getInstance() {
        if(databaseInstance == null) {
            databaseInstance = new Database();
        }
        return databaseInstance;
    }

    private Database() {
        //Mike's DB settings
        String url = "jdbc:mysql://localhost:3306/";
        String user = "lions_club";
        String password = "root";
        String dbName = "lions_club";

        /*String url = "jdbc:mysql://127.0.0.1:3306/";
        String user = "root";
        String password = "";
        String dbName = "lions_club";*/

       /* String url = "";
        String user = "";
        String password = "";
        String dbName = "";*/


        try {
            this.connection = DriverManager.getConnection(url + dbName, user, password);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public ResultSet select(String from) {
        String query = "SELECT * FROM `" + from+"`";
        ResultSet resultSet = queryDatabase(query);
        return resultSet;
    }

    public ResultSet select(String from, String where) {
        String query = "SELECT * FROM `" + from + "` WHERE " + where;
        ResultSet resultSet = queryDatabase(query);
        return resultSet;
    }

    public ResultSet select(String from, int id) {
        String query = "SELECT * FROM `" + from + "` WHERE id=" + id;
        ResultSet resultSet = queryDatabase(query);
        return resultSet;
    }

    public ResultSet select(String from, String foreignTable, String resultColumn, String filterColumn,String where) {
        String query = "SELECT * FROM `" + from + "` WHERE "+resultColumn+" IN ( SELECT "+filterColumn+" FROM "+foreignTable+ "` WHERE " + where +" )";
        ResultSet resultSet = queryDatabase(query);
        return resultSet;
    }

    public int insertInto(String table, HashMap data) {
        String queryTable = "INSERT INTO `" + table + "` (";
        String queryValues = ") VALUES(";
        Set keySet = data.keySet();
        Object[] keyArray = keySet.toArray();
        for(int index = 0; index < keyArray.length; index++) {
            queryTable += ""+keyArray[index].toString()+"";
            if(data.get(keyArray[index])  instanceof  String ) {
                queryValues += "'"+data.get(keyArray[index]).toString()+"'";
            }
            else {
                queryValues += data.get(keyArray[index]).toString();
            }
            if(index != keyArray.length - 1) {
                queryTable += ", ";
                queryValues += ", ";
            }
        }
        System.out.println(queryTable + queryValues + ")");
        int result = updateDatabase(queryTable + queryValues + ")", Statement.RETURN_GENERATED_KEYS);

        return result;
    }

    public int update(String table, int id, HashMap data) {
        String query = "UPDATE `" + table + "` SET ";
        Set keySet = data.keySet();
        Object[] keyArray = keySet.toArray();
        for(int index = 0; index < keyArray.length; index++) {
            query += keyArray[index].toString()+"=";
            if(data.get(keyArray[index]) instanceof String ) {
                query += "'"+data.get(keyArray[index])+"'";
            }
            else {
                query += data.get(keyArray[index]).toString();
            }
            if(index != keyArray.length - 1) {
                query += ", ";
            }
        }
        query += " WHERE id="+id;
        int result = updateDatabase(query);
        return result;
    }

    public int delete(String from, int id) {
        String query = "DELETE FROM `"+from+"` WHERE id="+id;
        int result = updateDatabase(query);
        return result;
    }

    public int delete(String from, String where) {
        String query = "DELETE FROM `"+from+"` WHERE "+where;
        return updateDatabase(query);
    }

    private ResultSet queryDatabase(String query) {
        try {
            statement = databaseInstance.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet;
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private int updateDatabase(String updateQuery) {
        try {
            statement = databaseInstance.connection.createStatement();
            int result = statement.executeUpdate(updateQuery);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    private int updateDatabase(String updateQuery, int settings) {
        try {
            statement = databaseInstance.connection.createStatement();
            int result = statement.executeUpdate(updateQuery, settings);
            try {
                ResultSet resultSet = statement.getGeneratedKeys();
                while(resultSet.next()) {
                    return resultSet.getInt(1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
