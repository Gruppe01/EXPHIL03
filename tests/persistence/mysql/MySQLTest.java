package persistence.mysql;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import persistence.mysql.MySQLQuery;

import static org.junit.Assert.*;

import java.util.*;

@SuppressWarnings("unchecked")
public class MySQLTest {
    MySQLQuery validConnection;
    MySQLQuery invalidConnection;

    String username;

    HashMap<String, String> usernameMap;
    HashMap<String, String> selectMap;

    ArrayList<String> userFields;
    ArrayList<String> userValuesBefore;
    ArrayList<String> userValuesAfter;

    @Before
    public void setUp() throws Exception {
        validConnection = new MySQLQuery();

        username = newTestUser();

        usernameMap = new HashMap<>(); usernameMap.put("username", username);
        selectMap = new HashMap<>(); selectMap.put("username", username);

        userFields = new ArrayList(Arrays.asList("username", "password", "email", "name"));
        userValuesBefore = new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123 user123"));
        userValuesAfter = new ArrayList(Arrays.asList(username, "password", "testUser@exphil03.com", "test user"));
    }

    @After
    public void tearDown() throws Exception {
        deleteAll();

        validConnection = null;
        invalidConnection = null;

        username = null;

        usernameMap = null;
        selectMap = null;

        userFields = null;
        userValuesBefore = null;
        userValuesAfter = null;
    }

    @Test
    public void testMySQL() throws Exception{
        testInsert();
        testUpdate();
        testSelect();
        testDelete();
    }

    @Test
    public void testGetAvailableRooms() throws Exception{
        validConnection.execute("ALTER TABLE MeetingRoom AUTO_INCREMENT = 1", new ArrayList<String>(), false);
        validConnection.execute("ALTER TABLE Meeting AUTO_INCREMENT = 1", new ArrayList<String>(), false);

        validConnection.execute("INSERT INTO MeetingRoom (capacity) VALUES (10);", new ArrayList<String>(), false);
        validConnection.execute("INSERT INTO MeetingRoom (capacity) VALUES (10);", new ArrayList<String>(), false);
        validConnection.execute("INSERT INTO MeetingRoom (capacity) VALUES (5);", new ArrayList<String>(), false);

        validConnection.execute("INSERT INTO User (username, password, name, email) VALUES ('testUserRoom', '', '', '');", new ArrayList<String>(), false);

        validConnection.execute("INSERT INTO Meeting (room, startTime, endTime, creator) VALUES (1, '2014-03-14 09:00:00', '2014-03-14 11:00:00', 'testUserRoom');", new ArrayList<String>(), false);
        validConnection.execute("INSERT INTO Meeting (room, startTime, endTime, creator) VALUES (2, '2014-03-14 11:00:00', '2014-03-14 14:00:00', 'testUserRoom');", new ArrayList<String>(), false);
        validConnection.execute("INSERT INTO Meeting (room, startTime, endTime, creator) VALUES (3, '2014-03-14 09:00:00', '2014-03-14 11:00:00', 'testUserRoom');", new ArrayList<String>(), false);

        assertEquals(new ArrayList(Arrays.asList(2)), validConnection.getAvailableRooms("2014-03-14 08:00:00", "2014-03-14 10:00:00", 7));
        assertEquals(new ArrayList(Arrays.asList(1, 3)), validConnection.getAvailableRooms("2014-03-14 11:00:00", "2014-03-14 12:00:00", 5));
        assertEquals(new ArrayList(Arrays.asList()), validConnection.getAvailableRooms("2014-03-14 09:00:00", "2014-03-14 15:00:00", 5));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidDriver() throws Exception {
        String url = "jdbc:mysql://mysql.stud.ntnu.no/";
        String dbName = "simonbo_exphil03_test";
        String driver = "com.mysql.jdbc.Driverr";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLQuery(url, dbName, driver, userName, password);

        select(invalidConnection, selectMap);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidUrl() throws Exception{
        String url = "jdbc:mysql://mysql.stud.ntnu.nop/";
        String dbName = "simonbo_exphil03_test";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLQuery(url, dbName, driver, userName, password);

        select(invalidConnection, selectMap);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDuplicateEntries() throws Exception{
        validConnection.insert("User", userFields, userValuesBefore);
        validConnection.insert("User", userFields, userValuesBefore);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSQLException() throws Exception{
        selectMap = new HashMap<>(); selectMap.put("usrname", username);

        select(validConnection, selectMap);
    }

    @Test
    public void testGetNextID() throws Exception{
        assertEquals(4, validConnection.getNextID("Meeting"));
        assertEquals(4, validConnection.getNextID("MeetingRoom"));
    }

    private ArrayList<HashMap<String, String>> select(MySQLQuery connection, HashMap<String, String> map) throws Exception {
        return connection.select(new ArrayList(Arrays.asList("User")), userFields, map, new ArrayList(Arrays.asList("")));
    }

    public void testInsert() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password123");
        selectMap.put("email", "testUser123@exphil03.com");
        selectMap.put("name", "test123 user123");

        validConnection.insert("User", userFields, userValuesBefore);

        assertEquals(1, select(validConnection, selectMap).size());
    }

    public void testUpdate() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password");
        selectMap.put("email", "testUser@exphil03.com");
        selectMap.put("name", "test user");

        validConnection.update("User", userFields, userValuesAfter, usernameMap, new ArrayList(Arrays.asList("")));

        assertEquals(1, select(validConnection, selectMap).size());
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

        assertEquals(0, select(validConnection, usernameMap).size());
    }

    public String newTestUser(){
        return "testUser" + Integer.toString(new Random().nextInt(99999));
    }

    public void deleteAll(){
        validConnection.execute("DELETE FROM Meeting WHERE '1'='1';", new ArrayList<String>(), false);
        validConnection.execute("DELETE FROM User WHERE '1'='1';", new ArrayList<String>(), false);
        validConnection.execute("DELETE FROM MeetingRoom WHERE '1'='1';", new ArrayList<String>(), false);
    }
}
