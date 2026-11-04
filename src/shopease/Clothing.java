package shopease;
public class Clothing extends Product {
    private final String size;
    public Clothing(String n, double p, int s, String size) { super(n, p, s); this.size = size; }
    @Override public double getTaxRate() { return 0.12; }
    @Override public String getCategory() { return "Clothing"; }
    @Override public String toString() { return super.toString() + " | size " + size; }
}
