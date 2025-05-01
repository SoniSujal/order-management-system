package org.example.Order_Management_System.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.Order_Management_System.config.DbConnection;
import org.example.Order_Management_System.enums.Constants;
import org.example.Order_Management_System.model.OrderItems;
import org.example.Order_Management_System.model.Products;
import org.example.Order_Management_System.service.OrderService;
import org.example.Order_Management_System.service.ProductService;
import org.example.Order_Management_System.utility.InputHelper;
import org.example.Order_Management_System.utility.PrintUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CustomerOrderController {

    public static List<OrderItems> orders;

    public static void addOrders(int cust_id) {

        orders = new ArrayList<>();
        List<Products> updateStocks = new ArrayList<>();
        boolean flag = true;
        while (flag) {
            System.out.println("\n📥 Do you want to add a product to your cart?");
            flag = InputHelper.askToContinue();
            if (!flag) break;

            PrintUtils.printProductsTable(ProductService.allProducts.values());

            int productID = InputHelper.promptForId(Constants.Product);

            if(! ProductService.allProducts.containsKey(productID)) {
                log.warn("❌ Product ID '{}' does not exist. Please try again.", productID);
                    continue;
            }
            Products products = ProductService.allProducts.get(productID);

            if(products == null) {
                log.warn("❌ Product data is not available.");
                continue;
            }
            int availableStock = products.getStock();
            log.info("📦 Product ID [{}] available stock: {}", productID, (ProductService.allProducts.get(productID).getStock()));

            int quantity = InputHelper.promptForQuantity(Constants.Product);
            if(quantity == 0) continue;
            if(quantity > availableStock) {
                log.warn("⚠️ Quantity requested ({}) exceeds available stock ({}). Order rejected for this item.", quantity, availableStock);
                continue;
            }

            OrderItems order = new OrderItems(productID, quantity);
            orders.add(order);
            int updateStock = availableStock - quantity;
            products.setStock(updateStock);
            updateStocks.add(new Products(order.getProductID(), updateStock));

            log.info("✅ Added to cart: Product ID [{}], Quantity [{}], Remaining Stock [{}]", productID, quantity, updateStock);

        }

         if(orders.isEmpty()) {
             log.warn("🛒 Cart is empty. No items were purchased.");
             return;
         }
        log.info("💾 Placing order with {} item(s)...", orders.size());
        OrderService.addOrders(cust_id, orders, updateStocks);
    }

    public static void updateProductStock() {
        List<Products> changeStock = new ArrayList<>();
        while (true) {
            PrintUtils.printProductsTable( ProductService.allProducts.values());

            int productID = InputHelper.promptForId(Constants.Product);
            if(!ProductService.allProducts.containsKey(productID)) {
                log.warn("❌ Product ID '{}' does not exist. Please try again.", productID);
                continue;
            }
            log.info("🔄 Current stock for Product ID [{}]: {}", productID, (ProductService.allProducts.get(productID).getStock()));

            int newStock = InputHelper.promptForQuantity(Constants.Product);

            changeStock.add(new Products(productID, newStock));
            ProductService.allProducts.get(productID).setStock(newStock);

            log.info("✅ Stock updated for Product ID [{}]: New Stock = {}", productID, newStock);

            if(!InputHelper.askToContinue()) break;
        }
        try {
            Connection connection = DbConnection.getConnection();
            ProductService.updateProductStock(connection, changeStock);
            log.info("💾 All product stock changes saved to database.");
        } catch (Exception e) {
            log.error("❌ Failed to update stock in database: {}", e.getMessage());
        }
    }

    public static void getAllOrdersById(int cust_id) {
        log.info("📦 Fetching all orders for Customer ID: {}", cust_id);
        OrderService.getOrdersById(cust_id);
    }
}
