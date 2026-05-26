package Model;

import Interface.Displayable;
import Interface.StockManageable;

public class ClothingItem implements Displayable, StockManageable {

    // ── Fields ───────────────────────────────────────────────
    private String itemId;
    private String itemName;
    private String size;
    private String color;
    private String gender;
    private String category;
    private double price;
    private int    stock;

    // ── Static counter ───────────────────────────────────────
    private static int clothingItemCount = 0;

    // ── Constructor ──────────────────────────────────────────
    public ClothingItem(String itemId, String itemName, String size,
                        String color, String gender, String category,
                        double price, int stock) {
        this.itemId    = cleanText(itemId,    "NO_ITEM_ID");
        this.itemName  = cleanText(itemName,  "Unknown Item");
        this.size      = cleanText(size,      "Free Size");
        this.color     = cleanText(color,     "Unknown Color");
        this.gender    = cleanText(gender,    "Unisex");
        this.category  = cleanText(category,  "General");
        setPrice(price);
        setStock(stock);
        clothingItemCount++;
    }

    // ── Validation helper ────────────────────────────────────
    private String cleanText(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) return defaultValue;
        return value.trim();
    }

    // ── Static method ────────────────────────────────────────
    public static int getClothingItemCount() { return clothingItemCount; }

    // ── Getters ──────────────────────────────────────────────
    public String getItemId()   { return itemId; }
    public String getItemName() { return itemName; }
    public String getSize()     { return size; }
    public String getColor()    { return color; }
    public String getGender()   { return gender; }
    public String getCategory() { return category; }
    public double getPrice()    { return price; }
    public int    getStock()    { return stock; }

    // ── Setters ──────────────────────────────────────────────
    public void setItemName(String itemName) {
        if (itemName != null && !itemName.trim().isEmpty()) this.itemName = itemName.trim();
    }
    public void setSize(String size) {
        if (size != null && !size.trim().isEmpty()) this.size = size.trim();
    }
    public void setColor(String color) {
        if (color != null && !color.trim().isEmpty()) this.color = color.trim();
    }
    public void setGender(String gender) {
        if (gender != null && !gender.trim().isEmpty()) this.gender = gender.trim();
    }
    public void setCategory(String category) {
        if (category != null && !category.trim().isEmpty()) this.category = category.trim();
    }
    public void setPrice(double price) {
        this.price = (price >= 0) ? price : 0;
    }
    public void setStock(int stock) {
        this.stock = (stock >= 0) ? stock : 0;
    }

    // ── StockManageable interface ─────────────────────────────
    @Override
    public boolean hasEnoughStock(int quantity) {
        return quantity > 0 && stock >= quantity;
    }

    @Override
    public boolean reduceStock(int quantity) {
        if (!hasEnoughStock(quantity)) {
            System.out.println("Not enough stock for " + itemName + ".");
            return false;
        }
        stock -= quantity;
        return true;
    }

    public void increaseStock(int quantity) {
        if (quantity > 0) stock += quantity;
    }

    // ── Displayable ──────────────────────────────────────────
    @Override
    public void displayInfo() {
        System.out.printf(
            "  ID: %-6s | %-20s | Size: %-3s | Color: %-7s | %-7s | Category: %-9s | Price: $%-6.2f | Stock: %d%n",
            itemId, itemName, size, color, gender, category, price, stock
        );
    }
}
