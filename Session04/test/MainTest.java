import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("TestSum")
    void sum() {
        Assertions.assertEquals(3, main.sum(1, 2));
        Assertions.assertEquals(5, main.sum(2, 2));
    }

}