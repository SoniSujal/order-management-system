package org.example.Order_Management_System.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItems {

    private int orderItemId;

    private int orderID;

    private int productID;

    private int quantity;

    public OrderItems(int productID, int quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItems{" +
                "orderItemId=" + orderItemId +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", Quantity=" + quantity +
                '}';
    }
}
