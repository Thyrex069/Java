package Model;

import java.util.ArrayList;

// Customer IS-A Person → correct inheritance
public class Customer extends Person {

    // ── Fields ───────────────────────────────────────────────
    private String membershipLevel;
    private int loyaltyPoints;
    private ArrayList<Order> orders; // one Customer can have many Orders

    // ── Static counter ───────────────────────────────────────
    private static int customerCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public Customer(String customerId, String name, String phone, String membershipLevel) {
        super(customerId, name, phone); // id, name, phone handled by Person
        setMembershipLevel(membershipLevel);
        this.loyaltyPoints = 0;
        this.orders = new ArrayList<>();
        customerCount++;
    }

    // ── Static method ────────────────────────────────────────
    public static int getCustomerCount() { return customerCount; }

    // ── Getters ──────────────────────────────────────────────
    public String getCustomerId()      { return id; } // inherited from Person
    public String getMembershipLevel() { return membershipLevel; }
    public int    getLoyaltyPoints()   { return loyaltyPoints; }

    public ArrayList<Order> getOrdersCopy() {
        return new ArrayList<>(orders); // defensive copy — no external modification
    }

    public int getOrderHistorySize() { return orders.size(); }

    // ── Setters ──────────────────────────────────────────────
    public void setMembershipLevel(String membershipLevel) {
        if (membershipLevel == null || membershipLevel.trim().isEmpty()) {
            this.membershipLevel = "Regular";
        } else {
            this.membershipLevel = membershipLevel.trim();
        }
    }

    // ── Business methods ─────────────────────────────────────
    public void addLoyaltyPoints(int points) {
        if (points > 0) {
            loyaltyPoints += points;
        }
    }

    // Called by ClothingShopSystem after order is placed successfully
    public void addOrder(Order order) {
        if (order != null && !orders.contains(order)) {
            orders.add(order);
        }
    }

    public void displayOrderHistory() {
        System.out.println("\nOrder History for " + name + ":");
        if (orders.isEmpty()) {
            System.out.println("  No orders yet.");
            return;
        }
        for (Order order : orders) {
            order.displayInfo();
        }
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|                     CUSTOMER INFO                      |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf( "| Customer ID      : %-35s |%n", id);
        System.out.printf( "| Name             : %-35s |%n", name);
        System.out.printf( "| Phone            : %-35s |%n", phone);
        System.out.printf( "| Membership Level : %-35s |%n", membershipLevel);
        System.out.printf( "| Loyalty Points   : %-35d |%n", loyaltyPoints);
        System.out.printf( "| Total Orders     : %-35d |%n", orders.size());
        System.out.println("+--------------------------------------------------------+");
    }
}
