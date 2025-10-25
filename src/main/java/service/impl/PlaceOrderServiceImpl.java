package service.impl;

import db.DBConnection;
import javafx.collections.ObservableList;
import model.dto.CartItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Orders;
import service.*;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    ItemService itemService = new ItemServiceImp();
    CustomerService customerService = new CustomerServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    OrderDetailService orderdetailService = new OrderDetailServiceImpl();

    @Override
    public Customer getCustomer(String text) {
        return customerService.getCustomer(text);
    }

    @Override
    public Item searchItem(String text) {
        return itemService.searchItem(text);
    }



    @Override
    public void placeOrder(Orders order, ObservableList<CartItem> cartOrder) throws SQLException {
        Connection connection = null;
            try {
                connection = DBConnection.getInstance().getConnection();
                connection.setAutoCommit(false);

                //Update the order table
                boolean isOrderUpdated = orderService.addOrder(order);
                if(isOrderUpdated) {
                    //Update the OrderDetail table
                    boolean isOrderDetailUpdated = orderdetailService.addOrderDetails(order, cartOrder);
                    if(isOrderDetailUpdated) {
                        // Update the Item table
                        boolean isItemUpdated = itemService.updateItemQty(cartOrder);
                        if(isItemUpdated) {
                            connection.commit();
                            return;
                        }
                    }
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }finally {
                connection.setAutoCommit(true);
            }
    }
}
