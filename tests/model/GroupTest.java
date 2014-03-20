package model;

import org.junit.*;
import static org.junit.Assert.*;
import persistence.mysql.MySQLQuery;
import persistence.mysql.MySQLTest;

import java.util.ArrayList;

public class GroupTest {
    MySQLQuery mySQLConnection = MySQLTest.TEST_CONNECTION();

    Group testGroup1;
    Group testGroup2;
    Group testGroup3;
    Group testGroup4;

    User user;
    ArrayList<User> members;

    @Before
    public void setUp() throws Exception {
        mySQLConnection.execute("ALTER TABLE `Group` AUTO_INCREMENT = 1", new ArrayList<String>(), false);

        user = new User("testuser", "drossap", "Test User", "test@user.user", "12345678");

        members = new ArrayList<>();
        members.add(user);

        testGroup1 = new Group();
        testGroup2 = new Group(members);
        testGroup3 = new Group(testGroup1.getGroupID());
        testGroup4 = new Group(testGroup1.getGroupID(), members);
    }

    @After
    public void tearDown() throws Exception {
        members = null;

        testGroup1 = null;
        testGroup2 = null;
        testGroup3 = null;
        testGroup4 = null;
    }

    @Test
    public void testGetGroupID() throws Exception {
        assertEquals(1, testGroup1.getGroupID());
    }

    @Test
    public void testGetSuperGroup() throws Exception {
        assertEquals(-1, testGroup1.getSuperGroup());
        assertEquals(testGroup1.getGroupID(), testGroup3.getSuperGroup());
    }

    @Test
    public void testSetSuperGroup() throws Exception {
        testGroup1.setSuperGroup(testGroup2.getGroupID());
        assertEquals(testGroup2.getGroupID(), testGroup1.getSuperGroup());
    }

    @Test
    public void testGetMembers() throws Exception {
        assertEquals(members, testGroup2.getMembers());
    }

    @Test
    public void testSetMembers() throws Exception {
        testGroup1.setMembers(members);
        assertEquals(members, testGroup1.getMembers());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMember() throws Exception {
        testGroup1.addMember(user);
        assertEquals(user, testGroup1.getMembers().get(0));
        testGroup1.addMember(user);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeleteMember() throws Exception {
        testGroup2.deleteMember(user);
        testGroup2.deleteMember(user);
    }
}
