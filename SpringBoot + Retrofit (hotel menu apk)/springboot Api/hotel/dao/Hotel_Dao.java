package com.hotel.hotel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.hotel.menu_model.Hotel_Menu_Model;

public interface Hotel_Dao extends JpaRepository<Hotel_Menu_Model, Long>{

}
