package shopease;
public class CashOnDelivery implements Payment {
    @Override public boolean pay(double amt) { System.out.printf("Rs.%.2f to be paid on delivery.%n", amt); return true; }
    @Override public String getName() { return "COD"; }
}
