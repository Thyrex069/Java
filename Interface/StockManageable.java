package Interface;

public interface StockManageable {
    boolean hasEnoughStock(int quantity);
    boolean reduceStock(int quantity);
}
