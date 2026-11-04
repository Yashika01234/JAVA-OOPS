package shopease;
public class PercentDiscount implements DiscountStrategy {
    private final double percent;
    public PercentDiscount(double percent) { this.percent = percent; }
    @Override public double apply(double a) { return a * (1 - percent / 100); }
    @Override public String describe() { return percent + "% off"; }
}
