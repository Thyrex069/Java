package Model;

public class ClothingItem {

    // ── Fields ───────────────────────────────────────────────
    private String itemCode;
    private String itemName;
    private String category;
    private String size;
    private String color;
    private String gender;
    private double price;
    private int stock;

    // ── Static counter ───────────────────────────────────────
    // Counts how many ClothingItem objects have been created
    private static int itemCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public ClothingItem(String itemCode, String itemName, String category,
            String size, String color, String gender) {
        setItemCode(itemCode);
        setItemName(itemName);
        setCategory(category);
        setSize(size);
        setColor(color);
        setGender(gender);
        itemCount++; // increment every time a new ClothingItem is created
    }

    // ── Validation helper ────────────────────────────────────
    private void validateString(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            System.out.println(fieldName + " cannot be null or empty");
        }
    }

    // ── Static method ────────────────────────────────────────
    public static int getItemCount() {
        return itemCount;
    }

    // ── Getters ──────────────────────────────────────────────
    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getGender() {
        return gender;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // ── Setters ──────────────────────────────────────────────
    private void setItemCode(String itemCode) {
        validateString(itemCode, "Item Code");
        this.itemCode = itemCode;
    }

    public void setItemName(String itemName) {
        validateString(itemName, "Item Name");
        this.itemName = itemName;
    }

    public void setCategory(String category) {
        validateString(category, "Category");
        this.category = category;
    }

    public void setSize(String size) {
        validateString(size, "Size");
        this.size = size;
    }

    public void setColor(String color) {
        validateString(color, "Color");
        this.color = color;
    }

    public void setGender(String gender) {
        validateString(gender, "Gender");
        this.gender = gender;
    }

    public void setPrice(double price) {
        if (price < 0) {
            System.out.println("Price cannot be negative");
        } else {
            this.price = price;
        }
    }

    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("Stock cannot be negative");
        } else {
            this.stock = stock;
        }
    }

    // ── Reduce stock method ──────────────────────────────────
    // Called after order is confirmed — reduces stock by quantity purchased
    public void reduceStock(int quantity) {
        if (quantity <= 0) {
            System.out.println("Quantity must be greater than zero");
        } else if (quantity > stock) {
            System.out.println("Not enough stock for: " + itemName);
        } else {
            this.stock -= quantity;
        }
    }
}
