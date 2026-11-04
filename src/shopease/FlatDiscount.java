package shopease;
public class FlatDiscount implements DiscountStrategy {
    private final double off, minOrder;
    public FlatDiscount(double off, double minOrder) { this.off = off; this.minOrder = minOrder; }
    @Override public double apply(double a) { return a >= minOrder ? a - off : a; }
    @Override public String describe() { return "Rs." + off + " off on orders above Rs." + minOrder; }
}
