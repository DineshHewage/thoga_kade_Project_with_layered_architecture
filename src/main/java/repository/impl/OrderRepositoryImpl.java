package repository.impl;

import db.DBConnection;
import model.dto.Orders;
import repository.Orderrepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderRepositoryImpl implements Orderrepository {

    @Override
    public boolean addOrder(Orders order){
        String SQL = "INSERT INTO orders VALUES (?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);

            psTm.setString(1,order.getOrderId());
            psTm.setDate(2, Date.valueOf(order.getOrderDate()));
            psTm.setString(3,order.getCustomerId());
            boolean sucess = psTm.executeUpdate()>0;
            return sucess;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
