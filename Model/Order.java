package Model;

import java.util.List;

public class Order {
    private String orderID;
    private String orderDate;
    private Customer customer;
    private List<OrderItem> items;
    private double totalPrice;
    private String status;
    private String paymentStatus;
    private String shippingAddress;

    // Constructor
    public Order(String orderID, Customer customer, String orderDate) {
        setOrderID(orderID);
        setCustomer(customer);
        setOrderDate(orderDate);
        setItems(new java.util.ArrayList<>());
        setTotalPrice(0.0);
    }

    // Helper method for string validation
    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println("Error: " + fieldName + " cannot be null or empty");
        }
    }

    public String getOrderID() {
        return orderID;
    }

    private void setOrderID(String orderID) {
        validateString(orderID, "Order ID");
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    private void setOrderDate(String orderDate) {
        validateString(orderDate, "Order Date");
        this.orderDate = orderDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    private void setCustomer(Customer customer) {
        if (customer == null) {
            System.out.println("Error: Customer cannot be null");
        } else {
            this.customer = customer;
        }
    }

    public List<OrderItem> getItems() {
        return items;
    }

    private void setItems(List<OrderItem> items) {
        if (items == null) {
            System.out.println("Error: Items list cannot be null");
        } else {
            this.items = items;
        }
    }

    // Add an item and automatically update totalPrice
    public void addItem(OrderItem item) {
        if (item != null) {
            items.add(item);
            totalPrice += item.calculateSubtotal();
        }
    }

    // Recalculate total from scratch (useful if quantities/discounts change)
    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.calculateSubtotal();
        }
        return total;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void setTotalPrice(double totalPrice) {
        if (totalPrice < 0) {
            System.out.println("Error: Total price cannot be negative");
        } else {
            this.totalPrice = totalPrice;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        validateString(status, "Status");
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        validateString(paymentStatus, "Payment Status");
        this.paymentStatus = paymentStatus;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        validateString(shippingAddress, "Shipping Address");
        this.shippingAddress = shippingAddress;
    }
}