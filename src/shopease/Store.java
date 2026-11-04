package shopease;
import java.util.*;
/** Singleton holding all application data (in-memory). */
public class Store {
    private static Store instance;
    private final Map<Integer, Product> products = new LinkedHashMap<>();
    private final Map<String, User> users = new HashMap<>();
    private final List<Order> orders = new ArrayList<>();
    private final Map<String, DiscountStrategy> coupons = new HashMap<>();

    private Store() { seed(); }
    public static Store getInstance() { if (instance == null) instance = new Store(); return instance; }

    private void seed() {
        addProduct(new Electronics("Wireless Earbuds", 1999, 20, 12));
        addProduct(new Electronics("Smart Watch", 3499, 10, 12));
        addProduct(new Electronics("Power Bank 10000mAh", 1299, 15, 6));
        addProduct(new Clothing("Cotton T-Shirt", 499, 40, "M"));
        addProduct(new Clothing("Denim Jacket", 1899, 12, "L"));
        addProduct(new Grocery("Basmati Rice 5kg", 650, 30, "Dec 2027"));
        addProduct(new Grocery("Almonds 500g", 480, 25, "Aug 2027"));
        users.put("admin@shop.com", new Admin("Admin", "admin@shop.com", "admin123"));
        coupons.put("SAVE10", new PercentDiscount(10));
        coupons.put("WELCOME5", new PercentDiscount(5));
        coupons.put("FLAT100", new FlatDiscount(100, 500));
    }
    public void addProduct(Product p) { products.put(p.getId(), p); }
    public Collection<Product> getProducts() { return products.values(); }
    public Product findProduct(int id) { return products.get(id); }
    public List<Product> search(String kw) {
        List<Product> r = new ArrayList<>();
        for (Product p : products.values())
            if (p.getName().toLowerCase().contains(kw.toLowerCase()) || p.getCategory().equalsIgnoreCase(kw)) r.add(p);
        return r;
    }
    public Customer register(String name, String email, String pass) {
        if (users.containsKey(email)) throw new IllegalArgumentException("Email already registered");
        Customer c = new Customer(name, email, pass); users.put(email, c); return c;
    }
    public User login(String email, String pass) throws InvalidCredentialsException {
        User u = users.get(email);
        if (u == null || !u.checkPassword(pass)) throw new InvalidCredentialsException("Wrong email or password");
        return u;
    }
    public DiscountStrategy getCoupon(String code) { return coupons.get(code.toUpperCase()); }
    public List<Order> getOrders() { return orders; }

    public Order placeOrder(Customer c, Payment pay, DiscountStrategy d) throws OutOfStockException {
        Cart cart = c.getCart();
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty");
        for (Map.Entry<Product, Integer> e : cart.getItems().entrySet())
            if (e.getValue() > e.getKey().getStock()) throw new OutOfStockException(e.getKey().getName() + " is out of stock");
        double total = cart.getSubtotal();
        if (d != null) total = d.apply(total);
        if (!pay.pay(total)) return null;
        for (Map.Entry<Product, Integer> e : cart.getItems().entrySet()) e.getKey().reduceStock(e.getValue());
        Order o = new Order(c, cart.getItems(), total, pay.getName());
        orders.add(o); c.getOrders().add(o); c.addLoyaltyPoints((int) (total / 100));
        cart.clear();
        return o;
    }
    public void cancelOrder(Order o) {
        if (o.getStatus() != OrderStatus.PLACED) throw new IllegalStateException("Only PLACED orders can be cancelled");
        o.getItems().forEach(Product::addStock);
        o.setStatus(OrderStatus.CANCELLED);
    }
}
