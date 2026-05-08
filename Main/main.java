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

        for (Customer c : customerList) {
            System.out.println("ID: " + c.getCustomerId()
                    + " | Name: " + c.getName()
                    + " | Phone: " + c.getPhone()
                    + " | Membership: " + c.getMembershipLevel()
                    + " | Points: " + c.getLoyaltyPoints());
        }
        // Static counter — how many Customer objects created
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

        System.out.println("ID: " + staff1.getStaffId()
                + " | Name: " + staff1.getName()
                + " | Role: " + staff1.getRole()
                + " | Shift: " + staff1.getShift()
                + " | Start Date: " + staff1.getStartDate());

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

        // ── STEP 4: Store clothing items in inventory ArrayList ──
        ArrayList<ClothingItem> inventory = new ArrayList<>();
        inventory.add(item1);
        inventory.add(item2);
        inventory.add(item3);

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
        System.out.println("Total Clothing Items Created: " + ClothingItem.getItemCount());

        // ════════════════════════════════════════════════════
        // STEP 5: CREATE ONE ORDER FOR CUSTOMER 1
        // Order now connects Customer + Staff together
        // ════════════════════════════════════════════════════
        System.out.println("\n--- Processing Order ---");

        // Order requires Customer AND Staff — full association
        Order order1 = new Order("ORD001", customer1, staff1, "2025-04-27");
        order1.setPaymentStatus("Paid");

        // ── STEP 6: Add at least two OrderItems ──────────────
        // OrderItem now holds a REAL ClothingItem object (Design B)
        // purchasePrice is locked at time of purchase
        OrderItem orderItem1 = new OrderItem(item1, 2); // 2x Classic White Tee
        OrderItem orderItem2 = new OrderItem(item3, 1); // 1x Floral Summer Dress

        order1.addItem(orderItem1);
        order1.addItem(orderItem2);

        // ── STEP 7: Apply discount to one OrderItem ───────────
        // Apply $5.00 discount to the dress
        orderItem2.setDiscount(5.00);
        System.out.println("Discount of $5.00 applied to: " + orderItem2.getItemName());

        // ── STEP 8: Calculate order total using order.calculateTotal() ──
        // calculateTotal() always loops through items — always accurate
        // even after discounts change
        double orderTotal = order1.calculateTotal();

        System.out.println("\nOrder ID      : " + order1.getOrderID());
        System.out.println("Customer      : " + order1.getCustomer().getName());
        System.out.println("Processed by  : " + order1.getStaff().getName());
        System.out.println("Date          : " + order1.getOrderDate());
        System.out.println("Payment       : " + order1.getPaymentStatus());

        System.out.println("\nItems Ordered:");
        for (OrderItem oi : order1.getItems()) {
            System.out.println("  - " + oi.getItemName()
                    + " | Size: " + oi.getSize()
                    + " | Color: " + oi.getColor()
                    + " | Qty: " + oi.getQuantity()
                    + " | Purchase Price: $" + oi.getPurchasePrice()
                    + " | Discount: $" + oi.getDiscount()
                    + " | Subtotal: $" + oi.calculateSubtotal());
        }
        System.out.printf("Order Total   : $%.2f%n", orderTotal);

        // ── STEP 9: Confirm order — reduces stock ─────────────
        // Stock is only reduced AFTER order is confirmed
        // This is important: don't reduce stock until customer actually buys
        System.out.println("\n--- Confirming Order ---");
        System.out.println("Stock BEFORE confirmation:");
        System.out.println("  " + item1.getItemName() + " stock: " + item1.getStock());
        System.out.println("  " + item3.getItemName() + " stock: " + item3.getStock());

        order1.confirmOrder(); // confirms order AND reduces stock automatically

        System.out.println("Stock AFTER confirmation:");
        System.out.println("  " + item1.getItemName() + " stock: " + item1.getStock());
        System.out.println("  " + item3.getItemName() + " stock: " + item3.getStock());

        // ── STEP 10: Create receipt FROM the completed order ──
        // Receipt connects to the full Order object — not duplicating data
        // finalAmount is calculated from order.calculateTotal() at receipt creation
        System.out.println("\n--- Receipt ---");
        Receipt receipt1 = new Receipt("REC001", order1, "Credit Card");

        System.out.println("Receipt ID     : " + receipt1.getReceiptId());
        System.out.println("Order ID       : " + receipt1.getOrderID());
        System.out.println("Customer Name  : " + receipt1.getCustomerName());
        System.out.println("Staff Name     : " + receipt1.getStaffName());
        System.out.println("Payment Method : " + receipt1.getPaymentMethod());

        System.out.println("\nItems on Receipt:");
        for (OrderItem oi : receipt1.getOrder().getItems()) {
            System.out.println("  - " + oi.getItemName()
                    + " x" + oi.getQuantity()
                    + " | Purchase Price: $" + oi.getPurchasePrice()
                    + " | Discount: $" + oi.getDiscount()
                    + " | Subtotal: $" + oi.calculateSubtotal());
        }
        System.out.printf("Final Total    : $%.2f%n", receipt1.getFinalAmount());

        // ════════════════════════════════════════════════════
        // STEP 11: STATIC COUNTERS SUMMARY
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