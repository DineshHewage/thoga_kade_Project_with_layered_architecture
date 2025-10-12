package service;

import javafx.collections.ObservableList;
import model.dto.CardItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Orders;

public interface PlaceOrderService {
    Customer getCustomer(String text);

    Item searchItem(String text);

    void placeOrder(Orders order, ObservableList<CardItem> addtoCartList);
}
