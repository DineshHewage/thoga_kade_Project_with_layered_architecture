package service;

import model.dto.Customer;
import model.dto.Item;

public interface PlaceOrderService {
    Customer getCustomer(String text);

    Item searchItem(String text);
}
