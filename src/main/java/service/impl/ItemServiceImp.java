package service.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.dto.CartItem;
import model.dto.Item;
import repository.ItemRepository;
import repository.impl.ItemRepositoryImpl;
import service.ItemService;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemServiceImp implements ItemService {
    ItemRepository itemRepository = new ItemRepositoryImpl();

    @Override
    public ObservableList<Item> getAll() {
        ObservableList<Item> itemObservableList = FXCollections.observableArrayList();
        try {
            ResultSet allItems = itemRepository.getAllItems();
            while (allItems.next()) {
                itemObservableList.add(new Item(
                        allItems.getString("ItemCode"),
                        allItems.getString("Description"),
                        allItems.getString("PackSize"),
                        allItems.getDouble("UnitPrice"),
                        allItems.getInt("QtyOnHand"))
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return itemObservableList;
    }

    @Override
    public void addItem(Item newItem) {
        itemRepository.addItem(newItem);
    }

    @Override
    public void deleteItem(String itemCode) {
        itemRepository.deleteItem(itemCode);

    }

    @Override
    public void updateItem(Item updateItem) {
        itemRepository.updateItem(updateItem);
    }

    @Override
    public Item searchItem(String itemCode) {
        try {
            ResultSet resultSet = itemRepository.searchItem(itemCode);
            if (resultSet.next()) {
                // ✅ Data found — create and return the Item
                return new Item(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("PackSize"),
                        resultSet.getDouble("UnitPrice"),
                        resultSet.getInt("QtyOnHand")
                );
            }else {
                // ⚠️ No matching data found
                Alert alert = new Alert(Alert.AlertType.WARNING,"Item with code not found in the database.");
                alert.showAndWait();
                return null;
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Error while searching for item.");
            alert.show();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateItemQty(ObservableList<CartItem> cartOrder) {
        for(CartItem cartItem : cartOrder) {
            try {
//              Return True or False
                return itemRepository.updateItemQuntity(cartItem.getItemCode(), cartItem.getItemQuantity());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
