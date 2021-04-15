package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	ProductRepository productRepository;
	
	
	@Override
	public void processOrder(String productId, int count) {
		Product pid = productRepository.getProductById(productId);
		
		if (pid.getUnitsInStock() < count) {
			throw new IllegalArgumentException("Out of Stock. Available Units in stock: " + pid.getUnitsInStock());
		}
		
		pid.setUnitsInStock(pid.getUnitsInStock() - count);
	}

}
