package repository.impl;

import db.DBConnection;
import model.dto.Item;
import repository.ItemRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepositoryImpl implements ItemRepository {

    @Override
    public ResultSet getAllItems() {
        String SQL = "SELECT * FROM item";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            ResultSet resultSet = psTm.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItem(Item item){
        String SQL = "INSERT INTO item VALUES (?, ?, ?, ?, ?)";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);

            psTm.setObject(1,item.getItemCode());
            psTm.setObject(2,item.getDescription());
            psTm.setObject(3,item.getPackSize());
            psTm.setObject(4,item.getUnitPrice());
            psTm.setObject(5,item.getQtyOnHand());
            psTm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItem(String itemCode){
        String Sql = "DELETE FROM item WHERE ItemCode = ?";
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTM = connection.prepareStatement(Sql);
            psTM.setObject(1,itemCode);
            psTM.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(Item newItem){
        String SQL = "UPDATE item SET Description = ? ,PackSize = ? ,UnitPrice = ? ,QtyOnHand = ? WHERE ItemCode =?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setString(5,newItem.getItemCode());
            psTm.setString(1,newItem.getDescription());
            psTm.setString(2,newItem.getPackSize());
            psTm.setDouble(3,newItem.getUnitPrice());
            psTm.setInt(4,newItem.getQtyOnHand());
            psTm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResultSet searchItem(String itemCode) {
        String SQL = "SELECT * FROM item WHERE ItemCode = ?";
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,itemCode);
            ResultSet resultSet = psTm.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//Update the item table, once the order is placed by the user.
    @Override
    public boolean updateItemQuntity(String itemCode, int itemQuantity) throws SQLException {
        String SQL = "UPDATE item SET QtyOnHand = QtyOnHand - ? WHERE ItemCode = ?";
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement psTm = connection.prepareStatement(SQL);
        psTm.setString(2,itemCode);
        psTm.setInt(1,itemQuantity);
//        Return True or False
        return psTm.executeUpdate()>0;
    }
}
