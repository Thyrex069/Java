package Model;

import java.util.ArrayList;

public class Receipt {
    // Encapsulation 
    private String receiptId;
    private String customerName;
    private double totalAmount;
    private String paymentMethod;

    // static variable 
    private static int receiptCount = 0;

    //  collection (store multiple items)
    private ArrayList<OrderItem> items;

    // Constructor 
    public Receipt(String receiptId, String customerName, String paymentMethod) {
        setReceiptId(receiptId);
        setCustomerName(customerName);
        setPaymentMethod(paymentMethod);
        this.totalAmount = 0.0;
        this.items = new ArrayList<>();
        receiptCount++;
    }

    // Add items
    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
            totalAmount += item.calculateSubtotal();
        }
    }

    // Getter 
    public String getReceiptId() {
        return receiptId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    // Static 
    public static int getReceiptCount() {
        return receiptCount;
    }

    // Setter
    public void setReceiptId(String receiptId) {
        if (receiptId != null && !receiptId.trim().isEmpty()) {
            this.receiptId = receiptId;
        }
    }

    public void setCustomerName(String customerName) {
        if (customerName != null && !customerName.trim().isEmpty()) {
            this.customerName = customerName;
        }
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod != null && !paymentMethod.trim().isEmpty()) {
            this.paymentMethod = paymentMethod;
        }
    }
}