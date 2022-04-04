package com.nbfc.bean;

import java.util.*;

/**
 * @author Saurav Tyagi 2017
 * 
 */
public class Product {

	private String id;
	private String name;
	private long price;
	private int quantity;
	private boolean status;
	private Date creationDate;

	
	public Product() {
		super();
	}
	public Product(String id, String name, long price, int quantity,
			boolean status, Date creationDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.status = status;
		this.creationDate = creationDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	}
