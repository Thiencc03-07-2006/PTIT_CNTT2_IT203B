package bai3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProcessorTest {
    @Test
    void TC1() {
        UserProcessor validator = new UserProcessor();
        String email = "user@gmail.com";
        String result = validator.processEmail(email);
        assertEquals("user@gmail.com", result);
    }

    @Test
    void TC2() {
        UserProcessor validator = new UserProcessor();
        String email = "usergmail.com";
        assertThrows(IllegalArgumentException.class, () -> {
            validator.processEmail(email);
        });
    }

    @Test
    void TC3() {
        UserProcessor validator = new UserProcessor();
        String email = "user@";

        assertThrows(IllegalArgumentException.class, () -> {
            validator.processEmail(email);
        });
    }

    @Test
    void TC4() {
        UserProcessor validator = new UserProcessor();
        String email = "Example@Gmail.com";
        String result = validator.processEmail(email);
        assertEquals("example@gmail.com", result);
    }
}