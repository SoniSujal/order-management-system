package org.example.Order_Management_System.service;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.config.DbConnection;
import org.example.Order_Management_System.exception.CustomerServiceException;
import org.example.Order_Management_System.model.Customers;
import org.example.Order_Management_System.utility.PrintUtils;
import org.example.Order_Management_System.utility.SqlQueries;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class CustomerService {

    public static final HashMap<Integer, Customers> allCustomers = new HashMap<>();

    public static void addCustomer(Customers customer) throws CustomerServiceException {
        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQueries.ADD_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, customer.getFullName());

//            changes suggestion
            List<String> allEmails = allCustomers.values().stream().map(Customers::getEmail).collect(Collectors.toList());
            if(allEmails.contains(customer.getEmail())) {
                throw new SQLException("Email Already Exists . Must Be Enter Unique !");
            }

            statement.setString(2, customer.getEmail());

            int rowsAffected = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            int customerId = 0;

            if (generatedKeys.next()) {
                customerId = generatedKeys.getInt(1);
            }

            allCustomers.put(customerId, new Customers(customerId,customer.getFullName(),customer.getEmail()));
            PrintUtils.printCustomer(allCustomers.get(customerId));

        } catch (SQLException e) {
            throw new CustomerServiceException("Failed to add customer  ", e);
        }
    }

    public static void getAllCustomer() throws CustomerServiceException{
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_ALL_CUSTOMERS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("cust_id");
                String name = resultSet.getString("full_name");
                String email = resultSet.getString("email");

                allCustomers.put(id, new Customers(id,name,email));
            }
        } catch (SQLException e) {
            throw new CustomerServiceException("Failed to fetch all customers", e);
        }
    }

    public static boolean getCustomerById(int id) throws CustomerServiceException{
        try {
            Optional<Customers> customerOpt = Optional.ofNullable(allCustomers.get(id));

            if (customerOpt.isPresent()) {
                Customers customer = customerOpt.get();
                System.out.println("✔ Welcome, " + customer.getFullName());
                log.info("Customer logged in: {}", customer.getEmail());
                return true;
            } else {
                log.warn("Invalid login attempt for customer Id: {}", id);
                System.out.println(" Invalid credentials.");
                return false;
            }
        } catch (Exception e) {
            throw new CustomerServiceException("Failed to get customer by ID", e);
        }
    }


}
