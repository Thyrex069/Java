public class Main {
    public static void main(String[] args) {

        // --- Customer ---
        Customer c1 = new Customer();
        c1.name  = "Vimean";
        c1.phone = "012345678";
        c1.email = "vimean@gmail.com";

        System.out.println("=== Customer ===");
        System.out.println("Name  : " + c1.name);
        System.out.println("Phone : " + c1.phone);
        System.out.println("Email : " + c1.email);

        // --- Staff ---
        Staff s1 = new Staff();
        s1.name   = "Sokha";
        s1.role   = "Cashier";
        s1.salary = 250.00;

        System.out.println("\n=== Staff ===");
        System.out.println("Name   : " + s1.name);
        System.out.println("Role   : " + s1.role);
        System.out.println("Salary : $" + s1.salary);

        // --- Clothing Item ---
        ClothingItem item1 = new ClothingItem();
        item1.itemName = "T-Shirt";
        item1.size     = "M";
        item1.color    = "Black";
        item1.price    = 12.99;
        item1.stock    = 50;

        System.out.println("\n=== Clothing Item ===");
        System.out.println("Item  : " + item1.itemName);
        System.out.println("Size  : " + item1.size);
        System.out.println("Color : " + item1.color);
        System.out.println("Price : $" + item1.price);
        System.out.println("Stock : " + item1.stock);

        // --- Order ---
        Order o1 = new Order();
        o1.orderID      = "ORD-001";
        o1.customerName = "Vimean";
        o1.totalPrice   = 12.99;
        o1.status       = "Paid";

        System.out.println("\n=== Order ===");
        System.out.println("Order ID : " + o1.orderID);
        System.out.println("Customer : " + o1.customerName);
        System.out.println("Total    : $" + o1.totalPrice);
        System.out.println("Status   : " + o1.status);
    }    
}
