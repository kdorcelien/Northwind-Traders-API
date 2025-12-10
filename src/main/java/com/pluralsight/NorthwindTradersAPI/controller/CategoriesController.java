package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDAO;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Products;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    private final CategoryDAO categoryDAO;

    public CategoriesController(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @RequestMapping(path="/category", method = RequestMethod.GET)
    public List<Category> getAllCategories(){
        return this.categoryDAO.getAll();
    }

     @RequestMapping(path="/category/{id}", method = RequestMethod.GET)
    public List<Category> getCategoryById(@PathVariable int id){
        return this.categoryDAO.getByCategoryId(id);
    }
}
