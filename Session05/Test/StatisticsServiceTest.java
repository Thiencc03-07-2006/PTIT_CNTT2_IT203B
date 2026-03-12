import com.fastfood.model.Food;
import com.fastfood.model.Order;
import com.fastfood.model.OrderStatus;
import com.fastfood.service.StatisticsService;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class StatisticsServiceTest {
    @Test
    void testDailyRevenue() {
        Order order1 = new Order("1","A");
        order1.addItem(new Food(1,"Burger",10),2);
        order1.setStatus(OrderStatus.PAID);

        Order order2 = new Order("2","B");
        order2.addItem(new Food(1,"Burger",10),1);
        order2.setStatus(OrderStatus.PAID);
        List<Order> orders = List.of(order1,order2);
        StatisticsService statisticsService = new StatisticsService(orders);
        double revenue = statisticsService.getDailyRevenue(LocalDate.now());
        assertTrue(revenue > 0);
    }

    @Test
    void testMonthlyRevenue() {
        Order order = new Order("1","A");
        order.addItem(new Food(1,"Burger",10),1);
        order.setStatus(OrderStatus.PAID);
        StatisticsService statisticsService = new StatisticsService(List.of(order));
        double revenue = statisticsService.getMonthlyRevenue(
                LocalDate.now().getYear(),
                LocalDate.now().getMonthValue()
        );
        assertTrue(revenue > 0);
    }

    @Test
    void testRevenueEmptyOrders() {

        StatisticsService statisticsService = new StatisticsService(List.of());
        double revenue = statisticsService.getDailyRevenue(LocalDate.now());
        assertEquals(0,revenue);
    }
}