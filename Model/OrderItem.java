package Model;

import Interface.Displayable;

// OrderItem implements Displayable → must provide display()
public class OrderItem implements Displayable {

    // ── Fields ───────────────────────────────────────────────
    // Design B: stores a real ClothingItem object instead of just ID/name/price
    private ClothingItem item;
    private int quantity;
    private double discount;

    // purchasePrice locks in the price at the time of purchase.
    // This is important because ClothingItem.price may change later,
    // but the customer was charged the price at time of purchase.
    private double purchasePrice;

    // ── Static counter ───────────────────────────────────────
    private static int orderItemCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public OrderItem(ClothingItem item, int quantity) {
        setItem(item);
        setQuantity(quantity);
        this.discount = 0.0;
        // Lock in price at time of purchase
        this.purchasePrice = item.getPrice();
        orderItemCount++;
    }

    // ── Static method ────────────────────────────────────────
    public static int getOrderItemCount() {
        return orderItemCount;
    }

    // ── Calculate subtotal ───────────────────────────────────
    // Uses purchasePrice (locked at purchase time), not current item price
    public double calculateSubtotal() {
        double subtotal = purchasePrice * quantity;
        return subtotal - discount;
    }

    // ── Getters ──────────────────────────────────────────────
    public ClothingItem getItem()      { return item; }
    public int          getQuantity()  { return quantity; }
    public double       getDiscount()  { return discount; }
    public double       getPurchasePrice() { return purchasePrice; }

    // Convenience getters — access ClothingItem info directly from OrderItem
    public String getItemName() { return item.getItemName(); }
    public String getItemCode() { return item.getItemCode(); }
    public String getSize()     { return item.getSize(); }
    public String getColor()    { return item.getColor(); }

    // ── Setters ──────────────────────────────────────────────
    public void setItem(ClothingItem item) {
        if (item == null) {
            System.out.println("ClothingItem cannot be null");
        } else {
            this.item = item;
        }
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero");
        } else {
            this.quantity = quantity;
        }
    }

    public void setDiscount(double discount) {
        if (discount < 0) {
            System.out.println("Discount cannot be negative");
        } else {
            this.discount = discount;
        }
    }

    // ── Displayable interface method ──────────────────────────
    @Override
    public void display() {
        System.out.println("  - " + getItemName()
                + " | Size: "  + getSize()
                + " | Color: " + getColor()
                + " | Qty: "   + quantity
                + " | Price: $"      + purchasePrice
                + " | Discount: $"   + discount
                + " | Subtotal: $"   + calculateSubtotal());
    }
}
