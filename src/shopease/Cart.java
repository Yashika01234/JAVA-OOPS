package shopease;
import java.util.*;
public class Cart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();
    public void add(Product p, int qty) throws OutOfStockException {
        int total = items.getOrDefault(p, 0) + qty;
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be positive");
        if (total > p.getStock()) throw new OutOfStockException("Only " + p.getStock() + " left for " + p.getName());
        items.put(p, total);
    }
    public void remove(Product p) { items.remove(p); }
    public void clear() { items.clear(); }
    public boolean isEmpty() { return items.isEmpty(); }
    public Map<Product, Integer> getItems() { return items; }
    public double getSubtotal() {
        double sum = 0;
        for (Map.Entry<Product, Integer> e : items.entrySet()) sum += e.getKey().getFinalPrice() * e.getValue();
        return sum;
    }
}
