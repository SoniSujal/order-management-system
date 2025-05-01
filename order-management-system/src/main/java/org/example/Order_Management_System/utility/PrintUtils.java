package org.example.Order_Management_System.utility;

import org.example.Order_Management_System.model.Customers;
import org.example.Order_Management_System.model.Products;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class PrintUtils {

    public static void printCustomer(Customers customer) {
        System.out.println("\n📋 Customer Information:");
        System.out.printf("%-10s %-20s %-30s%n", "Cust_ID", "Name", "Email");
        System.out.println("--".repeat(31));

        System.out.printf("%-10d %-20s %-30s%n",
                customer.getCustomerID(),
                customer.getFullName(),
                customer.getEmail());
        System.out.println();
    }

    public static void printProductsTable(Collection<Products> products) {
        System.out.println("\n🛒 Available Products:");
        System.out.println(String.format("%-10s %-20s %-10s %-10s", "ID", "Name", "Price", "Stock"));
        System.out.println("--".repeat(28));

        for (Products p : products) {
            System.out.println(String.format("%-10d %-20s %-10.2f %-10d",
                    p.getProductID(), p.getProductName(), p.getPrice(), p.getStock()));
        }
        System.out.println();
    }

    public static void printCustomerOrderDetails(ResultSet rs) throws SQLException {
        System.out.println("\n📦 Order Details:");
        String headerFormat = "%-8s %-20s %-25s %-10s %-23s %-10s %-20s %-10s %-10s %-12s%n";
        String rowFormat = "%-8d %-20s %-25s %-10d %-23s %-10d %-20s %-10.2f %-10d %-12.2f%n";

        System.out.printf(headerFormat,
                "CustID", "Full Name", "Email",
                "OrderID", "Order Date",
                "ProdID", "Product Name",
                "Price", "Qty", "Total");

        System.out.println("--".repeat(76));
        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.printf(rowFormat,
                    rs.getInt("cust_id"),
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getInt("order_id"),
                    rs.getTimestamp("order_date").toString(),
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getDouble("price"),
                    rs.getInt("quantity"),
                    rs.getDouble("total_price"));
        }

        if (!found) {
            System.out.println("❌ No order records found for the given customer ID.\n");
        }

        System.out.println();
    }
}
