package com.packt.webstore.exception;


public class ProductNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -6943111111111111111L;
	private String productId; 
	
	public ProductNotFoundException(String productId) {
		this.productId = productId; 
	}
	
	public String getProductId() {
		return productId; 
	}
}
