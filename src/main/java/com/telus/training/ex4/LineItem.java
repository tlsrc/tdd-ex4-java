package com.telus.training.ex4;

public class LineItem {
	
    private Product product;
    // The unit price of the product
    private float up;
    // The quantity of product items
    private int n;

    public static LineItem ForProduct(Product product) {
        return new LineItem(product);
    }

    private LineItem(Product product) {
        this.product = product;
        this.up = product.getPrice();
        this.n = 1;
    }

    public Product getProduct() {
    	return product;
    }
    
    public int getQuantity() {
    	return n;
    }
    
    public void	setQuantity(int value) {
    	this.n = value;
    }

    public float getUnitPrice() {
    	return up;
    }
	
}
	

