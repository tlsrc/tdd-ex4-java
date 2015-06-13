package com.telus.training.ex4;

public class Product {
    public static final int REGULAR = 1;
    public static final int NEW = 2;
    public static final int OLD = 3;

    private String sku;
    // Note: prices are in US dollars
    private float price;
    // price category
    private int category = REGULAR;

    public Product(String sku, float price) 
    {
        this.sku = sku;
        this.price = price;
    }
    
    public String getSku() {
    	return sku;
    }
    public void setSku(String value) {
    	this.sku = value;
    }

    public float getPrice() {
        return price;
    }
    
    public void setPrice(float value) {
    	this.price = value;
    }

    public int getCategory() { return category; }
    public void setCategory(int value) {this.category = value; }
}
