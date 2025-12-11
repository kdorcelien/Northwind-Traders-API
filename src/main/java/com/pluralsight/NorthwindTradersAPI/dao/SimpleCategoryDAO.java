package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Products;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class SimpleCategoryDAO implements CategoryDAO{
    private final DataSource dataSource;
    private List<Category> categories;

    public SimpleCategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
        this.categories = new ArrayList<>();
    }

    @Override
    public List<Category> getAll() {
        this.categories.clear();
        String query = "SELECT * FROM Categories";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rows = statement.executeQuery();
            while (rows.next()) {
                this.categories.add(new Category(rows.getInt(1), rows.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.categories;
    }

    @Override
    public List<Category> getByCategoryId(int id) {

        this.categories.clear();
        String query = "SELECT * FROM Categories WHERE CategoryID = ?";

        try {
            Connection connection = dataSource.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, id);

                try (ResultSet rows = statement.executeQuery()) {
                    while (rows.next()) {
                        this.categories.add(new Category(rows.getInt(1), rows.getString(2)));
                    }
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return this.categories;
    }

    @Override
    public Category insert(Category category) {
        this.categories.clear();
        String query = "INSERT INTO Categories (CategoryName) VALUES ( ? )";
        try {
            Connection connection = dataSource.getConnection();

            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, category.getCategoryName());

                statement.getGeneratedKeys();

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Category added successfully!");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error adding category: " + e.getMessage());
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void delete(int categoryID) {
        String query = "DELETE FROM Categories WHERE CategoryID = ?";
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, categoryID);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Category deleted successfully!");
                } else {
                    System.out.println("Category not found.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error deleting Category: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Category category) {
        String query = "UPDATE Categories SET CategoryName = ? WHERE CategoryID = ?";

        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, category.getCategoryName());
                statement.setInt(2, id);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Category updated successfully!");
                } else {
                    System.out.println("Category not found.");
                }

            }
        } catch (SQLException e) {
            System.out.println("Error updating Category: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
