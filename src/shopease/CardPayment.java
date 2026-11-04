package shopease;
public class CardPayment implements Payment {
    private final String cardNo;
    public CardPayment(String cardNo) { this.cardNo = cardNo; }
    @Override public boolean pay(double amt) {
        if (!cardNo.matches("\\d{16}")) { System.out.println("Card number must be 16 digits."); return false; }
        System.out.printf("Paid Rs.%.2f via Card ending %s%n", amt, cardNo.substring(12)); return true;
    }
    @Override public String getName() { return "Card"; }
}
