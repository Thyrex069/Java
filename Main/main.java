package Main;

import Model.ClothingItem;
import Model.ClothingShopSystem;
import Model.Customer;
import Model.Order;
import Model.OrderItem;
import Model.Receipt;
import Model.Staff;

public class Main {

    public static void main(String[] args) {

        // ── Create the system ─────────────────────────────────────────────────
        ClothingShopSystem system = new ClothingShopSystem("CAM Clothing Shop");

        // ── Step 1: Create customers ──────────────────────────────────────────
        Customer customer1 = new Customer("C001", "Emma Davis",  "012345678", "Gold");
        Customer customer2 = new Customer("C002", "Dara Sok",    "098765432", "Regular");

        system.addCustomer(customer1);
        system.addCustomer(customer2);

        // ── Step 2: Create staff ──────────────────────────────────────────────
        Staff staff1 = new Staff("S001", "Sokha", "011111111",
                                 "Cashier", "Morning", "2026-05-18", 450);
        system.addStaff(staff1);

        // ── Step 3: Create clothing items ─────────────────────────────────────
        ClothingItem tee    = new ClothingItem("IT001", "Classic White Tee", "M",  "White", "Unisex",  "T-Shirt", 29.90, 50);
        ClothingItem dress  = new ClothingItem("IT002", "Summer Dress",      "S",  "Blue",  "Women",   "Dress",   49.90, 20);
        ClothingItem jacket = new ClothingItem("IT003", "Denim Jacket",      "L",  "Blue",  "Unisex",  "Jacket",  79.90, 10);

        system.addClothingItem(tee);
        system.addClothingItem(dress);
        system.addClothingItem(jacket);

        System.out.println("Before order:");
        system.displayInventory();
        system.displayCategories();

        // ── Validate wrong item selection ─────────────────────────────────────
        ClothingItem wrongSelection = system.searchClothingItemById("IT999");
        if (wrongSelection == null) {
            System.out.println("\nInvalid item selection: item IT999 does not exist.");
        }

        // ── Step 4: Build order ───────────────────────────────────────────────
        Order order1 = new Order("ORD001", customer1, "2026-05-18");

        ClothingItem selectedItem1 = system.searchClothingItemById("IT001");
        ClothingItem selectedItem2 = system.searchClothingItemById("IT002");

        OrderItem orderItem1 = new OrderItem(1, selectedItem1, 2);
        OrderItem orderItem2 = new OrderItem(2, selectedItem2, 1);

        orderItem1.setDiscountPercent(10); // 10% discount on tee

        order1.addOrderItem(orderItem1);
        order1.addOrderItem(orderItem2);

        System.out.println("\nOrder total before confirmation: $" + order1.calculate());

        // ── Step 5: Place order (staff confirms, stock reduced here) ──────────
        system.placeOrder(order1, staff1);

        // ── Step 6: Process payment ───────────────────────────────────────────
        system.processPayment("ORD001");

        // ── Step 7: Create receipt ────────────────────────────────────────────
        Receipt receipt1 = system.createReceipt("REC001", order1, "Credit Card", "2026-05-18");

        // ── Display results ───────────────────────────────────────────────────
        System.out.println("\nAfter order:");
        order1.displayInfo();

        if (receipt1 != null) {
            receipt1.printReceipt();
        }

        // ── Search demo ───────────────────────────────────────────────────────
        Order foundOrder = system.searchOrderById("ORD001");
        if (foundOrder != null) {
            System.out.println("\nSearch result for Order ORD001:");
            foundOrder.displayInfo();
        }

        // ── Customer order history ────────────────────────────────────────────
        customer1.displayOrderHistory();

        // ── Staff info ────────────────────────────────────────────────────────
        staff1.displayInfo();

        // ── Inventory after order ─────────────────────────────────────────────
        system.displayInventory();

        // ── System summary ────────────────────────────────────────────────────
        system.displayInfo();

        // ── Static counters vs collection sizes ──────────────────────────────
        System.out.println("\nStatic counters vs collection size:");
        System.out.println("Customer.getCustomerCount()          : " + Customer.getCustomerCount());
        System.out.println("system.getCustomerListSize()         : " + system.getCustomerListSize());
        System.out.println("Staff.getStaffCount()                : " + Staff.getStaffCount());
        System.out.println("system.getStaffListSize()            : " + system.getStaffListSize());
        System.out.println("ClothingItem.getClothingItemCount()  : " + ClothingItem.getClothingItemCount());
        System.out.println("system.getInventorySize()            : " + system.getInventorySize());
        System.out.println("OrderItem.getOrderItemCount()        : " + OrderItem.getOrderItemCount());
        System.out.println("Order.getOrderCount()                : " + Order.getOrderCount());
        System.out.println("system.getOrderListSize()            : " + system.getOrderListSize());
        System.out.println("Receipt.getReceiptCount()            : " + Receipt.getReceiptCount());
        System.out.println("system.getReceiptListSize()          : " + system.getReceiptListSize());
    }
}
