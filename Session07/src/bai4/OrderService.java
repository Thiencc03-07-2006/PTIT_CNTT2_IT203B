package bai4;

public class OrderService {

    private OrderRepository orderRepository;
    private NotificationService notificationService;

    public OrderService(OrderRepository orderRepository, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.notificationService = notificationService;
    }

    public void createOrder(Order order, String customer) {
        orderRepository.save(order);
        notificationService.send("Đơn hàng " + order.getOrderId() + " đã được tạo", customer);
    }
}