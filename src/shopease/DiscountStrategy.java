package shopease;
/** Strategy pattern: coupons are interchangeable discount behaviours. */
public interface DiscountStrategy {
    double apply(double amount);
    String describe();
}
