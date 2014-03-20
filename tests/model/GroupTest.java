package model;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class GroupTest {
    Group testGroup1;
    Group testGroup2;

    User user;
    ArrayList<User> members;

    @Before
    public void setUp() throws Exception {
        members = new ArrayList<>();
        members.add(user);

        testGroup1 = new Group(1, -1);
        testGroup2 = new Group(2, testGroup1.getGroupID());
    }

    @After
    public void tearDown() throws Exception {
        members = null;

        testGroup1 = null;
        testGroup2 = null;
    }

    @Test
    public void testGetGroupID() throws Exception {
        assertEquals(1, testGroup1.getGroupID());
    }

    @Test
    public void testGetSuperGroup() throws Exception {
        assertEquals(-1, testGroup1.getSuperGroup());
        assertEquals(testGroup1.getGroupID(), testGroup2.getSuperGroup());
    }

    @Test
    public void testSetSuperGroup() throws Exception {
        testGroup1.setSuperGroup(testGroup2.getGroupID());
        assertEquals(testGroup2.getGroupID(), testGroup1.getSuperGroup());
    }
}
