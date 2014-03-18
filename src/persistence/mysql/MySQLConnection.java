package persistence.mysql;

import java.sql.*;
import java.util.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class MySQLConnection {
    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String password;
    private Connection connection;

    protected MySQLConnection() {
        url = "jdbc:mysql://mysql.stud.ntnu.no/";
        dbName = "simonbo_exphil03_test";
        driver = "com.mysql.jdbc.Driver";
        userName = "simonbo_exphil03";
        password = "drossap";
    }

    protected MySQLConnection(String url, String dbName, String driver, String userName, String password) {
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    protected ArrayList<HashMap<String, String>> execute(String sql, ArrayList<String> values, boolean returns) {
        try{
            connect();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if(values != null){
                for(int i=0; i<values.size(); i++){
                    preparedStatement.setString(i+1, values.get(i));
                }
            }

            ArrayList<HashMap<String, String>> results = null;

            if(returns) results = getResults(preparedStatement.executeQuery());
            else preparedStatement.executeUpdate();

            close();

            return results;
        }catch (MySQLIntegrityConstraintViolationException ex){
            throw new IllegalArgumentException("Could not edit database. " + ex.getMessage());
        }catch (SQLException ex){
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private ArrayList<HashMap<String, String>> getResults(ResultSet resultSet) throws SQLException{
        ArrayList<HashMap<String, String>> results = new ArrayList<>();

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columns = metaData.getColumnCount();

        while(resultSet.next()){
            HashMap<String, String> map = new HashMap<>();

            for(int i = 1; i<=columns; i++){
                map.put(metaData.getColumnName(i), resultSet.getString(i));
            }

            results.add(map);
        }

        return results;
    }

    private void connect() {
        try{
            Class.forName(driver).newInstance();

            connection = DriverManager.getConnection(url + dbName, userName, password);
        }catch (SQLException e){
            throw new IllegalArgumentException("Could not connect to database.");
        }catch (Exception e){
            throw new IllegalArgumentException("Class not found.");
        }
    }

    private void close() throws SQLException {
        connection.close();
        connection = null;
    }
}
