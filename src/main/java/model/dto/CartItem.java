package model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartItem {
    private String itemCode;
    private String itemDescription;
    private int itemQuantity;
    private double itemUnitPrice;
    private double discount;
    private double totalPrice;
}
