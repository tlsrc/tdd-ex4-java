package com.telus.training.ex4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrderTest {
	Order order;
	
	@Before
	public void Init() {
  		order = new Order();
	}
	
	@Test
	public void ShouldPreserveOriginalProductPrice() {
        float regularPrice = 15.5F;
        float discountedPrice = 12.00F;

        Product product = ProductCosting(regularPrice);
        order.AddProduct(product);

        product.setPrice(discountedPrice);
        Assert.assertEquals(regularPrice, order.GetTotalPrice());
	}
	
	@Test
	public void ShouldBeAbleToCalculateTotalPrice() {
        order.AddProduct(Mouse());
        order.AddProduct(Mouse());
        order.AddProduct(Keyboard());

        float expectedTotal = Mouse().getPrice() * 2 + Keyboard().getPrice();
        Assert.assertEquals(expectedTotal, order.GetTotalPrice());
	}

	@Test
	public void ShouldOfferTenPercentDiscountOnNewlyReleasedProducts() {
    	Product newRelease = NewRelease(30f);
    	order.AddProduct(newRelease);

    	float expectedTotal = newRelease.getPrice() * .9F;
    	Assert.assertEquals(expectedTotal, order.GetTotalPrice());
	}

	@Test
	public void ShouldOfferFiveDollarsOffOnDiscountinuedProducts() {
    	Product discounted = Discontinued(20F);
    	order.AddProduct(discounted);
    	order.AddProduct(discounted);

    	float expectedTotal = (discounted.getPrice() - 5 ) * 2;
    	Assert.assertEquals(expectedTotal, order.GetTotalPrice());
	}

	public void testPrintsBillingAndShippingAddressOnShippingSlip() {
        order.SetShippingAddress("John Doe", "456 West Street", "New-York", "60750");
        order.SetBillingAddress("John Doe", "123 East Street", "New-York", "60570");

        String expectedShippingSlip =
                "*************\n" +
                "Shipping Slip\n" +
                "*************\n" +
                "\n" +
                "Ship To:\n" +
                "John Doe\n" +
                "456 West Street\n" +
                "60750, New-York\n" +
                "\n" +
                "Bill To:\n" +
                "John Doe\n" +
                "123 East Street\n" +
                "60570, New-York\n" +
                "\n" +
                "Details:\n" +
                "\n" +
                "Total = 0\n" +
                "\n" +
                "*************\n" +
                "Thank You!\n" +
                "*************\n";

        Assert.assertEquals(expectedShippingSlip, order.ShippingSlip());
	}

	@Test
   	 public void PrintsOrderDetailsOnShippingSlip() {
        order.AddProduct(Mouse());
        order.AddProduct(Mouse());
        order.AddProduct(Keyboard());

        String expectedShippingSlip =
                "*************\n" +
                "Shipping Slip\n" +
                "*************\n" +
                "\n" +
                "Ship To:\n" +
                "\n" +
                "Bill To:\n" +
                "\n" +
                "Details:\n" +
                "Mouse x 2 = 59\n" +
                "Keyboard x 1 = 60\n" +
                "\n" +
                "Total = 119\n" +
                "\n" +
                "*************\n" +
                "Thank You!\n" +
                "*************\n";

        Assert.assertEquals(expectedShippingSlip, order.ShippingSlip());
    }

   	private Product ProductCosting(float regularPrice) {
    	return new Product("ABC", regularPrice);
	}
	
    private Product Keyboard() {
    	return new Product("Keyboard", 60f);
	}

	private Product Mouse() {
    	return new Product("Mouse", 29.5f);
	}

	private Product NewRelease(float price) {
    	Product newRelease = new Product("newRelease", price);
    	newRelease.setCategory(Product.NEW);
    	return newRelease;
	}

	private Product Discontinued(float price) {
    	Product discontinued = new Product("discontinued", price);
    	discontinued.setCategory(Product.OLD);
    	return discontinued;
	}
}
