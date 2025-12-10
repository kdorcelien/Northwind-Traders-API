package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Products;

import java.util.List;

public interface CategoryDAO {
    public List<Category> getAll();
    public List<Category> getByCategoryId(int id);
    public Category insert(Category category);
}
