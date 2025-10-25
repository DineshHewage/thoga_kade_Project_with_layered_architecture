package repository;

import model.dto.OrderDetail;

public interface OrderDetailRepository {
    Boolean addOrderDetails(OrderDetail orderDetail);
}
