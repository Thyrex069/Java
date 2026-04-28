package Main;

import Model.ClothingItem;
import Model.Customer;
import Model.Order;
import Model.OrderItem;
import Model.Receipt;
import Model.Staff;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("     CLOTHING SHOP MANAGEMENT SYSTEM    ");
        System.out.println("========================================\n");

        // ── 1. CREATE STAFF OBJECTS ──────────────────────────────
        System.out.println("--- Staff Members ---");
        Staff staff1 = new Staff("S001", "Alice Johnson", "2023-01-15");
        staff1.setRole("Manager");
        staff1.setSalary(3500.00);
        staff1.setShift("Morning");
        staff1.setPhoneNumber("012-3456789");
        staff1.setEmail("alice@clothingshop.com");

        Staff staff2 = new Staff("S002", "Bob Smith", "2024-03-01");
        staff2.setRole("Cashier");
        staff2.setSalary(2000.00);
        staff2.setShift("Afternoon");
        staff2.setPhoneNumber("011-9876543");
        staff2.setEmail("bob@clothingshop.com");

        // Store staff in ArrayList
        ArrayList<Staff> staffList = new ArrayList<>();
        staffList.add(staff1);
        staffList.add(staff2);

        // Loop and print staff
        for (Staff s : staffList) {
            System.out.println("ID: " + s.getStaffId()
                    + " | Name: " + s.getName()
                    + " | Role: " + s.getRole()
                    + " | Shift: " + s.getShift()
                    + " | Start Date: " + s.getStartDate());
        }

        // ── 2. CREATE CLOTHING ITEMS ─────────────────────────────
        System.out.println("\n--- Clothing Inventory ---");
        ClothingItem item1 = new ClothingItem("CI001", "Classic White Tee", "Tops", "M", "White", "Unisex");
        item1.setprice(29.90);
        item1.setstock(50);

        ClothingItem item2 = new ClothingItem("CI002", "Slim Fit Jeans", "Bottoms", "L", "Blue", "Male");
        item2.setprice(89.90);
        item2.setstock(30);

        ClothingItem item3 = new ClothingItem("CI003", "Floral Summer Dress", "Dress", "S", "Pink", "Female");
        item3.setprice(59.90);
        item3.setstock(20);

        // Store items in ArrayList
        ArrayList<ClothingItem> inventory = new ArrayList<>();
        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

        // Loop and print inventory
        for (ClothingItem item : inventory) {
            System.out.println("Code: " + item.getItemCode()
                    + " | Name: " + item.getItemName()
                    + " | Category: " + item.getCategory()
                    + " | Size: " + item.getSize()
                    + " | Color: " + item.getColor()
                    + " | Gender: " + item.getGender()
                    + " | Price: $" + item.getPrice()
                    + " | Stock: " + item.getStock());
        }

        // ── 3. CREATE CUSTOMER OBJECTS ───────────────────────────
        System.out.println("\n--- Customers ---");
        Customer customer1 = new Customer("C001", "Emma Davis");
        customer1.setPhone("016-1112222");
        customer1.setEmail("emma@gmail.com");
        customer1.setAddress("123 Main Street, KL");
        customer1.addLoyaltyPoints(100);

        Customer customer2 = new Customer("C002", "James Lee");
        customer2.setPhone("017-3334444");
        customer2.setEmail("james@gmail.com");
        customer2.setAddress("456 Jalan Bukit, Penang");

        // Store customers in ArrayList
        ArrayList<Customer> customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);

        // Loop and print customers
        for (Customer c : customerList) {
            System.out.println("ID: " + c.getCustomerId()
                    + " | Name: " + c.getName()
                    + " | Phone: " + c.getPhone()
                    + " | Email: " + c.getEmail()
                    + " | Membership: " + c.getMembershipLevel()
                    + " | Loyalty Points: " + c.getLoyaltyPoints());
        }

        // ── 4. CREATE ORDER ──────────────────────────────────────
        System.out.println("\n--- Processing Order ---");
        Order order1 = new Order("ORD001", customer1, "2025-04-27");
        order1.setStatus("Confirmed");
        order1.setPaymentStatus("Paid");
        order1.setShippingAddress(customer1.getAddress());

        // Create OrderItems from the ClothingItem details
        OrderItem orderItem1 = new OrderItem(
                item1.getItemCode(), item1.getItemName(), item1.getPrice(), 2);
        OrderItem orderItem2 = new OrderItem(
                item3.getItemCode(), item3.getItemName(), item3.getPrice(), 1);

        // Add items to the order
        order1.getItems().add(orderItem1);
        order1.getItems().add(orderItem2);

        System.out.println("Order ID   : " + order1.getOrderID());
        System.out.println("Customer   : " + order1.getCustomer().getName());
        System.out.println("Date       : " + order1.getOrderDate());
        System.out.println("Status     : " + order1.getStatus());
        System.out.println("Payment    : " + order1.getPaymentStatus());
        System.out.println("Ship To    : " + order1.getShippingAddress());

        System.out.println("\nItems Ordered:");
        double orderTotal = 0;
        for (OrderItem oi : order1.getItems()) {
            System.out.println("  - " + oi.getItemName()
                    + " | Qty: " + oi.getQuantity()
                    + " | Price: $" + oi.getPrice()
                    + " | Subtotal: $" + oi.calculateSubtotal());
            orderTotal += oi.calculateSubtotal();
        }
        System.out.println("Order Total: $" + orderTotal);

        // ── 5. GENERATE RECEIPT ──────────────────────────────────
        System.out.println("\n--- Receipt ---");
        Receipt receipt1 = new Receipt("REC001", customer1.getName(), "Credit Card");
        for (OrderItem oi : order1.getItems()) {
            receipt1.addItem(oi);
        }

        System.out.println("Receipt ID     : " + receipt1.getReceiptId());
        System.out.println("Customer Name  : " + receipt1.getCustomerName());
        System.out.println("Payment Method : " + receipt1.getPaymentMethod());
        System.out.println("Items on Receipt:");
        for (OrderItem oi : receipt1.getItems()) {
            System.out.println("  - " + oi.getItemName()
                    + " x" + oi.getQuantity()
                    + " = $" + oi.calculateSubtotal());
        }
        System.out.printf("Total Amount   : $%.2f%n", receipt1.getTotalAmount());

        // ── 6. STATIC COUNTER ────────────────────────────────────
        System.out.println("\n--- System Summary (Static Counter) ---");
        System.out.println("Total Receipts Issued: " + Receipt.getReceiptCount());

        System.out.println("\n========================================");
        System.out.println("           PROGRAM COMPLETE             ");
        System.out.println("========================================");
    }
}