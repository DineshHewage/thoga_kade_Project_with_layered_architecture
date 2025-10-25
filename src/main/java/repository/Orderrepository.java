package repository;

import model.dto.Orders;

public interface Orderrepository {
    boolean addOrder(Orders order);
}
