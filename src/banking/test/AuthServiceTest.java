package banking.test;

import banking.service.AuthService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import banking.model.User;

class AuthServiceTest {

    @Test
    void login_shouldReturnUserWhenCorrect() {
        AuthService auth = new AuthService();
        User user = new User("rayan", "1234", "CUSTOMER");

        auth.register(user);
        User logged = auth.login("rayan", "1234");

        assertNotNull(logged);
    }

    @Test
    void login_wrongPassword_shouldFail() {
        AuthService auth = new AuthService();
        auth.register(new User("rayan", "1234", "CUSTOMER"));

        User logged = auth.login("rayan", "wrong");

        assertNull(logged);
    }
}
