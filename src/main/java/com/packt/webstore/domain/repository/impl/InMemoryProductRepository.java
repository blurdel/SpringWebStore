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
public class InMemoryProductRepository implements ProductRepository {

	private List<Product> prodList = new ArrayList<>();
	
	
	public InMemoryProductRepository() {
		
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.00-inch 640x1186 display and 8-megapixel rear camera");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		
		Product laptop = new Product("P1235", "Dell Inspiron", new BigDecimal(700));
		laptop.setDescription("Dell Inspiron 14-inch laptop (Black) with 3rd Generation Intel Core processors");
		laptop.setCategory("Laptop");
		laptop.setManufacturer("Dell");
		laptop.setUnitsInStock(1000);
		
		Product tablet = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet.setDescription("Google Nexus 7 is the lightest 7-inch tablet with a quad-core Qualcomm Snapdragon S4 Pro processor");
		tablet.setCategory("Tablet");
		tablet.setManufacturer("Google");
		tablet.setUnitsInStock(1000);
		
		prodList.add(iphone);
		prodList.add(laptop);
		prodList.add(tablet);
	}
	
	@Override
	public List<Product> getAllProducts() {
		return prodList;
	}

	@Override
	public Product getProductById(String productId) {
		Product productById = null;
		
		for (Product p : prodList) {
			if (p != null && p.getProductId() != null && p.getProductId().equals(productId)) {
				productById = p;
				break;
			}
		}
		
		if (productById == null) {
			throw new ProductNotFoundException(productId);
		}
		
		return productById;
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		
		List<Product> list = new ArrayList<>();
		
		for (Product p : prodList) {
			if (category.equalsIgnoreCase(p.getCategory())) {
				list.add(p);
			}
			
		}		
		return list;
	}

	@Override
	public Set<Product> getProductByFilter(Map<String, List<String>> filterParams) {
		Set<Product> prodByBrand = new HashSet<>();
		Set<Product> prodByCategory = new HashSet<>();
		
		Set<String> criterias = filterParams.keySet();
		
		if (criterias.contains("brand")) {
			for (String brandName : filterParams.get("brand")) {
				for (Product p : prodList) {
					if (brandName.equalsIgnoreCase(p.getManufacturer())) {
						prodByBrand.add(p);
					}
				}
			}
		}
		
		if (criterias.contains("category")) {
			for (String category : filterParams.get("category")) {
				prodByCategory.addAll(this.getProductByCategory(category));
			}			
		}
		
		prodByCategory.retainAll(prodByBrand);
		
		return prodByCategory;
	}

	@Override
	public void addProduct(Product product) {
		prodList.add(product);		
	}

}
