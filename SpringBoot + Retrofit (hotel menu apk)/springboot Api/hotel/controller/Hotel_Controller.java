package com.hotel.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.hotel.menu_model.Hotel_Menu_Model;
import com.hotel.hotel.service.Hotel_Service;

@RestController
public class Hotel_Controller {
	
	@Autowired
	private Hotel_Service service;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome to veg sagar hotel";
	}

	@GetMapping("/hotel/menu")
	public List<Hotel_Menu_Model> getMenuList(){
		return service.getMenuList();
	}
	
	@GetMapping("/hotel/menu/{menuId}")
	public Hotel_Menu_Model getMenuById(@PathVariable String menuId) {
		return service.getMenuById(Long.parseLong(menuId));
	}
	
	@PostMapping("/hotel/add")
	public Hotel_Menu_Model addItemToMenu(@RequestBody Hotel_Menu_Model model) {
		return service.addItemToMenu(model);
	}
	
	@PutMapping("/hotel/update")
	public Hotel_Menu_Model updateMenuItem(@RequestBody Hotel_Menu_Model model) {
		return service.updateItemToMenu(model);
	}
	
	@DeleteMapping("/hotel/delete/{menuId}")
	public void deleteItemFromMenu(@PathVariable String menuId) {
		service.deleteItemFromMenu(Long.parseLong(menuId));
	}
}
