package service.impl;

import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.OrderDetail;
import model.dto.Orders;
import repository.OrderDetailRepository;
import repository.impl.OrderDetailRepositoryImpl;
import service.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService {
    OrderDetailRepository orderDetailRepository = new OrderDetailRepositoryImpl();

    @Override
    public boolean addOrderDetails(Orders order, ObservableList<CartItem> cartOrder) {
        for (CartItem cartItem : cartOrder) {
            boolean success = orderDetailRepository.addOrderDetails(
                    new OrderDetail(
                            order.getOrderId(),
                            cartItem.getItemCode(),
                            cartItem.getItemQuantity(),
                            cartItem.getDiscount()
                    )
            );
            if (!success) {
                return false;
            }
        }
        return true;
    }
}
