package com.pluralsight.NorthwindTradersAPI.controller;

import com.pluralsight.NorthwindTradersAPI.dao.ProductsDAO;
import com.pluralsight.NorthwindTradersAPI.model.Products;
import com.pluralsight.NorthwindTradersAPI.util.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    private final ProductsDAO productsDAO;

    @Autowired
    public ProductsController(ProductsDAO productsDAO) {
        this.productsDAO = productsDAO;
    }

    @RequestMapping(path="/products", method = RequestMethod.GET)
    public List<Products> getAllProducts(){
        return this.productsDAO.getAll();
    }

    @RequestMapping(path="/products/{id}", method = RequestMethod.GET)
    public List<Products> getProductsById(@PathVariable int id){

        return this.productsDAO.getByProductId(id);
    }

    @RequestMapping(path="/products",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Products addProduct(@RequestBody Products products){

        return productsDAO.insert(products);
    }

    @RequestMapping(path="/products/{id}",method=RequestMethod.PUT)
    public void updateProduct (@PathVariable int id, @RequestBody Products products) {
        productsDAO.update(id, products);
    }

    @RequestMapping(path="/products/{id}",method=RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteEmployee (@PathVariable int id) {
        productsDAO.delete(id);
    }
}
