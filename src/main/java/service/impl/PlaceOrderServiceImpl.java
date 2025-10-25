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
                // Get the single connection for this transaction
                connection = DBConnection.getInstance().getConnection();
                // Turn off auto-commit
                connection.setAutoCommit(false);

                // Execute operations *using the same connection*
                boolean isOrderUpdated = orderService.addOrder(order);
                // check first result before proceeding.
                if(isOrderUpdated) {
                    //Update the OrderDetail table
                    boolean isOrderDetailUpdated = orderdetailService.addOrderDetails(order, cartOrder);
                    if(isOrderDetailUpdated) {
                        // Update the Item table
                        boolean isItemUpdated = itemService.updateItemQty(cartOrder);
                        if(isItemUpdated) {
                            // All parts succeeded, commit the transaction
                            connection.commit();
                        }else {
                            connection.rollback();
                            throw new SQLException("Failed to update item quantity, transaction rolled back.");
                        }
                    }else {
                        connection.rollback();
                        throw new SQLException("Failed to save order details, transaction rolled back.");
                    }
                }else{
                    connection.rollback();
                    throw new SQLException("Failed to save order, transaction rolled back.");
                }
            } catch (SQLException e) {
                if(connection != null) {
                    connection.rollback();
                }
                throw e;
            }finally {
                if(connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            }
    }
}
