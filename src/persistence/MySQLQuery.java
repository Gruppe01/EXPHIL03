package persistence;

import org.apache.commons.lang.StringUtils;

import java.util.*;

public class MySQLQuery {
    MySQLConnection connection;

    public MySQLQuery(){
        connection = new MySQLConnection();
    }

    public MySQLQuery(String url, String dbName, String driver, String userName, String password){
        connection = new MySQLConnection(url, dbName, driver, userName, password);
    }

    protected ArrayList<HashMap<String, String>> execute(String sql, ArrayList<String> values, boolean returns){
        return connection.execute(sql, values, returns);
    }

    public void insert(String table, ArrayList<String> fields, ArrayList<String> values) {
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String fieldsString = StringUtils.join(fields, ", ");
        String valuesString = "?";

        for(int i = 1; i<values.size(); i++){
            valuesString += ", ?";
        }

        String insertSQL = "INSERT INTO  " + table + " (" + fieldsString + ") VALUES (" + valuesString + ");";

        connection.execute(insertSQL, values, false);
    }

    @SuppressWarnings("unchecked")
    public void update(String table, ArrayList<String> fields, ArrayList<String> values, HashMap conditions, ArrayList<String> selection) {
        if(fields.size() != values.size()) throw new IllegalArgumentException("The number of fields and values must correspond.");

        String setString = StringUtils.join(fields, " = ?, ") + " = ?";
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";
        String selectionString = StringUtils.join(selection, " ");

        String updateSQL = "UPDATE " + table + " SET " + setString + " WHERE " + whereString + " " + selectionString + ";";

        ArrayList<String> queryValues = new ArrayList<>();

        queryValues.addAll(values);
        queryValues.addAll(conditions.values());

        connection.execute(updateSQL, queryValues, false);
    }

    @SuppressWarnings("unchecked")
    public void delete(String table, HashMap conditions) {
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";

        String deleteSQL = "DELETE FROM " + table + " WHERE " + whereString + ";";

        ArrayList<String> queryValues = new ArrayList<String>(conditions.values());

        connection.execute(deleteSQL, queryValues, false);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<HashMap<String, String>> select(ArrayList<String> tables, ArrayList<String> fields, HashMap conditions, ArrayList<String> selection) {
        String tablesString = StringUtils.join(tables, ", ");
        String fieldsString = StringUtils.join(fields, ", ");
        String whereString = StringUtils.join(conditions.keySet(), " = ? AND ") + " = ?";
        String selectionString = StringUtils.join(selection, " ");

        String selectSQL = "SELECT " + fieldsString + " FROM " + tablesString + " WHERE " + whereString + " " + selectionString + ";";

        ArrayList<String> queryValues = new ArrayList<String>(conditions.values());

        return connection.execute(selectSQL, queryValues, true);
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Integer> getAvailableRooms(String startTime, String endTime, int capacity){
        String query = "SELECT * FROM MeetingRoom WHERE roomNumber NOT IN (SELECT room FROM Meeting WHERE NOT (endTime <= ? OR startTime >= ?)) AND capacity >= ? ORDER BY roomNumber ASC";

        ArrayList<HashMap<String, String>> roomsHashMap = connection.execute(query, new ArrayList(Arrays.asList(startTime, endTime, Integer.toString(capacity))), true);
        ArrayList<Integer> rooms = new ArrayList<>();

        for(HashMap<String, String> room : roomsHashMap){
            rooms.add(Integer.parseInt(room.get("roomNumber")));
        }

        return rooms;
    }

    @SuppressWarnings("unchecked")
    public int getNextID(String table){
        ArrayList<HashMap<String, String>> tableInfo = execute("SHOW TABLE STATUS LIKE ?;", new ArrayList(Arrays.asList(table)), true);
        System.out.println(tableInfo);
        return Integer.parseInt(tableInfo.get(0).get("AUTO_INCREMENT"));
    }
}