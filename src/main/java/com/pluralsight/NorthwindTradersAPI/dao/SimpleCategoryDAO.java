package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Products;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "INSERT INTO Categories (CategoryName, ";
        return null;
    }
//    CategoryID, CategoryName, Description
}
