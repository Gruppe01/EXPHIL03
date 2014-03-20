package model;

import org.junit.*;
import static org.junit.Assert.*;

public class ExternalUserTest {
    private ExternalUser testUser;

    @Before
    public void setUp() throws Exception {
        testUser = new ExternalUser("test@test.com", 1, "Test User", "12345678");
    }

    @After
    public void tearDown() throws Exception {
        testUser = null;
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals("test@test.com", testUser.getEmail());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEmail() throws Exception {
        testUser.setEmail("test@test123.com");
        assertEquals("test@test123.com", testUser.getEmail());
        testUser.setEmail("test£test123.com");
    }

    @Test
    public void testGetMeetingID() throws Exception {
        assertEquals(1, testUser.getMeetingID());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals("Test User", testUser.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetName() throws Exception {
        testUser.setName("Test-User");
        assertEquals("Test-User", testUser.getName());
        testUser.setName("Test User123");
    }

    @Test
    public void testGetPhoneNumber() throws Exception {
        assertEquals("12345678", testUser.getPhoneNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetPhoneNumber() throws Exception {
        testUser.setPhoneNumber("123456789");
        assertEquals("123456789", testUser.getPhoneNumber());
        testUser.setPhoneNumber("1234567");
    }

    @Test
    public void testToString() throws Exception {
        assertEquals("Test User", testUser.toString());
    }
}
