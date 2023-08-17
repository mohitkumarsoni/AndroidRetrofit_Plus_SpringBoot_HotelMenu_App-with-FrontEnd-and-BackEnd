package com.hotel.hotel.service;

import java.util.List;

import com.hotel.hotel.menu_model.Hotel_Menu_Model;

public interface Hotel_Service {
	
	public List<Hotel_Menu_Model> getMenuList();
	public Hotel_Menu_Model getMenuById(long id);
	public Hotel_Menu_Model addItemToMenu(Hotel_Menu_Model menu);
	public Hotel_Menu_Model updateItemToMenu(Hotel_Menu_Model menu);
	public void deleteItemFromMenu(long id);

}
