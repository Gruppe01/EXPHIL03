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

    public MySQLConnection() {
        url = "jdbc:mysql://mysql.stud.ntnu.no/";
        dbName = "simonbo_exphil03";
        driver = "com.mysql.jdbc.Driver";
        userName = "simonbo_exphil03";
        password = "drossap";
    }

    public MySQLConnection(String url, String dbName, String driver, String userName, String password) {
        this.url = url;
        this.dbName = dbName;
        this.driver = driver;
        this.userName = userName;
        this.password = password;
    }

    private ArrayList<HashMap<String, String>> execute(String type, String sql, ArrayList<String> values) {
        try {
            switch (type){
                case "update":
                    executeUpdate(sql, values);
                    return null;
                case "query":
                    return executeQuery(sql, values);
                default:
                    return null;
            }
        }catch (SQLException ex){
            throw new IllegalArgumentException("Could not disconnect from database.");
        }
    }

    private void executeUpdate(String sql, ArrayList<String> values) throws SQLException {
        try{
            connect();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            for(int i=0; i<values.size(); i++){
                preparedStatement.setString(i+1, values.get(i));
            }

            preparedStatement.executeUpdate();

            close();
        }catch (MySQLIntegrityConstraintViolationException ex){
            throw new IllegalArgumentException("Could not edit database. " + ex.getMessage());
        }
    }

    private ArrayList<HashMap<String, String>> executeQuery(String sql, ArrayList<String> values) throws SQLException {
        connect();

        PreparedStatement preparedStatement = conn.prepareStatement(sql);

        for(int i=0; i<values.size(); i++){
            preparedStatement.setString(i+1, values.get(i));
        }

        ArrayList<HashMap<String, String>> results = getResults(preparedStatement.executeQuery());

        close();

        return results;
    }

    public void insert(String table, ArrayList<String> fields, ArrayList<String> values) {
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String fieldsString = StringUtils.join(fields, ", ");
        String valuesString = "?";

        for(int i = 1; i<values.size(); i++){
            valuesString += ", ?";
        }

        String insertSQL = "INSERT INTO  " + table + " (" + fieldsString + ") VALUES (" + valuesString + ");";

        execute("update", insertSQL, values);
    }

    @SuppressWarnings("unchecked")
    public void update(String table, ArrayList<String> fields, ArrayList<String> values, HashMap conditions){
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String setString = StringUtils.join(fields, " = ?, ") + " = ?";
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String updateSQL = "UPDATE " + table + " SET " + setString + " WHERE " + whereString + ";";

        ArrayList<String> queryValues = new ArrayList<>();

        queryValues.addAll(values);
        queryValues.addAll(conditions.values());

        execute("update", updateSQL, queryValues);
    }

    @SuppressWarnings("unchecked")
    public void delete(String table, HashMap conditions) {
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String deleteSQL = "DELETE FROM " + table + " WHERE " + whereString + ";";

        ArrayList<String> queryValues = new ArrayList<String>(conditions.values());

        execute("update", deleteSQL, queryValues);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap<String, String>> select(ArrayList<String> tables, ArrayList<String> fields, HashMap conditions){
        String tablesString = StringUtils.join(tables, ", ");
        String fieldsString = StringUtils.join(fields, ", ");
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String selectSQL = "SELECT " + fieldsString + " FROM " + tablesString + " WHERE " + whereString + ";";

        ArrayList<String> queryValues = new ArrayList<String>(conditions.values());

        return execute("query", selectSQL, queryValues);
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

    public void connect(){
        try{
            Class.forName(driver).newInstance();

            conn = DriverManager.getConnection(url + dbName, userName, password);
        }catch (SQLException e){
            throw new IllegalArgumentException("Could not connect to database.");
        }catch (Exception e){
            throw new IllegalArgumentException("Class not found.");
        }
    }

    public void close() throws SQLException {
        conn.close();
        conn = null;
    }
}
