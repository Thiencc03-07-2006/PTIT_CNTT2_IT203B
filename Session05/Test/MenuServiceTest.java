import com.fastfood.model.Food;
import com.fastfood.model.MenuItem;
import com.fastfood.repository.MenuRepository;
import com.fastfood.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuServiceTest {

    private MenuService menuService;
    private MenuRepository repository;

    @BeforeEach
    void setUp() {
        repository = new MenuRepository();
        menuService = new MenuService(repository);

        repository.addMenuItem(new Food(1,"Burger",10));
        repository.addMenuItem(new Food(2,"Bread",5));
        repository.addMenuItem(new Food(3,"Coke",3));
    }

    @Test
    void testSearchByName() {
        List<MenuItem> result = menuService.searchByName("Burger");

        assertEquals(1,result.size());
        assertEquals("Burger",result.get(0).getName());
    }

    @Test
    void testSearchByPriceRange() {
        List<MenuItem> result = menuService.searchByPriceRange(5,10);

        assertEquals(2,result.size());
    }

    @Test
    void testSearchEmptyResult() {
        List<MenuItem> result = menuService.searchByName("Pizza");

        assertTrue(result.isEmpty());
    }
}