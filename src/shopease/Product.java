package shopease;
/** Abstract base class: abstraction + encapsulation. */
public abstract class Product {
    private static int counter = 100;
    private final int id;
    private String name;
    private double price;
    private int stock;

    protected Product(String name, double price, int stock) {
        this.id = ++counter; this.name = name; this.price = price; this.stock = stock;
    }
    public abstract double getTaxRate();      // each category has its own GST
    public abstract String getCategory();

    public double getFinalPrice() { return price * (1 + getTaxRate()); }
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public void addStock(int q) { if (q > 0) stock += q; }
    public void reduceStock(int q) throws OutOfStockException {
        if (q > stock) throw new OutOfStockException("Only " + stock + " left for " + name);
        stock -= q;
    }
    @Override public String toString() {
        return String.format("[%d] %-22s %-12s Rs.%9.2f | stock: %d", id, name, getCategory(), getFinalPrice(), stock);
    }
}
