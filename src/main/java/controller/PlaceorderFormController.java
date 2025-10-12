package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import model.dto.CardItem;
import model.dto.Customer;
import model.dto.Item;
import model.dto.Orders;
import service.PlaceOrderService;
import service.impl.PlaceOrderServiceImpl;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PlaceorderFormController implements Initializable {

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
    private TableView<CardItem> tblAddtoCart;

    @FXML
    private JFXTextField txtOrderId;

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

    ObservableList<CardItem> addtoCartList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//      ---------------Bind data to the table---------------
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("itemUnitPrice"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        tblAddtoCart.setItems(addtoCartList);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        CardItem cardItem = new CardItem(
                txtItemCode.getText(),
                txtItemDescription.getText(),
                Integer.parseInt(txtQuantity.getText()),
                Double.parseDouble(txtUnitPrice.getText()),
                Double.parseDouble(txtDiscount.getText()),
                getTotal()
        );
        addtoCartList.add(cardItem);
        clearFields();
        calcukateNewTolat();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        Orders order = new Orders(
                txtOrderId.getText(),
                LocalDate.now(),
                txtCustomerId.getText()
        );

        placeOrderService.placeOrder(order,addtoCartList);
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

    private double getTotal(){
        double total = (Double.parseDouble(txtUnitPrice.getText()) * Integer.parseInt(txtQuantity.getText())) - Double.parseDouble(txtDiscount.getText());
        return total;
    }

    private void clearFields() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtItemCode.clear();
        txtItemDescription.clear();
        txtUnitPrice.clear();
        txtDiscount.clear();
        txtQuantity.clear();
    }

    private void calcukateNewTolat(){
        double netTotal = 0;
        for (CardItem cardItem : addtoCartList) {
            netTotal += cardItem.getTotalPrice();
            lblNetTotal.setText(String.valueOf(netTotal));
        }
    }
}
