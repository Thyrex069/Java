package Model;

import Interface.Displayable;
import Interface.Printable;
import java.util.ArrayList;

public class Receipt implements Displayable, Printable {

    // ── Fields ───────────────────────────────────────────────
    private String receiptId;
    private Order  order;           // connects to the real Order — no data duplication
    private String paymentMethod;
    private double finalAmount;     // snapshot of total at receipt creation time
    private String receiptDate;
    private boolean valid;

    // ── Static counter ───────────────────────────────────────
    private static int receiptCount = 0;

    // ── Constructor ──────────────────────────────────────────
    // Receipt is only valid when order is both Confirmed AND Paid
    public Receipt(String receiptId, Order order, String paymentMethod, String receiptDate) {
        this.receiptId     = cleanText(receiptId,     "NO_RECEIPT_ID");
        this.order         = order;
        this.paymentMethod = cleanText(paymentMethod, "Unknown Method");
        this.receiptDate   = cleanText(receiptDate,   "No Date");

        if (order != null && order.isConfirmed() && order.isPaid()) {
            this.finalAmount = order.calculate();
            this.valid       = true;
            receiptCount++;
        } else {
            this.finalAmount = 0;
            this.valid       = false;
            System.out.println("Invalid receipt: order must be confirmed and paid first.");
        }
    }

    // ── Validation helper ────────────────────────────────────
    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) return defaultValue;
        return value.trim();
    }

    // ── Static method ────────────────────────────────────────
    public static int getReceiptCount() { return receiptCount; }

    // ── Getters ──────────────────────────────────────────────
    public String  getReceiptId()    { return receiptId; }
    public Order   getOrder()        { return order; }
    public String  getPaymentMethod(){ return paymentMethod; }
    public double  getFinalAmount()  { return finalAmount; }
    public String  getReceiptDate()  { return receiptDate; }
    public boolean isValid()         { return valid; }

    // ── Printable interface ───────────────────────────────────
    @Override
    public void printReceipt() {
        if (!valid || order == null) {
            System.out.println("Cannot print invalid receipt.");
            return;
        }
        System.out.println("====================================================================================================");
        System.out.println("                                           SALES RECEIPT                                            ");
        System.out.println("====================================================================================================");
        System.out.printf( "  Receipt ID     : %s%n", receiptId);
        System.out.printf( "  Receipt Date   : %s%n", receiptDate);
        System.out.printf( "  Order ID       : %s%n", order.getOrderId());
        System.out.printf( "  Payment Method : %s%n", paymentMethod);
        if (order.getCustomer() != null) {
            System.out.printf( "  Customer       : %s%n", order.getCustomer().getName());
        }
        if (order.getStaff() != null) {
            System.out.printf( "  Processed By   : %s%n", order.getStaff().getName());
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.println("  Items Purchased:");
        ArrayList<OrderItem> items = order.getOrderItemsCopy();
        for (OrderItem item : items) {
            item.displayInfo();
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
        System.out.printf( "  Final Amount   : $%.2f%n", finalAmount);
        System.out.printf( "  Receipt Count  : %d%n", receiptCount);
        System.out.println("====================================================================================================");
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        printReceipt(); // displayInfo() delegates to printReceipt()
    }
}
