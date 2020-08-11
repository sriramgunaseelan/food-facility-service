package com.mobile.food.facility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.FoodDAO;
import com.mobile.food.facility.model.Food;
import com.mobile.food.facility.repository.FoodRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodDAOImpl implements FoodDAO{
	
	@Autowired
	FoodRepository foodRepository;
	
	@Override
	public void save(Food food) {
		log.info("Save food - "+food.toString());
		foodRepository.save(food);
	}
	
	

}
