package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Orders;

public interface OrderDetailService {

    boolean addOrderDetails(Orders order, ObservableList<CartItem> cartOrder);
}
