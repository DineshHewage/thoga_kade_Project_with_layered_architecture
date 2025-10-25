package repository;

import model.dto.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ItemRepository {

    ResultSet getAllItems();

    void addItem(Item item);

    void deleteItem(String itemCode);

    void updateItem(Item newItem);

    ResultSet searchItem(String itemCode);

    boolean updateItemQuntity(String itemCode, int itemQuantity) throws SQLException;
}
