package org.example.Order_Management_System.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.enums.Constants;
import org.example.Order_Management_System.exception.CustomerServiceException;
import org.example.Order_Management_System.model.Customers;
import org.example.Order_Management_System.service.CustomerService;
import org.example.Order_Management_System.service.ProductService;
import org.example.Order_Management_System.utility.InputHelper;

import java.util.Scanner;

@Slf4j
public class CustomerAccessController {

    public static Scanner sc = new Scanner(System.in);

    static {
        try {
            CustomerService.getAllCustomer();
        } catch (CustomerServiceException e) {
            log.error("Failed to get customer by ID", e);
        }
        ProductService.getAllProducts();}

    public static void startCustomerMenu() throws CustomerServiceException {

        boolean flag = true;

        while(flag) {
            System.out.println("\n=========== 👤 CUSTOMER ACCESS MENU ===========");
            System.out.println("1️  Register New Customer");
            System.out.println("2️  Login Existing Customer");
            System.out.println("3️  ❌ Exit");
            System.out.println("===============================================");
            System.out.print("👉 Please enter your choice (1-3): ");

            int Choice = InputHelper.promptForId(Constants.Menu);
            if(Choice == 0) continue;

            switch (Choice) {
                case 1 :

                    String name = InputHelper.promptForName(Constants.Customers);
                    if(name.equals("-1")) continue;

                    String Email = InputHelper.promptForEmail();
                    if(Email.equals("-1")) continue;

                    Customers customer = new Customers(name, Email);

                    CustomerService.addCustomer(customer);
                    break;

                case 2 :

                    int id = InputHelper.promptForId(Constants.Customers);
                    if(id == 0) continue;
                    try {
                        if(!CustomerService.getCustomerById(id)) continue;
                        boolean flag1 = true;

                        while (flag1) {
                            System.out.println("\n============ 🛒 ORDER MANAGEMENT MENU ============");
                            System.out.println("1️  Insert Order by Customer ");
                            System.out.println("2️  Fetch Order Details by Customer ID");
                            System.out.println("3️  Update Product Stock");
                            System.out.println("4️  🚪 Logout");
                            System.out.println("==================================================");
                            System.out.print("👉 Please enter your choice (1-4): ");
                            int Choice1 = sc.nextInt();

                            switch (Choice1) {
                                case 1 :
                                    ProductService.getAllProducts();
                                    CustomerOrderController.addOrders(id);
                                    break;
                                case 2 :
                                    CustomerOrderController.getAllOrdersById(id);
                                    break;
                                case 3 :
                                    CustomerOrderController.updateProductStock();
                                    break;
                                case 4 :
                                    System.out.println("🔒 Logged out successfully.\n");
                                    flag1 = false;
                                    break;
                                default :
                                    log.warn("❗ Invalid input! Please enter a choice between 1 and 4.");
                            }
                        }
                    } catch (Exception e ) {
                        log.error("❌ Invalid input format! Please try again.");
                        e.printStackTrace();
                        e.getMessage();
                    }
                    break;

                case 3 :

                    System.out.println("Exiting the system. Goodbye!");
                    flag = false;
                    sc.close();
                    break;

                default :
                    log.warn("❗ Invalid input! Please enter a choice between 1 and 3.");            }
        }
    }
}
