package shopease;
public class UpiPayment implements Payment {
    private final String upiId;
    public UpiPayment(String upiId) { this.upiId = upiId; }
    @Override public boolean pay(double amt) {
        if (!upiId.matches("[\\w.\\-]+@[\\w]+")) { System.out.println("Invalid UPI ID."); return false; }
        System.out.printf("Paid Rs.%.2f via UPI (%s)%n", amt, upiId); return true;
    }
    @Override public String getName() { return "UPI"; }
}
