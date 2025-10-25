package service;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Orders;

import java.sql.SQLException;

public interface PlaceOrderService {
    Customer getCustomer(String text);

    Item searchItem(String text);

    void placeOrder(Orders order, ObservableList<CartItem> addtoCartList) throws SQLException;
}
