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
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	@Override
	public Product getProductById(String id) {
		return productRepository.getProductById(id);
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		return productRepository.getProductByCategory(category);
	}

	@Override
	public Set<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductByFilter(filterParams);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
