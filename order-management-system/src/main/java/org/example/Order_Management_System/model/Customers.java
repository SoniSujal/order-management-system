package org.example.Order_Management_System.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customers {

    private int customerID;

    private String fullName;

    private String email;

    public Customers(String fullName, String email) {
        this.email = email;
        this.fullName = fullName;
    }
}
