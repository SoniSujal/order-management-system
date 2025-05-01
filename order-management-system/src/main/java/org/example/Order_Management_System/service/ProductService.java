package org.example.Order_Management_System.service;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.config.DbConnection;
import org.example.Order_Management_System.model.Products;
import org.example.Order_Management_System.utility.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ProductService {

    public static Map<Integer,Products> allProducts = new HashMap<>();

    public static void getAllProducts() {

        try(Connection connection = DbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQueries.GET_ALL_PRODUCTS)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                allProducts.put(resultSet.getInt(1),new Products(resultSet.getInt(1), resultSet.getString(2), resultSet.getDouble(3), resultSet.getInt(4)));
            }

        } catch (Exception e) {
            log.error("Error fetching products from database", e);
            System.out.println("❌ Failed to fetch products.");
        }
    }

    public static void updateProductStock(Connection connection,List<Products> products) {

        try(PreparedStatement statement = connection.prepareStatement(SqlQueries.UPDATE_PRODUCT_STOCK_BY_ID)) {
            connection.setAutoCommit(false);

            for(Products product : products) {
                statement.setInt(2, product.getProductID());
                statement.setInt(1, product.getStock());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            log.info("Product stock updated successfully.");

        } catch (SQLException e) {
            log.error("Error occurred while updating product stock. Rolling back transaction.", e);
            log.error("Failed to roll back transaction after error.", e);
        }
    }
}
