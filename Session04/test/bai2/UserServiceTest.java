package bai2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void TC1() {
        UserService validator = new UserService();
        int age = 18;
        boolean result = validator.checkRegistrationAge(age);
        assertEquals(true, result);
    }

    @Test
    void TC2() {
        UserService validator = new UserService();
        int age = 17;
        boolean result = validator.checkRegistrationAge(age);
        assertEquals(false, result);
        ;
    }

    @Test
    void TC3() {
        UserService validator = new UserService();
        assertThrows(IllegalArgumentException.class, () -> {
            validator.checkRegistrationAge(-1);
        });
    }
}