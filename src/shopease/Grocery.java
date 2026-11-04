package shopease;
public class Grocery extends Product {
    private final String expiry;
    public Grocery(String n, double p, int s, String expiry) { super(n, p, s); this.expiry = expiry; }
    @Override public double getTaxRate() { return 0.05; }
    @Override public String getCategory() { return "Grocery"; }
    @Override public String toString() { return super.toString() + " | exp " + expiry; }
}
