package com.packt.webstore.domain;

public class Customer {

	private long id;
	private String name;
	private String address;
	private int numOrdersMade;

	
	public Customer() {
	}
	
	public Customer(long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumOrdersMade() {
		return numOrdersMade;
	}
	public void setNumOrdersMade(int numOrdersMade) {
		this.numOrdersMade = numOrdersMade;
	}
	
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", numOrdersMade=" + numOrdersMade
				+ "]";
	}
	
}
