package com.telus.training.ex4;

import java.util.ArrayList;
import java.util.List;

	public class Order
	{
	    private List<LineItem> lineItems = new ArrayList<LineItem>();

	    // Shipping address
	    private String address1Name;
	    private String address1Street;
	    private String address1City;
	    private String address1ZipCode;

	    // Billing address
	    private String address2Name;
	    private String address2Street;
	    private String address2City;
	    private String address2ZipCode;
	    
		public Order()
		{
		}
		
    	public void AddProduct(Product product) 
    	{
        	for(LineItem lineItem : lineItems) {
            	if (lineItem.getProduct().getSku().equals(product.getSku())) 
            	{
                	lineItem.setQuantity(lineItem.getQuantity() + 1);
                	return;
            	}
        	}

        	lineItems.add(LineItem.ForProduct(product));
    	}
    	
		public float GetTotalPrice()
		{
	        float total = 0F;
	        for(LineItem lineItem : lineItems) {
	            // Calculate line item price
	            float price = 0F;
	            switch (lineItem.getProduct().getCategory()) {
	                case Product.NEW:
	                    price = lineItem.getUnitPrice() * lineItem.getQuantity() * .9F;
	                    break;
	                case Product.OLD:
	                    price = (lineItem.getUnitPrice() - 5) * lineItem.getQuantity();
	                    break;
	                case Product.REGULAR:
	                    price = lineItem.getUnitPrice() * lineItem.getQuantity();
	                    break;
	            }
	            total += price;
	        }
        	return total;
		}
		
	    public void SetShippingAddress(String name, String street, String city, String zipCode) 
	    {
	        address1Name = name;
	        address1Street = street;
	        address1City = city;
	        address1ZipCode = zipCode;
    	}

	    public void SetBillingAddress(String name, String street, String city, String zipCode) 
	    {
	        address2Name = name;
	        address2Street = street;
	        address2City = city;
	        address2ZipCode = zipCode;
	    }

    	public String ShippingSlip() {
	        String shippingSlip =
	            // Header
	            "*************\n" +
	            "Shipping Slip\n" +
	            "*************\n\n";

	        // Shipping address
	        shippingSlip += "Ship To:\n";
	        if (address1Name != null) shippingSlip += address1Name + "\n";
	        if (address1Street != null) shippingSlip += address1Street + "\n";
	        if (address1ZipCode != null) shippingSlip += address1ZipCode;
	        if (address1City != null) shippingSlip += ", " + address1City + "\n";
	        shippingSlip += "\n";

	        // Billing address
	        shippingSlip += "Bill To:\n";
	        if (address2Name != null) shippingSlip += address2Name + "\n";
	        if (address2Street != null) shippingSlip += address2Street + "\n";
	        if (address2ZipCode != null) shippingSlip += address2ZipCode;
	        if (address2City != null) shippingSlip += ", " + address2City + "\n";
	        shippingSlip += "\n";

	        float totalPrice = 0;
	        // Order Details
	        shippingSlip += "Details:\n";
	        for (LineItem lineItem : lineItems) {
	            shippingSlip += lineItem.getProduct().getSku() + " x " + lineItem.getQuantity() + " = ";

	            // Calculate line item price
	            float price = 0;
	            switch (lineItem.getProduct().getCategory()) {
	                case Product.NEW:
	                    price = lineItem.getUnitPrice() * lineItem.getQuantity() * 0.9F;
	                    break;
	                case Product.OLD:
	                    price = (lineItem.getUnitPrice() - 5) * lineItem.getQuantity();
	                    break;
	                case Product.REGULAR:
	                    price = lineItem.getUnitPrice() * lineItem.getQuantity();
	                    break;
	            }
	            shippingSlip += price + "\n";

	            totalPrice += price;
	        }
	        shippingSlip += "\n";

	        // Total
	        shippingSlip += "Total = " + totalPrice + "\n\n";

	        // Footer
	        shippingSlip +=
	            "*************\n" +
	            "Thank You!\n" +
	            "*************\n";

	        return shippingSlip;
    	}
	
	}