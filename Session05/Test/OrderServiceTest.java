import com.fastfood.model.Food;
import com.fastfood.model.Order;
import com.fastfood.repository.DiscountRepository;
import com.fastfood.repository.OrderRepository;
import com.fastfood.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;
    private DiscountRepository discountRepository;
    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        discountRepository = new DiscountRepository();
        orderService = new OrderService(orderRepository,discountRepository);
        Order order = new Order("1","TestCustomer");
        order.addItem(new Food(1,"Burger",10),1);
        order.addItem(new Food(2,"Bread",5),2);
        orderRepository.save(order);
    }

    @Test
    void testCalculateTotalPrice() {

        double total = orderService.calculateTotal("1");
        assertEquals(21,total,0.01);
    }

    @Test
    void testCalculateTotalAfterDiscount() {
        Order order = orderRepository.findById("1").get();
        double discount = order.calculateTotal()*0.1;
        order.setDiscountAmount(discount);
        double finalTotal = order.getTotalAfterDiscount();
        assertEquals(18.9,finalTotal,0.01);
    }

    @Test
    void testOrderNotFound() {
        assertThrows(RuntimeException.class,()->{
            orderService.calculateTotal("999");
        });
    }
}