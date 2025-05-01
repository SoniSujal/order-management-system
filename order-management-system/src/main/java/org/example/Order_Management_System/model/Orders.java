package org.example.Order_Management_System.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    private int orderID;

    private int customerID;

    private LocalDateTime dateTime;

}
