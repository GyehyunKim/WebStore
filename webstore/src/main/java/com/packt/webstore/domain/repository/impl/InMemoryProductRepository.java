package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.exception.ProductNotFoundException;

@Repository
public class InMemoryProductRepository implements ProductRepository{
	
	private List<Product> listOfProducts = new ArrayList<Product>();
	
	public InMemoryProductRepository() {
		
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s with 4.00 inches");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		iphone.setCategory("Smart phone");
		
		Product laptop_dell = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14 inch laptop(black) "
				+ "with 3rd generation Intel Core Processor");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);
		laptop_dell.setCategory("Laptop");

		Product tablet_nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_nexus.setDescription("Google Nexus 7 is the lightest 7 inch tablet "
				+ "with quad-core Qualcomm Snapdragon");
		tablet_nexus.setManufacturer("Google");
		tablet_nexus.setUnitsInStock(1000);
		tablet_nexus.setCategory("Tablet");
		
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_nexus);
		
	}	
	
	public List<Product> getAllProducts() {		
		return listOfProducts;		
	}
	
	public Product getProductById(String productId) {
		
		Product productById = null; 
		
		for(Product product: listOfProducts) {
			if (productId != null && product.getProductId() != null && product.getProductId().equals(productId)) {
				productById = product;
				break;
			}
		}
		
		if (productById == null) {
//			throw new IllegalArgumentException("No product found with the product ID.");
			throw new ProductNotFoundException(productId);
		}
		
		return productById; 
	}
	
	public List<Product> getProductsByCategory(String category){
		
		List<Product> productsByCategory = new ArrayList<Product>();
		
		for(Product product: listOfProducts) {
			if (product.getCategory().equalsIgnoreCase(category)) 
				productsByCategory.add(product);			
		}
		
		return productsByCategory;
	}
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams){
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>(); 
		
		Set<String> criterias = filterParams.keySet();
		
		if (criterias.contains("brand")) {
			for(String brandName: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if (brandName.equalsIgnoreCase(product.getManufacturer())) 
						productsByBrand.add(product);					
				}
			}
		}
		
		if (criterias.contains("category")) {
			for(String categoryName: filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(categoryName));
			}
		}
		
		productsByCategory.retainAll(productsByBrand);
		
		return productsByCategory;
	}
	
	public void addProduct(Product product){
		listOfProducts.add(product);
	}
}
