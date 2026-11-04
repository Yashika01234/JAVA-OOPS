package shopease;
public class Electronics extends Product {
    private final int warrantyMonths;
    public Electronics(String n, double p, int s, int warrantyMonths) { super(n, p, s); this.warrantyMonths = warrantyMonths; }
    @Override public double getTaxRate() { return 0.18; }
    @Override public String getCategory() { return "Electronics"; }
    @Override public String toString() { return super.toString() + " | " + warrantyMonths + "m warranty"; }
}
