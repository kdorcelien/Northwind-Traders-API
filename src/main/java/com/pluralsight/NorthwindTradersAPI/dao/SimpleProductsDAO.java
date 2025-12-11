package com.pluralsight.NorthwindTradersAPI.dao;


import com.pluralsight.NorthwindTradersAPI.model.Products;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleProductsDAO implements ProductsDAO {
    private final DataSource dataSource;
    private List<Products> products;

    public SimpleProductsDAO(DataSource dataSource) {
        this.products = new ArrayList<>();
        this.dataSource = dataSource;
    }


    @Override
    public List<Products> getAll() {
        this.products.clear();
        String query = "SELECT p.productId, p.ProductName, c.CategoryID, p.UnitPrice FROM Products p JOIN Categories c" +
                " ON P.CategoryID = C.CategoryID ";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.products.add(new Products(rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public List<Products> getByProductId(int id) {
        this.products.clear();
        String query = "SELECT * FROM Products WHERE productId = ?";

        try {
            Connection connection = dataSource.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, id);

                try (ResultSet rows = statement.executeQuery()) {
                    while (rows.next()) {
                        this.products.add(new Products(rows.getInt(1), rows.getString(2), rows.getInt(4), rows.getInt(6)));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.products;
    }

    @Override
    public Products insert(Products product) {
        this.products.clear();
        String query = "INSERT INTO Products (ProductName, CategoryID, UnitPrice)" +
                " VALUES (?, ?, ?)";
        try {
            Connection connection = dataSource.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, product.getName());
                statement.setInt(2, product.getCategory());
                statement.setDouble(3, product.getPrice());

                statement.getGeneratedKeys();

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Product added successfully!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
        }
        return product;
    }


    @Override
    public void delete(int productId) {
        String query = "DELETE FROM Products WHERE ProductID = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, productId);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Product not found.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error deleting product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Products product) {
        String query = "UPDATE Products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, product.getName());
                statement.setInt(2, product.getCategory());
                statement.setDouble(3, product.getPrice());
                statement.setInt(4, id);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Product updated successfully!");
                } else {
                    System.out.println("Product not found.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error updating product: " + e.getMessage());
            e.printStackTrace();
        }
    }

}

