package repository.impl;

import db.DBConnection;
import model.dto.OrderDetail;
import repository.OrderDetailRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
//    Implements the orderDetailsRepository interface.

    @Override
    public Boolean addOrderDetails(OrderDetail orderDetail) {
//        Implementation of the 'addOrderDetails' method declared in the interface.
//        This method insert Order_Details data into orderDetail table.

        String SQL = "INSERT INTO orderdetail VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
//            Create a preparedStatement Object to insert data into the table.
            PreparedStatement psTm = connection.prepareStatement(SQL);
//            Set the first placeholder (?) in the SQL statment to the value return by orderDetail.getOrderID().
            psTm.setString(1,orderDetail.getOrderID());
            psTm.setString(2,orderDetail.getItemCode());
            psTm.setInt(3,orderDetail.getOrderQty());
            psTm.setDouble(4,orderDetail.getDiscount());
//            Return True or False
            return psTm.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
