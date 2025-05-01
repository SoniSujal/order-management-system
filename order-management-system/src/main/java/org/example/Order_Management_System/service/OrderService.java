package org.example.Order_Management_System.service;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.config.DbConnection;
import org.example.Order_Management_System.model.OrderItems;
import org.example.Order_Management_System.model.Products;
import org.example.Order_Management_System.utility.PrintUtils;
import org.example.Order_Management_System.utility.SqlQueries;

import java.sql.*;
import java.util.List;

@Slf4j
public class OrderService {

    public static void addOrders(int cust_id, List<OrderItems> orders, List<Products> updateStocks) {
        Connection connection =DbConnection.getConnection();

        try(PreparedStatement statement1 = connection.prepareStatement(SqlQueries.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(SqlQueries.INSERT_ORDER_ITEM)) {
            connection.setAutoCommit(false);

            statement1.setInt(1, cust_id);
            int orderCount = statement1.executeUpdate();
            if (orderCount == 0) {
                log.error("Failed to create the order.");
                return;
            }

            ResultSet generatedKey = statement1.getGeneratedKeys();
            int orderID = 0;
            if (generatedKey.next()) {
                orderID = generatedKey.getInt(1);
            }

            for(OrderItems order : orders) {
                statement2.setInt(1,orderID);
                statement2.setInt(2, order.getProductID());
                statement2.setInt(3, order.getQuantity());
                statement2.addBatch();
            }
            statement2.executeBatch();

            ProductService.updateProductStock(connection, updateStocks);
            connection.commit();

            log.info("Order with ID {} successfully placed for customer {}", orderID, cust_id);

        } catch (SQLException e) {
            try {
                log.error("Database error while adding order: {}", e.getMessage(), e);
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public static void getOrdersById (int cust_id) {
        try(Connection connection = DbConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_ORDER_DETAILS_BY_CUSTOMER_ID)) {

            statement.setInt(1,cust_id);
            ResultSet resultSet = statement.executeQuery();

            PrintUtils.printCustomerOrderDetails(resultSet);

        } catch (SQLException e) {
            log.error("Failed to retrieve orders for customer ID {}: {}", cust_id, e.getMessage(), e);
        }
    }

}
