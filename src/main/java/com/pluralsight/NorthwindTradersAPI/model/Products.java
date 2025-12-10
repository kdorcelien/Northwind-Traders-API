package com.pluralsight.NorthwindTradersAPI.model;



//import com.pluralsight.NorthwindTradersSpringBoot.util.ConsoleColors;
//import com.pluralsight.NorthwindTradersSpringBoot.util.InputValidator;

public class Products {
    private int productId, categoryID;
    private String name;
    private double price;

    public Products(int productId, String name, int categoryID, double price) {
        this.productId = productId;
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
    }


    public Products( String name, int categoryID, double price) {
        this.name = name;
        this.categoryID = categoryID;
        this.price = price;
    }

    public Products(){}


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategory() {
        return categoryID;
    }

    public void setCategory(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Products: " +
                "\n\tproductId='" + productId + '\'' +
                "\n\tname='" + name + '\'' +
                "\n\tcategoryID='" + categoryID + '\'' +
                "\n\tprice='" + price + '\'' + '\n';
    }
}

