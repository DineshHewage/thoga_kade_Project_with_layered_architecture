package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import model.dto.Customer;
import model.dto.Item;
import service.PlaceOrderService;
import service.impl.PlaceOrderServiceImpl;

public class PlaceorderFormController {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXButton btnPlaceOrder;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblNetTotal;

    @FXML
    private TableView<?> tblOrderDetails;

    @FXML
    private JFXTextField txtCustomerId;

    @FXML
    private JFXTextField txtCustomerName;

    @FXML
    private JFXTextField txtDiscount;

    @FXML
    private JFXTextField txtItemCode;

    @FXML
    private JFXTextField txtItemDescription;

    @FXML
    private JFXTextField txtQuantity;

    @FXML
    private JFXTextField txtUnitPrice;

    PlaceOrderService placeOrderService = new PlaceOrderServiceImpl();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        String text = txtCustomerId.getText();
        Customer customer = placeOrderService.getCustomer(text);
        txtCustomerName.setText(customer.getName());
    }

    @FXML
    void txtItemCodeOnAction(ActionEvent event) {
        Item item = placeOrderService.searchItem(txtItemCode.getText());
        if (item != null) {
            txtItemDescription.setText(item.getDescription());
            txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        }else {
            txtItemDescription.clear();
            txtUnitPrice.clear();
        }
    }

    @FXML
    void txtQuantityOnAction(KeyEvent event) {

    }
}
