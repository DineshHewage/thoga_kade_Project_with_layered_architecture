package service.impl;

import model.dto.Customer;
import model.dto.Item;
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
}
