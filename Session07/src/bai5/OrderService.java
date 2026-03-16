package bai5;

import java.util.*;

public class OrderService {

    private OrderRepository repo;
    private NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo;
        this.notify = notify;
    }

    public void createOrder(Order order,
                            DiscountStrategy discount,
                            PaymentMethod payment) {

        double total = order.getTotal();

        double afterDiscount = discount.apply(total);

        payment.pay(afterDiscount);

        order.setFinalAmount(afterDiscount);

        repo.save(order);

        notify.send("Đơn hàng " + order.getId() + " đã được tạo",
                order.getCustomer().getEmail());
    }

    public List<Order> getOrders() {
        return repo.findAll();
    }
}