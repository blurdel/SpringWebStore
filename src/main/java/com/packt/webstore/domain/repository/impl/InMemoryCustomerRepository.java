package com.packt.webstore.domain.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Customer;
import com.packt.webstore.domain.repository.CustomerRepository;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {

	private List<Customer> list = new ArrayList<>();
	
	
	public InMemoryCustomerRepository() {
		list.add(new Customer(1, "Zoey", "Arlington VA"));
		list.add(new Customer(2, "Tana", "Arlington, VA"));
		list.add(new Customer(3, "Cami", "Alexandria, VA"));
	}
	
	@Override
	public List<Customer> getAllCustomers() {
		return list;
	}

	
}
