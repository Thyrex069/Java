package Model;

import Interface.Displayable;
import Interface.ClothingSearchable;
import Interface.OrderSearchable;
import java.util.ArrayList;
import java.util.HashSet;

public class ClothingShopSystem implements Displayable, ClothingSearchable, OrderSearchable {

    // ── Fields ───────────────────────────────────────────────
    private String              systemName;
    private ArrayList<Customer>    customers;
    private ArrayList<Staff>       staffMembers;
    private ArrayList<ClothingItem> inventory;
    private ArrayList<Order>       orders;
    private ArrayList<Receipt>     receipts;
    private HashSet<String>        categories; // unique category names only

    // ── Constructor ──────────────────────────────────────────
    public ClothingShopSystem(String systemName) {
        this.systemName   = (systemName == null || systemName.trim().isEmpty())
                          ? "Clothing Shop System" : systemName.trim();
        this.customers    = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
        this.inventory    = new ArrayList<>();
        this.orders       = new ArrayList<>();
        this.receipts     = new ArrayList<>();
        this.categories   = new HashSet<>();
    }

    // ── Add methods ──────────────────────────────────────────
    public boolean addCustomer(Customer customer) {
        if (customer == null) return false;
        customers.add(customer);
        return true;
    }

    public boolean addStaff(Staff staff) {
        if (staff == null) return false;
        staffMembers.add(staff);
        return true;
    }

    public boolean addClothingItem(ClothingItem item) {
        if (item == null) return false;
        inventory.add(item);
        categories.add(item.getCategory()); // HashSet handles duplicates automatically
        return true;
    }

    // ── ClothingSearchable interface ─────────────────────────
    @Override
    public ClothingItem searchClothingItemById(String itemId) {
        if (itemId == null) return null;
        for (ClothingItem item : inventory) {
            if (item.getItemId().equalsIgnoreCase(itemId.trim())) {
                return item;
            }
        }
        return null; // not found
    }

    // ── OrderSearchable interface ────────────────────────────
    @Override
    public Order searchOrderById(String orderId) {
        if (orderId == null) return null;
        for (Order order : orders) {
            if (order.getOrderId().equalsIgnoreCase(orderId.trim())) {
                return order;
            }
        }
        return null; // not found
    }

    // ── Additional search methods ────────────────────────────
    public Customer searchCustomerById(String customerId) {
        if (customerId == null) return null;
        for (Customer customer : customers) {
            if (customer.getCustomerId().equalsIgnoreCase(customerId.trim())) {
                return customer;
            }
        }
        return null;
    }

    public Staff searchStaffById(String staffId) {
        if (staffId == null) return null;
        for (Staff staff : staffMembers) {
            if (staff.getStaffId().equalsIgnoreCase(staffId.trim())) {
                return staff;
            }
        }
        return null;
    }

    // ── Place order ──────────────────────────────────────────
    // Staff confirms the order; only then is it stored and linked to customer
    public boolean placeOrder(Order order, Staff staff) {
        if (order == null) {
            System.out.println("Cannot place a null order.");
            return false;
        }
        if (searchOrderById(order.getOrderId()) != null) {
            System.out.println("Order ID already exists: " + order.getOrderId());
            return false;
        }
        boolean confirmed = staff.processOrder(order);
        if (!confirmed) {
            System.out.println("Order " + order.getOrderId() + " was not placed.");
            return false;
        }
        orders.add(order);
        if (order.getCustomer() != null) {
            order.getCustomer().addOrder(order); // link order to customer history
        }
        System.out.println("Order " + order.getOrderId() + " placed successfully.");
        return true;
    }

    // ── Process payment ──────────────────────────────────────
    public boolean processPayment(String orderId) {
        Order order = searchOrderById(orderId);
        if (order == null) {
            System.out.println("Payment failed: order not found.");
            return false;
        }
        boolean paid = order.markAsPaid();
        if (paid) {
            System.out.println("Payment processed successfully for order " + orderId + ".");
        } else {
            System.out.println("Payment failed for order " + orderId + ".");
        }
        return paid;
    }

    // ── Create receipt ───────────────────────────────────────
    // Receipt is only created after order is confirmed AND paid
    public Receipt createReceipt(String receiptId, Order order,
                                 String paymentMethod, String receiptDate) {
        if (order == null) {
            System.out.println("Cannot create receipt without an order.");
            return null;
        }
        if (!order.isConfirmed()) {
            System.out.println("Cannot create receipt. Order is not confirmed yet.");
            return null;
        }
        if (!order.isPaid()) {
            System.out.println("Cannot create receipt. Order is not paid yet.");
            return null;
        }
        Receipt receipt = new Receipt(receiptId, order, paymentMethod, receiptDate);
        if (receipt.isValid()) {
            receipts.add(receipt);
            return receipt;
        }
        return null;
    }

    // ── Display helpers ──────────────────────────────────────
    public void displayInventory() {
        System.out.println("====================================================================================================");
        System.out.println("                                            STORE INVENTORY                                         ");
        System.out.println("====================================================================================================");
        if (inventory.isEmpty()) {
            System.out.println("  No clothing items available.");
        } else {
            for (ClothingItem item : inventory) {
                item.displayInfo();
            }
        }
        System.out.println("====================================================================================================");
    }

    public void displayCategories() {
        System.out.println("Clothing Categories:");
        if (categories.isEmpty()) {
            System.out.println("  No categories yet.");
        } else {
            for (String category : categories) {
                System.out.println("  • " + category);
            }
        }
    }

    public void displayAllOrders() {
        System.out.println("Order History in " + systemName + ":");
        if (orders.isEmpty()) {
            System.out.println("  No orders yet.");
        } else {
            for (Order order : orders) {
                order.displayInfo();
            }
        }
    }

    // ── Defensive copy getters ───────────────────────────────
    public ArrayList<Customer>     getCustomersCopy()    { return new ArrayList<>(customers); }
    public ArrayList<Staff>        getStaffMembersCopy() { return new ArrayList<>(staffMembers); }
    public ArrayList<ClothingItem> getInventoryCopy()    { return new ArrayList<>(inventory); }
    public ArrayList<Order>        getOrdersCopy()       { return new ArrayList<>(orders); }
    public ArrayList<Receipt>      getReceiptsCopy()     { return new ArrayList<>(receipts); }

    // ── Size getters ─────────────────────────────────────────
    public int getCustomerListSize()  { return customers.size(); }
    public int getStaffListSize()     { return staffMembers.size(); }
    public int getInventorySize()     { return inventory.size(); }
    public int getOrderListSize()     { return orders.size(); }
    public int getReceiptListSize()   { return receipts.size(); }
    public int getCategorySize()      { return categories.size(); }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.println("+--------------------------------------------------------+");
        System.out.println("|               CLOTHING SHOP SYSTEM SUMMARY             |");
        System.out.println("+--------------------------------------------------------+");
        System.out.printf( "| System Name   : %-38s |%n", systemName);
        System.out.printf( "| Customers     : %-38d |%n", customers.size());
        System.out.printf( "| Staff Members : %-38d |%n", staffMembers.size());
        System.out.printf( "| Inventory Size: %-38d |%n", inventory.size());
        System.out.printf( "| Orders Placed : %-38d |%n", orders.size());
        System.out.printf( "| Receipts Gen  : %-38d |%n", receipts.size());
        System.out.printf( "| Categories    : %-38d |%n", categories.size());
        System.out.println("+--------------------------------------------------------+");
    }
}
