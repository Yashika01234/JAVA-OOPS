package shopease;
/** Interface: polymorphic payment methods. */
public interface Payment {
    boolean pay(double amount);
    String getName();
}
