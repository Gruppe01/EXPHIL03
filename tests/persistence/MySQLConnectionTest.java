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
    HashMap<String, String> updateMap;
    HashMap<String, String> deleteMap;
    HashMap<String, String> selectMap;
    String username;

    @Before
    public void setUp() throws Exception {
        validConnection = new MySQLConnection();
        updateMap = new HashMap<>();
        deleteMap = new HashMap<>();
        selectMap = new HashMap<>();
        username = "testUser" + Integer.toString(new Random().nextInt(99999));

        updateMap.put("username", username);
        deleteMap.put("username", username);
        selectMap.put("username", username);
    }

    @After
    public void tearDown() throws Exception {
        testDelete();

        validConnection = null;
        invalidConnection = null;
        updateMap = null;
        deleteMap = null;
        selectMap = null;
        username = null;
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

        select(invalidConnection);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidUrl() throws Exception{
        String url = "jdbc:mysql://mysql.stud.ntnu.nop/";
        String dbName = "simonbo_exphil03";
        String driver = "com.mysql.jdbc.Driver";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLConnection(url, dbName, driver, userName, password);

        select(invalidConnection);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testDuplicateEntries() throws Exception{
        validConnection.insert("User", new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123", "user123")));
        validConnection.insert("User", new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123", "user123")));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testSQLException() throws Exception{
        selectMap = new HashMap<>();
        selectMap.put("userame", username);

        select(validConnection);
    }

    private ArrayList<HashMap<String, String>> select(MySQLConnection connection) throws Exception {
        return connection.select(new ArrayList(Arrays.asList("User")), new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), selectMap, new ArrayList(Arrays.asList("")));
    }

    public void testInsert() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password123");
        selectMap.put("email", "testUser123@exphil03.com");
        selectMap.put("firstName", "test123");
        selectMap.put("lastName", "user123");

        validConnection.insert("User", new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123", "user123")));

        ArrayList<HashMap<String, String>> results = select(validConnection);

        assertEquals(results.size(), 1);
    }

    public void testUpdate() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password");
        selectMap.put("email", "testUser@exphil03.com");
        selectMap.put("firstName", "test");
        selectMap.put("lastName", "user");

        validConnection.update("User", new ArrayList(Arrays.asList("password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList("password", "testUser@exphil03.com", "test", "user")), updateMap, new ArrayList(Arrays.asList("")));

        ArrayList<HashMap<String, String>> results = select(validConnection);

        assertEquals(results.size(), 1);
    }

    public void testSelect() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);
        selectMap.put("password", "password");
        selectMap.put("email", "testUser@exphil03.com");
        selectMap.put("firstName", "test");
        selectMap.put("lastName", "user");

        ArrayList<HashMap<String, String>> results = select(validConnection);

        Iterator<String> iterator = results.get(0).values().iterator();

        assertEquals(iterator.next(), "user");
        assertEquals(iterator.next(), username);
        assertEquals(iterator.next(), "testUser@exphil03.com");
        assertEquals(iterator.next(), "test");
        assertEquals(iterator.next(), "password");
    }

    public void testDelete() throws Exception {
        selectMap = new HashMap<>();
        selectMap.put("username", username);

        validConnection.delete("User", deleteMap);

        ArrayList<HashMap<String, String>> result = select(validConnection);

        assertEquals(0, result.size());
    }
}
