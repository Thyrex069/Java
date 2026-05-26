package Model;

import Interface.Displayable;
import Interface.Calculatable;

public class OrderItem implements Displayable, Calculatable {

    // ── Fields ───────────────────────────────────────────────
    private int          orderItemId;
    private ClothingItem item;          // real object — not just a name/ID string
    private int          quantity;
    private double       discountPercent;
    private double       purchasePrice;  // locked at purchase time — survives price changes

    // ── Static counter ───────────────────────────────────────
    private static int orderItemCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public OrderItem(int orderItemId, ClothingItem item, int quantity) {
        setOrderItemId(orderItemId);
        this.item = item;
        setQuantity(quantity);
        this.discountPercent = 0;
        this.purchasePrice   = (item != null) ? item.getPrice() : 0;
        orderItemCount++;
    }

    // ── Static method ────────────────────────────────────────
    public static int getOrderItemCount() { return orderItemCount; }

    // ── Getters ──────────────────────────────────────────────
    public int          getOrderItemId()    { return orderItemId; }
    public ClothingItem getItem()           { return item; }
    public int          getQuantity()       { return quantity; }
    public double       getDiscountPercent(){ return discountPercent; }
    public double       getPurchasePrice()  { return purchasePrice; }

    // ── Setters ──────────────────────────────────────────────
    private void setOrderItemId(int orderItemId) {
        this.orderItemId = (orderItemId > 0) ? orderItemId : 0;
    }

    public void setQuantity(int quantity) {
        this.quantity = (quantity > 0) ? quantity : 1;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = (discountPercent >= 0 && discountPercent <= 100)
                ? discountPercent : 0;
    }

    // ── Stock helpers (delegates to ClothingItem's StockManageable methods) ──
    public boolean hasEnoughStock() {
        if (item == null) return false;
        return item.hasEnoughStock(quantity);
    }

    public boolean reduceItemStock() {
        if (item == null) return false;
        return item.reduceStock(quantity);
    }

    // ── Calculatable interface ────────────────────────────────
    // Formula: purchasePrice × quantity, then subtract discount amount
    @Override
    public double calculate() {
        double subtotalBeforeDiscount = purchasePrice * quantity;
        double discountAmount = subtotalBeforeDiscount * discountPercent / 100;
        return subtotalBeforeDiscount - discountAmount;
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        if (item == null) {
            System.out.println("    [Invalid Item]");
            return;
        }
        System.out.printf(
            "    - %-22s | Size: %-3s | Color: %-7s | Qty: %-2d | Price: $%-6.2f | Disc: %5.1f%% | Subtotal: $%.2f%n",
            item.getItemName(), item.getSize(), item.getColor(), quantity, purchasePrice, discountPercent, calculate()
        );
    }
}
