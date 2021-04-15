package com.packt.webstore.exception;



public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4940652196131005577L;
	
	private String productId;
	
	
	public ProductNotFoundException(String productId) {
		super();
		this.productId = productId;
	}

	public String getProductId() {
		return productId;
	}
		
}
