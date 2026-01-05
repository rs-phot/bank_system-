package banking.test;

import banking.service.Session;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import banking.model.User;

class SessionTest {

    @Test
    void login_shouldSetUser() {
        Session session = new Session();
        User user = new User("rayan", "1234", "CUSTOMER");

        session.login(user);

        assertTrue(session.isLoggedIn());
        assertEquals("rayan", session.getCurrentUser().getUsername());
    }

    @Test
    void logout_shouldClearSession() {
        Session session = new Session();
        session.login(new User("rayan", "1234", "CUSTOMER"));

        session.logout();

        assertFalse(session.isLoggedIn());
        assertNull(session.getCurrentUser());
    }
}
