package service.impl;

import javafx.collections.ObservableList;
import model.dto.CardItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Orders;
import service.CustomerService;
import service.ItemService;
import service.PlaceOrderService;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    ItemService itemService = new ItemServiceImp();
    CustomerService customerService = new CustomerServiceImpl();

    @Override
    public Customer getCustomer(String text) {
        return customerService.getCustomer(text);
    }

    @Override
    public Item searchItem(String text) {
        return itemService.searchItem(text);
    }

    @Override
    public void placeOrder(Orders order, ObservableList<CardItem> addtoCartList) {

    }
}
