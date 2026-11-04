package shopease;
import java.util.*;
public class Order {
    private static int counter = 5000;
    private final int id = ++counter;
    private final Customer customer;
    private final Map<Product, Integer> items;
    private final double total;
    private final String paymentMethod;
    private OrderStatus status = OrderStatus.PLACED;

    public Order(Customer c, Map<Product, Integer> items, double total, String paymentMethod) {
        this.customer = c; this.items = new LinkedHashMap<>(items); this.total = total; this.paymentMethod = paymentMethod;
    }
    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Map<Product, Integer> getItems() { return items; }
    public double getTotal() { return total; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus s) { status = s; }
    @Override public String toString() {
        StringBuilder sb = new StringBuilder(String.format("Order #%d | %s | Rs.%.2f | %s | %s%n", id, customer.getName(), total, paymentMethod, status));
        items.forEach((p, q) -> sb.append("    ").append(p.getName()).append(" x").append(q).append("\n"));
        return sb.toString();
    }
}
