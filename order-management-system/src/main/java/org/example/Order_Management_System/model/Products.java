package org.example.Order_Management_System.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    private int productID;

    private String productName;

    private double price;

    private int stock;

    public Products(int productID, int stock) {
        this.productID = productID;
        this.stock = stock;
    }
}
