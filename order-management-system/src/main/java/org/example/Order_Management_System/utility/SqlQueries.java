package org.example.Order_Management_System.utility;

public class SqlQueries {

    public final static String ADD_CUSTOMER = "INSERT INTO customers (full_name, email) VALUES ( ?, ?)";

    public final static String INSERT_ORDER = "INSERT INTO orders (cust_id) VALUES (?)";

    public final static String INSERT_ORDER_ITEM = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?,?,?)";

    public final static String GET_ALL_CUSTOMERS = "SELECT * FROM customers";

    public final static String GET_ALL_PRODUCTS = "SELECT * FROM products";

    public final static String UPDATE_PRODUCT_STOCK_BY_ID = "UPDATE products SET stock = ? WHERE product_id = ?";

    public final static String GET_ORDER_DETAILS_BY_CUSTOMER_ID = "SELECT \n" +
            "    c.cust_id,\n" +
            "    c.full_name,\n" +
            "    c.email,\n" +
            "    o.order_id,\n" +
            "    o.order_date,\n" +
            "    p.product_id,\n" +
            "    p.product_name,\n" +
            "    p.price,\n" +
            "    oi.quantity,\n" +
            "    (p.price * oi.quantity) AS total_price\n" +
            "FROM customers c\n" +
            "JOIN orders o ON c.cust_id = o.cust_id\n" +
            "JOIN order_items oi ON o.order_id = oi.order_id\n" +
            "JOIN products p ON oi.product_id = p.product_id\n" +
            "WHERE c.cust_id = ?";

}
