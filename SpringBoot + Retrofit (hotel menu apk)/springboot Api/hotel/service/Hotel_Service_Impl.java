package com.hotel.hotel.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.hotel.dao.Hotel_Dao;
import com.hotel.hotel.menu_model.Hotel_Menu_Model;

@Service
public class Hotel_Service_Impl implements Hotel_Service{
	
	@Autowired
	private Hotel_Dao dao;

	@Override
	public List<Hotel_Menu_Model> getMenuList() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Hotel_Menu_Model getMenuById(long id) {
		// TODO Auto-generated method stub
		return dao.getReferenceById(id);
	}

	@Override
	public Hotel_Menu_Model addItemToMenu(Hotel_Menu_Model menu) {
		// TODO Auto-generated method stub
		return dao.save(menu);
	}

	@Override
	public Hotel_Menu_Model updateItemToMenu(Hotel_Menu_Model menu) {
		// TODO Auto-generated method stub
		return dao.save(menu);
	}

	@Override
	public void deleteItemFromMenu(long id) {
		// TODO Auto-generated method stub
		dao.delete(dao.getReferenceById(id));
	}

}
