package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDAO;
import com.pluralsight.NorthwindTradersAPI.model.Category;
import com.pluralsight.NorthwindTradersAPI.model.Products;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(path="/category",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category){

        return categoryDAO.insert(category);
    }

    @RequestMapping(path="/category/{id}",method=RequestMethod.PUT)
    public void updateCategory (@PathVariable int id, @RequestBody Category category) {
        categoryDAO.update(id, category);
    }

    @RequestMapping(path="/category/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCategory (@PathVariable int id) {
        categoryDAO.delete(id);
    }
}
