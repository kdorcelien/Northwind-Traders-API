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
    public void add(Products product) {
        this.products.add(product);

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
                statement.setInt(2,product.getCategory());
                statement.setDouble(3, product.getPrice());

                statement.getGeneratedKeys();

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Product added successfully!");
                }

            }
    }catch (SQLException e) {
            System.out.println("Error adding product: " + e.getMessage());
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Products> getGetByName(String name) {
        return List.of();
    }

    @Override
    public List<Products> getGetGetByCategory(String category) {
        return List.of();
    }

    @Override
    public void delete(int productId) {

    }

    @Override
    public void update(Products product) {

    }


    @Override
    public List<Products> getByProductId() {
        return List.of();
    }

    @Override
    public List<Products> getGetByName() {
        return List.of();
    }

    @Override
    public List<Products> getGetGetByCategory() {
        return List.of();
    }
}

