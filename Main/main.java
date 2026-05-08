package Main;

import Model.ClothingItem;
import Model.Customer;
import Model.Order;
import Model.OrderItem;
import Model.Receipt;
import Model.Staff;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   CLOTHING SHOP MANAGEMENT SYSTEM     ");
        System.out.println("========================================\n");

        // ════════════════════════════════════════════════════
        // STEP 1: CREATE TWO CUSTOMERS
        // ════════════════════════════════════════════════════
        System.out.println("--- Customers ---");

        Customer customer1 = new Customer("C001", "Emma Davis");
        customer1.setPhone("016-1112222");
        customer1.addLoyaltyPoints(100);

        Customer customer2 = new Customer("C002", "James Lee");
        customer2.setPhone("017-3334444");

        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        // display() from Displayable interface — each Customer prints itself
        for (Customer c : customerList) {
            c.display();
        }
        // getSummary() from Manageable interface — one-line summary
        System.out.println("\nCustomer Summaries:");
        for (Customer c : customerList) {
            System.out.println("  " + c.getSummary());
        }
        System.out.println("Total Customers Created: " + Customer.getCustomerCount());

        // ════════════════════════════════════════════════════
        // STEP 2: CREATE ONE STAFF MEMBER
        // ════════════════════════════════════════════════════
        System.out.println("\n--- Staff ---");

        Staff staff1 = new Staff("S001", "Bob Smith", "2024-03-01");
        staff1.setRole("Cashier");
        staff1.setShift("Morning");
        staff1.setPhoneNumber("011-9876543");
        staff1.setSalary(2000.00);

        // display() from Displayable interface
        staff1.display();
        System.out.println("Summary: " + staff1.getSummary()); // Manageable
        System.out.println("Total Staff Created: " + Staff.getStaffCount());

        // ════════════════════════════════════════════════════
        // STEP 3: CREATE THREE CLOTHING ITEMS
        // ════════════════════════════════════════════════════
        System.out.println("\n--- Clothing Inventory ---");

        ClothingItem item1 = new ClothingItem("CI001", "Classic White Tee", "Tops", "M", "White", "Unisex");
        item1.setPrice(29.90);
        item1.setStock(50);

        ClothingItem item2 = new ClothingItem("CI002", "Slim Fit Jeans", "Bottoms", "L", "Blue", "Male");
        item2.setPrice(89.90);
        item2.setStock(30);

        ClothingItem item3 = new ClothingItem("CI003", "Floral Summer Dress", "Dress", "S", "Pink", "Female");
        item3.setPrice(59.90);
        item3.setStock(20);

        ArrayList<ClothingItem> inventory = new ArrayList<>();
        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        // display() from Displayable interface — each item prints itself
        for (ClothingItem item : inventory) {
            item.display();
        }
        System.out.println("Total Clothing Items Created: " + ClothingItem.getItemCount());

        // ════════════════════════════════════════════════════
        // STEP 4: CREATE ONE ORDER FOR CUSTOMER 1
        // ════════════════════════════════════════════════════
        System.out.println("\n--- Processing Order ---");

        Order order1 = new Order("ORD001", customer1, staff1, "2025-04-27");
        order1.setPaymentStatus("Paid");

        // Add OrderItems to the order
        OrderItem orderItem1 = new OrderItem(item1, 2); // 2x Classic White Tee
        OrderItem orderItem2 = new OrderItem(item3, 1); // 1x Floral Summer Dress

        order1.addItem(orderItem1);
        order1.addItem(orderItem2);

        // Apply $5.00 discount to the dress
        orderItem2.setDiscount(5.00);
        System.out.println("Discount of $5.00 applied to: " + orderItem2.getItemName());

        // display() from Displayable interface — Order prints itself + all items
        order1.display();

        // ════════════════════════════════════════════════════
        // STEP 5: CONFIRM ORDER — REDUCES STOCK
        // ════════════════════════════════════════════════════
        System.out.println("\n--- Confirming Order ---");
        System.out.println("Stock BEFORE confirmation:");
        System.out.println("  " + item1.getItemName() + " stock: " + item1.getStock());
        System.out.println("  " + item3.getItemName() + " stock: " + item3.getStock());

        order1.confirmOrder();

        System.out.println("Stock AFTER confirmation:");
        System.out.println("  " + item1.getItemName() + " stock: " + item1.getStock());
        System.out.println("  " + item3.getItemName() + " stock: " + item3.getStock());

        // ════════════════════════════════════════════════════
        // STEP 6: CREATE RECEIPT FROM THE COMPLETED ORDER
        // ════════════════════════════════════════════════════
        Receipt receipt1 = new Receipt("REC001", order1, "Credit Card");

        // display() from Displayable interface — Receipt prints itself like a real receipt
        receipt1.display();

        // ════════════════════════════════════════════════════
        // STEP 7: STATIC COUNTERS SUMMARY
        // ════════════════════════════════════════════════════
        System.out.println("\n--- System Summary (Static Counters) ---");
        System.out.println("Total Customers Created    : " + Customer.getCustomerCount());
        System.out.println("Total Staff Created        : " + Staff.getStaffCount());
        System.out.println("Total Clothing Items Created: " + ClothingItem.getItemCount());
        System.out.println("Total Orders Created       : " + Order.getOrderCount());
        System.out.println("Total Order Items Created  : " + OrderItem.getOrderItemCount());
        System.out.println("Total Receipts Issued      : " + Receipt.getReceiptCount());

        System.out.println("\n========================================");
        System.out.println("           PROGRAM COMPLETE             ");
        System.out.println("========================================");

    }
}