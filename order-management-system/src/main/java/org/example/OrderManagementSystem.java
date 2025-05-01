    package org.example;

    import org.example.Order_Management_System.controller.CustomerAccessController;
    import org.example.Order_Management_System.exception.CustomerServiceException;


    public class OrderManagementSystem {
        public static void main(String[] args) throws CustomerServiceException {
            CustomerAccessController.startCustomerMenu();
        }
    }