package repository;

import model.dto.Item;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface ItemRepository {

    ResultSet getAllItems();

    void addItem(Item item);

    void deleteItem(String itemCode);

    void updateItem(Item newItem);

    ResultSet searchItem(String itemCode);
}
