package service.impl;

import model.dto.Orders;
import repository.Orderrepository;
import repository.impl.OrderRepositoryImpl;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
    Orderrepository orderRepository = new OrderRepositoryImpl();

    @Override
    public boolean addOrder(Orders order) {
        boolean sucess = orderRepository.addOrder(order);
        return sucess;
    }
}