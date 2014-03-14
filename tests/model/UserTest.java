package model;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
    User testUser;
    ExternalUser testExternalUser;
    @Before
    public void setUp() throws Exception {
        testUser = new User("Username", "password", "FirstName LastName", "email@example.com", "+47 123 45 678");
    }

    @After
    public void tearDown() throws Exception {
        testUser = null;
    }

    @Test
    public void testUsernamePattern() throws Exception {
        assertTrue("Username.-_".matches(User.USERNAME_PATTERN));
        assertFalse("User name".matches(User.USERNAME_PATTERN));
    }

    @Test
    public void testNamePattern() throws Exception {
        assertTrue("Name Namesen".matches(User.NAME_PATTERN));
        assertTrue("Name Name-Namesen".matches(User.NAME_PATTERN));
        assertTrue("NameNamesen".matches(User.NAME_PATTERN));
        assertFalse("Name_Namesen".matches(User.NAME_PATTERN));
    }

    @Test
    public void testPhonePattern() throws Exception {
        assertTrue("12345678".matches(User.PHONE_PATTERN));
        assertTrue("+47 123 45 678".matches(User.PHONE_PATTERN));
        assertTrue("+4712345678".matches(User.PHONE_PATTERN));
        assertTrue("(+47) 123 45 678".matches(User.PHONE_PATTERN));
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals(testUser.getUsername(), "Username");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetUsername() throws Exception {
        testUser.setUsername("User name");
    }

    @Test
    public void testGetPassword() throws Exception {
        assertEquals(testUser.getPassword(), "password");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetPassword() throws Exception {
        testUser.setPassword("12345");
        testUser.setPassword("123456123456123456123456123456123");
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(testUser.getName(), "FirstName LastName");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetName() throws Exception {
        testUser.setName("FirstName_LastName");
    }

    @Test
    public void testGetEmail() throws Exception {
        assertEquals(testUser.getEmail(), "email@example.com");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetEmail() throws Exception {
        testUser.setEmail("email_email.com");
        testUser.setEmail("email@email,com");
    }

    @Test
    public void testGetPhonenumber() throws Exception {
        assertEquals(testUser.getPhonenumber(), "+47 123 45 678");
    }

    @Test (expected = IllegalArgumentException.class)
    public void testSetPhonenumber() throws Exception {
        testUser.setPhonenumber("+47 123 45a 678");
    }

    @Test
    public void setTestExternalUser() throws Exception {
        testExternalUser = new ExternalUser("Test User", "externaluser@exphil03.com", "+47 123 45 678");

        assertEquals("Test User", testExternalUser.getName());
        assertEquals("externaluser@exphil03.com", testExternalUser.getEmail());
        assertEquals("+47 123 45 678", testExternalUser.getPhonenumber());
    }

    @Test
    public void testToString() throws Exception {
        assertEquals(testUser.toString(), "FirstName LastName");
    }
}
