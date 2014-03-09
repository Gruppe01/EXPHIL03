package persistence;

import java.sql.*;
import java.util.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.apache.commons.lang.StringUtils;

public class MySQLConnection {
    private String url;
    private String dbName;
    private String driver;
    private String userName;
    private String password;

    private Connection conn;
    //private Statement statement;
    private PreparedStatement preparedStatement;
    //private ResultSet resultSet;

    public MySQLConnection() {
        url = "jdbc:mysql://mysql.stud.ntnu.no/";
        dbName = "simonbo_exphil03";
        driver = "com.mysql.jdbc.Driver";
        userName = "simonbo_exphil03";
        password = "drossap";

        HashMap<String, String> updateMap = new HashMap<>();
        HashMap<String, String> deleteMap = new HashMap<>();

        updateMap.put("username", "admin");
        deleteMap.put("username", "simoms");

        insert("User", Arrays.asList("username", "password", "email", "firstName", "lastName"), Arrays.asList("admin", "admin", "admin@exphil03.com", "admin", "admin"));
        update("User", Arrays.asList("username", "password", "email", "firstName", "lastName"), Arrays.asList("simoms", "drossap", "simoms@exphil03.com", "b-j", "simon"), updateMap);
        delete("User", deleteMap);
    }

    public MySQLConnection(String url, String dbName, String driver, String userName, String password){
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    public void executeUpdate(String query, List<String> values) {
        try{
            connect();

            System.out.println(query);
            System.out.println(values);

            preparedStatement = conn.prepareStatement(query);

            for(int i=0; i<values.size(); i++){
                preparedStatement.setString(i+1, values.get(i));
            }

            preparedStatement.executeUpdate();

            close();
        }catch (MySQLIntegrityConstraintViolationException ex){
            throw new IllegalArgumentException("Could not edit database. " + ex.getMessage());
        }catch (SQLException ex){
            throw new IllegalArgumentException("Could not connect to database.");
        }
    }

    public void insert(String table, List<String> fields, List<String> values) {
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String fieldsString = StringUtils.join(fields, ", ");
        String valuesString = "?";

        for(int i = 1; i<values.size(); i++){
            valuesString += ", ?";
        }

        String insertSQL = "INSERT INTO  " + table + " (" + fieldsString + ") VALUES (" + valuesString + ");";

        executeUpdate(insertSQL, values);
    }

    @SuppressWarnings("unchecked")
    public void update(String table, List<String> fields, List<String> values, HashMap conditions){
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String setString = StringUtils.join(fields, " = ?, ") + " = ?";
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String updateSQL = "UPDATE " + table + " SET " + setString + " WHERE " + whereString + ";";

        ArrayList<String> queryValues = new ArrayList<>();

        queryValues.addAll(values);
        queryValues.addAll(conditions.values());

        executeUpdate(updateSQL, queryValues);
    }

    @SuppressWarnings("unchecked")
    public void delete(String table, HashMap conditions) {
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String deleteSQL = "DELETE FROM " + table + " WHERE " + whereString + ";";

        List<String> queryValues = new ArrayList<String>(conditions.values());

        executeUpdate(deleteSQL, queryValues);
    }

    private void connect() throws SQLException{
        try{
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url + dbName, userName, password);
        }catch (Exception e){
            throw new IllegalArgumentException("Class not found.");
        }
    }

    private void close() throws SQLException {
        conn.close();
    }

    public static void main(String args[]) {
        MySQLConnection connectionn = new MySQLConnection();
    }
}
