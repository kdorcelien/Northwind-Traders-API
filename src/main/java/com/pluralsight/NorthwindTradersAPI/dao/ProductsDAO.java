package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.model.Products;

import java.util.List;

public interface ProductsDAO {
    public List<Products> getAll();
    public List<Products> getByProductId(int id);
    public void delete(int productId);
    public void update(int id, Products product);
    public Products insert(Products product);

}

