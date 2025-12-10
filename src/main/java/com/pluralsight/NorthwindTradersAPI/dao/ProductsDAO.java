package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Products;

import java.util.List;

public interface ProductsDAO {
    public void add(Products product);
    public List<Products> getAll();
    public List<Products> getByProductId(int id);
    public List<Products> getGetByName(String name);
    public List<Products> getGetGetByCategory(String category);
    public void delete(int productId);
    public void update(Products product);
    public Products insert(Products product);

    List<Products> getByProductId();

    List<Products> getGetByName();

    List<Products> getGetGetByCategory();
}

