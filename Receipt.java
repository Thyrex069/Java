public class Receipt {
    String receiptId;
    String customerName;
    String date;
    double totalAmount;
    String paymentMethod;

    //Getter
    public String getReceiptId() {
        return receiptId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    //Setter
    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }
    public void SetCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

}
