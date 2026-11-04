# ShopEase – Console E-Commerce Platform (Java OOP Project)

A Flipkart/Amazon-style shopping app built in **pure Java (no libraries)** to demonstrate core Object-Oriented Programming concepts.

## Features
- **Customer:** register/login, browse & search products, cart, coupons, checkout (UPI / Card / COD), order history, cancel order, loyalty points
- **Admin:** add products, restock, view all orders, update order status (SHIPPED / DELIVERED)
- Category-wise GST (Electronics 18%, Clothing 12%, Grocery 5%)
- Coupons: `SAVE10`, `WELCOME5`, `FLAT100`

## OOP Concepts Used
| Concept | Where |
|---|---|
| **Abstraction** | `Product`, `User` (abstract classes); `Payment`, `DiscountStrategy` (interfaces) |
| **Inheritance** | `Electronics / Clothing / Grocery` extend `Product`; `Customer / Admin` extend `User` |
| **Polymorphism** | `getTaxRate()`, `toString()` overridden per category; `Payment.pay()` per method; `DiscountStrategy.apply()` |
| **Encapsulation** | Private fields + getters; stock changes only via `addStock/reduceStock`; password never exposed |
| **Composition** | `Customer` has a `Cart` and `Orders`; `Order` has items |
| **Exception Handling** | Custom `OutOfStockException`, `InvalidCredentialsException` |
| **Design Patterns** | Singleton (`Store`), Strategy (`DiscountStrategy`) |
| **Enums / Collections** | `OrderStatus`; `Map`, `List`, `LinkedHashMap`, Streams |

## Project Structure
```
ShopEase/
├── README.md
├── run.sh / run.bat
└── src/shopease/
    ├── Main.java                 # menus & user interaction
    ├── Store.java                # Singleton: products, users, orders, coupons
    ├── Product.java (abstract) → Electronics, Clothing, Grocery
    ├── User.java (abstract)    → Customer, Admin
    ├── Cart.java, Order.java, OrderStatus.java
    ├── Payment.java (interface) → UpiPayment, CardPayment, CashOnDelivery
    ├── DiscountStrategy.java (interface) → PercentDiscount, FlatDiscount
    └── OutOfStockException.java, InvalidCredentialsException.java
```

## How to Run (JDK 11+)
```bash
# Linux / Mac
./run.sh

# Windows
run.bat

# or manually
mkdir out && javac -d out src/shopease/*.java && java -cp out shopease.Main
```
**Admin login:** `admin@shop.com` / `admin123`  •  Customers: use *Register* first.

## Future Improvements
File/database persistence (JDBC + MySQL), seller role, product reviews, GUI (JavaFX/Swing), REST API with Spring Boot.

## Resume Line
*Built ShopEase, a Java e-commerce platform applying abstraction, inheritance, polymorphism, encapsulation, custom exceptions, and Singleton/Strategy design patterns across 20 classes with cart, coupon, multi-mode payment and order-management modules.*
