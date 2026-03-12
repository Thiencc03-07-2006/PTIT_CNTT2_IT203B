package bai1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void TC01() {
        UserValidator validator = new UserValidator();
        String username = "user123";
        boolean result = validator.isValidUsername(username);
        assertTrue(result);
    }

    @Test
    void TC02() {
        UserValidator validator = new UserValidator();
        String username = "abc";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }

    @Test
    void TC03() {
        UserValidator validator = new UserValidator();
        String username = "user name";
        boolean result = validator.isValidUsername(username);
        assertFalse(result);
    }
}