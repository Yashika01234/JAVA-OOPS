package shopease;
import java.util.*;
public class Customer extends User {
    private final Cart cart = new Cart();
    private final List<Order> orders = new ArrayList<>();
    private int loyaltyPoints;
    public Customer(String n, String e, String p) { super(n, e, p); }
    @Override public String getRole() { return "CUSTOMER"; }
    public Cart getCart() { return cart; }
    public List<Order> getOrders() { return orders; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public void addLoyaltyPoints(int p) { loyaltyPoints += p; }
}
