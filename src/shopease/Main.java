package shopease;
import java.util.*;
public class Main {
    static final Scanner sc = new Scanner(System.in);
    static final Store store = Store.getInstance();

    static String ask(String p) { System.out.print(p); return sc.nextLine().trim(); }
    static int askInt(String p) {
        while (true) try { return Integer.parseInt(ask(p)); } catch (NumberFormatException e) { System.out.println("Enter a number."); }
    }

    public static void main(String[] args) {
        System.out.println("=== ShopEase: Console E-Commerce ===");
        while (true) {
            System.out.println("\n1. Login  2. Register  3. Exit");
            int ch = askInt("Choice: ");
            try {
                if (ch == 1) {
                    User u = store.login(ask("Email: "), ask("Password: "));
                    System.out.println("Welcome, " + u.getName() + " (" + u.getRole() + ")");
                    if (u instanceof Admin) adminMenu((Admin) u); else customerMenu((Customer) u);
                } else if (ch == 2) {
                    store.register(ask("Name: "), ask("Email: "), ask("Password: "));
                    System.out.println("Registered! Please login.");
                } else if (ch == 3) { System.out.println("Bye!"); return; }
            } catch (InvalidCredentialsException | IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    static void customerMenu(Customer c) {
        DiscountStrategy coupon = null;
        while (true) {
            System.out.println("\n--- Customer Menu (Points: " + c.getLoyaltyPoints() + ") ---");
            System.out.println("1.Browse 2.Search 3.Add to cart 4.View cart 5.Remove from cart 6.Apply coupon 7.Checkout 8.My orders 9.Cancel order 0.Logout");
            int ch = askInt("Choice: ");
            try {
                switch (ch) {
                    case 1: store.getProducts().forEach(System.out::println); break;
                    case 2: store.search(ask("Keyword/category: ")).forEach(System.out::println); break;
                    case 3: {
                        Product p = store.findProduct(askInt("Product ID: "));
                        if (p == null) { System.out.println("No such product."); break; }
                        c.getCart().add(p, askInt("Qty: ")); System.out.println("Added to cart."); break;
                    }
                    case 4:
                        if (c.getCart().isEmpty()) System.out.println("Cart is empty.");
                        else { c.getCart().getItems().forEach((p, q) -> System.out.printf("%s x%d = Rs.%.2f%n", p.getName(), q, p.getFinalPrice() * q));
                               System.out.printf("Subtotal: Rs.%.2f%n", c.getCart().getSubtotal()); }
                        break;
                    case 5: {
                        Product p = store.findProduct(askInt("Product ID: "));
                        if (p != null) c.getCart().remove(p); break;
                    }
                    case 6:
                        coupon = store.getCoupon(ask("Coupon (SAVE10/WELCOME5/FLAT100): "));
                        System.out.println(coupon == null ? "Invalid coupon." : "Applied: " + coupon.describe()); break;
                    case 7: {
                        System.out.println("Pay by: 1.UPI 2.Card 3.COD");
                        int m = askInt("Choice: ");
                        Payment pay = m == 1 ? new UpiPayment(ask("UPI ID: ")) : m == 2 ? new CardPayment(ask("16-digit card: ")) : new CashOnDelivery();
                        Order o = store.placeOrder(c, pay, coupon);
                        if (o != null) { System.out.println("Order placed!\n" + o); coupon = null; }
                        break;
                    }
                    case 8: if (c.getOrders().isEmpty()) System.out.println("No orders yet."); c.getOrders().forEach(System.out::print); break;
                    case 9: {
                        int id = askInt("Order ID: ");
                        Order target = c.getOrders().stream().filter(o -> o.getId() == id).findFirst().orElse(null);
                        if (target == null) System.out.println("Order not found."); else { store.cancelOrder(target); System.out.println("Cancelled."); }
                        break;
                    }
                    case 0: return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (OutOfStockException | IllegalStateException | IllegalArgumentException e) { System.out.println("Error: " + e.getMessage()); }
        }
    }

    static void adminMenu(Admin a) {
        while (true) {
            System.out.println("\n--- Admin Menu ---\n1.View products 2.Add product 3.Restock 4.View all orders 5.Update order status 0.Logout");
            int ch = askInt("Choice: ");
            switch (ch) {
                case 1: store.getProducts().forEach(System.out::println); break;
                case 2: {
                    int t = askInt("Type 1.Electronics 2.Clothing 3.Grocery: ");
                    String n = ask("Name: "); double pr = Double.parseDouble(ask("Price: ")); int s = askInt("Stock: ");
                    if (t == 1) store.addProduct(new Electronics(n, pr, s, askInt("Warranty (months): ")));
                    else if (t == 2) store.addProduct(new Clothing(n, pr, s, ask("Size: ")));
                    else store.addProduct(new Grocery(n, pr, s, ask("Expiry: ")));
                    System.out.println("Product added."); break;
                }
                case 3: {
                    Product p = store.findProduct(askInt("Product ID: "));
                    if (p == null) System.out.println("No such product."); else { p.addStock(askInt("Add qty: ")); System.out.println("Restocked."); }
                    break;
                }
                case 4: if (store.getOrders().isEmpty()) System.out.println("No orders."); store.getOrders().forEach(System.out::print); break;
                case 5: {
                    int id = askInt("Order ID: ");
                    Order o = store.getOrders().stream().filter(x -> x.getId() == id).findFirst().orElse(null);
                    if (o == null) { System.out.println("Not found."); break; }
                    try { o.setStatus(OrderStatus.valueOf(ask("Status (SHIPPED/DELIVERED): ").toUpperCase())); System.out.println("Updated."); }
                    catch (IllegalArgumentException e) { System.out.println("Invalid status."); }
                    break;
                }
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }
}
