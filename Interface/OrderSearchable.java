package Interface;

import Model.Order;

public interface OrderSearchable {
    Order searchOrderById(String orderId);
}
