package persistence;

import java.sql.*;
import java.util.List;

public class MySQLConnection {
    public static final String url = "jdbc:mysql://mysql.stud.ntnu.no/";
    public static final String dbName = "simonbo_exphil03";
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String userName = "simonbo_exphil03";
    public static final String password = "drossap";

    private Connection conn;

    public void insert(String table, List<String> fields, List<String> values) {

    }

    public void update(String table, List<String> fields, List<String> values) {

    }

    public void delete(String table, List<String> fields, List<String> values) {

    }

    public void connect() throws IllegalArgumentException{
        try{
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
        }catch (Exception e){
            throw new IllegalArgumentException("Could not connect to database.");
        }
    }

    public void close() throws IllegalArgumentException{
        try{
            conn.close();
        }catch (Exception e){
            throw new IllegalArgumentException("Could not disconnect from database.");
        }
    }
}
