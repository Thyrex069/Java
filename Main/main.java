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

        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 1] INITIAL STORE INVENTORY & CATEGORIES                              ");
        System.out.println("====================================================================================================");
        system.displayInventory();
        system.displayCategories();

        // ── Validate wrong item selection ─────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 2] TESTING INVALID CLOTHING ITEM SEARCH                              ");
        System.out.println("====================================================================================================");
        ClothingItem wrongSelection = system.searchClothingItemById("IT999");
        if (wrongSelection == null) {
            System.out.println("  Search Result: Clothing item with ID 'IT999' was not found (Correct behavior).");
        }

        // ── Step 4: Build order ───────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 3] BUILDING AND INITIATING AN ORDER                                  ");
        System.out.println("====================================================================================================");
        Order order1 = new Order("ORD001", customer1, "2026-05-18");

        ClothingItem selectedItem1 = system.searchClothingItemById("IT001");
        ClothingItem selectedItem2 = system.searchClothingItemById("IT002");

        OrderItem orderItem1 = new OrderItem(1, selectedItem1, 2);
        OrderItem orderItem2 = new OrderItem(2, selectedItem2, 1);

        orderItem1.setDiscountPercent(10); // 10% discount on tee

        order1.addOrderItem(orderItem1);
        order1.addOrderItem(orderItem2);

        System.out.printf("  Order subtotal calculated before confirmation: $%.2f%n", order1.calculate());

        // ── Step 5: Place order (staff confirms, stock reduced here) ──────────
        System.out.println("\n  Placing order through system...");
        system.placeOrder(order1, staff1);

        // ── Step 6: Process payment ───────────────────────────────────────────
        System.out.println("  Processing order payment...");
        system.processPayment("ORD001");

        // ── Step 7: Create receipt ────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 4] RECEIPT GENERATION & CUSTOMER CHECKOUT                            ");
        System.out.println("====================================================================================================");
        Receipt receipt1 = system.createReceipt("REC001", order1, "Credit Card", "2026-05-18");

        if (receipt1 != null) {
            receipt1.printReceipt();
        }

        // ── Display results ───────────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 5] DETAILED CONFIRMED ORDER VIEW                                     ");
        System.out.println("====================================================================================================");
        order1.displayInfo();

        // ── Search demo ───────────────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 6] SEARCHING FOR ORDER IN SYSTEM BY ID                               ");
        System.out.println("====================================================================================================");
        Order foundOrder = system.searchOrderById("ORD001");
        if (foundOrder != null) {
            System.out.println("  Order found successfully:");
            foundOrder.displayInfo();
        }

        // ── Customer order history ────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 7] CUSTOMER ACCOUNT & PURCHASE HISTORY                               ");
        System.out.println("====================================================================================================");
        customer1.displayOrderHistory();

        // ── Staff info ────────────────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 8] STAFF INFORMATION & EMPLOYMENT SUMMARY                            ");
        System.out.println("====================================================================================================");
        staff1.displayInfo();

        // ── Inventory after order ─────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 9] INVENTORY STATE AFTER ORDER FULFILLMENT                           ");
        System.out.println("====================================================================================================");
        system.displayInventory();

        // ── System summary ────────────────────────────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 10] SYSTEM STATUS SUMMARY                                            ");
        System.out.println("====================================================================================================");
        system.displayInfo();

        // ── Static counters vs collection sizes ──────────────────────────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 11] SYSTEM METRICS & STATIC CONTEXT VALIDATION                       ");
        System.out.println("====================================================================================================");
        System.out.printf("  %-38s : %d%n", "Customer.getCustomerCount()", Customer.getCustomerCount());
        System.out.printf("  %-38s : %d%n", "system.getCustomerListSize()", system.getCustomerListSize());
        System.out.printf("  %-38s : %d%n", "Staff.getStaffCount()", Staff.getStaffCount());
        System.out.printf("  %-38s : %d%n", "system.getStaffListSize()", system.getStaffListSize());
        System.out.printf("  %-38s : %d%n", "ClothingItem.getClothingItemCount()", ClothingItem.getClothingItemCount());
        System.out.printf("  %-38s : %d%n", "system.getInventorySize()", system.getInventorySize());
        System.out.printf("  %-38s : %d%n", "OrderItem.getOrderItemCount()", OrderItem.getOrderItemCount());
        System.out.printf("  %-38s : %d%n", "Order.getOrderCount()", Order.getOrderCount());
        System.out.printf("  %-38s : %d%n", "system.getOrderListSize()", system.getOrderListSize());
        System.out.printf("  %-38s : %d%n", "Receipt.getReceiptCount()", Receipt.getReceiptCount());
        System.out.printf("  %-38s : %d%n", "system.getReceiptListSize()", system.getReceiptListSize());

        // ── Step 12: Test order cancellation and stock restoration ───────────
        System.out.println("\n====================================================================================================");
        System.out.println("                         [STEP 12] TESTING ORDER CANCELLATION & STOCK RESTORATION                  ");
        System.out.println("====================================================================================================");
        Customer tempCustomer = new Customer("C999", "Test Customer", "000", "Regular");
        system.addCustomer(tempCustomer);

        Order order2 = new Order("ORD002", tempCustomer, "2026-05-18");
        ClothingItem denimJacket = system.searchClothingItemById("IT003");
        OrderItem tempOi = new OrderItem(3, denimJacket, 2);
        order2.addOrderItem(tempOi);

        System.out.println("  Initial stock of Denim Jacket (IT003)      : " + denimJacket.getStock());
        
        System.out.println("  Confirming order...");
        system.placeOrder(order2, staff1);
        System.out.println("  Stock after order confirmation (IT003)     : " + denimJacket.getStock());

        System.out.println("  Cancelling order...");
        order2.cancelOrder();
        System.out.println("  Stock after order cancellation (IT003)     : " + denimJacket.getStock());
    }
}
