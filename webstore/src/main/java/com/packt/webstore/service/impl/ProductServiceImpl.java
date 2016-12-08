package com.packt.webstore.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;

@Service 
public class ProductServiceImpl implements ProductService {
	
	@Autowired 
	ProductRepository productRepository;
	
	public List <Product> getAllProducts() {
		
		List <Product> listOfProducts = productRepository.getAllProducts();
		return listOfProducts;
	}
	
	public Product getProductById(String productId){
		Product productById = productRepository.getProductById(productId);
		return productById;
	}
	
	public List<Product> getProductsByCategory(String category) {
		List<Product> productsByCategory = productRepository.getProductsByCategory(category);
		return productsByCategory;
	}
	
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams){
		Set<Product> productsByFilter = productRepository.getProductsByFilter(filterParams);
		return productsByFilter;
	}
	
	public void addProduct(Product product){
		productRepository.addProduct(product);
	}
	
}
