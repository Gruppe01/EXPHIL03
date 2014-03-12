package persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class MySQLConnectionTest {
    MySQLConnection validConnection;
    MySQLConnection invalidConnection;

    String username;

    HashMap<String, String> usernameMap;
    HashMap<String, String> selectMap;

    ArrayList<String> fields;
    ArrayList<String> valuesBefore;
    ArrayList<String> valuesAfter;

    @Before
    public void setUp() throws Exception {
        validConnection = new MySQLConnection();

        username = "testUser" + Integer.toString(new Random().nextInt(99999));

        usernameMap = new HashMap<>(); usernameMap.put("username", username);
        selectMap = new HashMap<>(); selectMap.put("username", username);

        fields = new ArrayList(Arrays.asList("username", "password", "email", "name"));
        valuesBefore = new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123 user123"));
        valuesAfter = new ArrayList(Arrays.asList(username, "password", "testUser@exphil03.com", "test user"));
    }

    @After
    public void tearDown() throws Exception {
        testDelete();

        validConnection = null;
        invalidConnection = null;

        username = null;

        usernameMap = null;
        selectMap = null;

        fields = null;
        valuesBefore = null;
        valuesAfter = null;
    }

    @Test
    public void testMySQL() throws Exception{
        testInsert();
        testUpdate();
        testSelect();
        testDelete();
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidDriver() throws Exception {
        String url = "jdbc:mysql://mysql.stud.ntnu.no/";
        String dbName = "simonbo_exphil03";
        String driver = "com.mysql.jdbc.Driverr";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLConnection(url, dbName, driver, userName, password);

        select(invalidConnection, selectMap);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidUrl() throws Exception{
        String url = "jdbc:mysql://mysql.stud.ntnu.nop/";
        String dbName = "simonbo_exphil03";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLConnection(url, dbName, driver, userName, password);

        select(invalidConnection, selectMap);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDuplicateEntries() throws Exception{
        validConnection.insert("User", fields, valuesBefore);
        validConnection.insert("User", fields, valuesBefore);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSQLException() throws Exception{
        selectMap = new HashMap<>(); selectMap.put("usrname", username);

        select(validConnection, selectMap);
    }

    private ArrayList<HashMap<String, String>> select(MySQLConnection connection, HashMap<String, String> map) throws Exception {
        return connection.select(new ArrayList(Arrays.asList("User")), fields, map, new ArrayList(Arrays.asList("")));
    }

    public void testInsert() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password123");
        selectMap.put("email", "testUser123@exphil03.com");
        selectMap.put("name", "test123 user123");

        validConnection.insert("User", fields, valuesBefore);

        ArrayList<HashMap<String, String>> results = select(validConnection, selectMap);

        assertEquals(results.size(), 1);
    }

    public void testUpdate() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password");
        selectMap.put("email", "testUser@exphil03.com");
        selectMap.put("name", "test user");

        validConnection.update("User", fields, valuesAfter, usernameMap, new ArrayList(Arrays.asList("")));

        ArrayList<HashMap<String, String>> results = select(validConnection, selectMap);

        assertEquals(results.size(), 1);
    }

    public void testSelect() throws Exception {
        ArrayList<HashMap<String, String>> results = select(validConnection, selectMap);

        Iterator<String> iterator = results.get(0).values().iterator();

        assertEquals(iterator.next(), username);
        assertEquals(iterator.next(), "testUser@exphil03.com");
        assertEquals(iterator.next(), "test user");
        assertEquals(iterator.next(), "password");
    }

    public void testDelete() throws Exception {
        validConnection.delete("User", usernameMap);

        ArrayList<HashMap<String, String>> result = select(validConnection, usernameMap);

        assertEquals(0, result.size());
    }
}
