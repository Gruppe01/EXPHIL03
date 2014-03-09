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
    public void testInvalidDriver(){
        String url = "jdbc:mysql://mysql.stud.ntnu.no/";
        String dbName = "simonbo_exphil03";
        String driver = "com.mysql.jdbc.Driverr";
        String userName = "simonbo_exphil03";
        String password = "drossap";

        invalidConnection = new MySQLConnection(url, dbName, driver, userName, password);

        select(invalidConnection);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidUrl(){
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

    private ArrayList<HashMap<String, String>> select(MySQLConnection connection){
        return connection.select(new ArrayList(Arrays.asList("User")), new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), selectMap);
    }

    public void testInsert() throws Exception {
        validConnection.insert("User", new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList(username, "password123", "testUser123@exphil03.com", "test123", "user123")));
    }

    public void testUpdate() throws Exception {
        validConnection.update("User", new ArrayList(Arrays.asList("username", "password", "email", "firstName", "lastName")), new ArrayList(Arrays.asList(username, "password", "testUser@exphil03.com", "test", "user")), updateMap);
    }

    public void testSelect() throws Exception {
        ArrayList<HashMap<String, String>> results = select(validConnection);

        Iterator<String> iterator = results.get(0).values().iterator();

        assertEquals(iterator.next(), "user");
        assertEquals(iterator.next(), username);
        assertEquals(iterator.next(), "testUser@exphil03.com");
        assertEquals(iterator.next(), "test");
        assertEquals(iterator.next(), "password");
    }

    public void testDelete() throws Exception {
        validConnection.delete("User", deleteMap);

        ArrayList<HashMap<String, String>> result = select(validConnection);

        assertEquals(0, result.size());
    }
}
