package com.hotel.hotel.menu_model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Hotel_Menu_Model {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String dish;
	private long dish_price;
	
	
	public Hotel_Menu_Model() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Hotel_Menu_Model(String dish, long dish_price) {
		super();
		this.dish = dish;
		this.dish_price = dish_price;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDish() {
		return dish;
	}
	public void setDish(String dish) {
		this.dish = dish;
	}
	public long getDish_price() {
		return dish_price;
	}
	public void setDish_price(long dish_price) {
		this.dish_price = dish_price;
	}
	@Override
	public String toString() {
		return "Hotel_Menu_Model [id=" + id + ", dish=" + dish + ", dish_price=" + dish_price + "]";
	}
	
	

}
