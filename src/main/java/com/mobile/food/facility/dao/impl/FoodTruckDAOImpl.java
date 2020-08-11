package com.mobile.food.facility.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.FoodTruckDAO;
import com.mobile.food.facility.model.response.GetFoodTruck;
import com.mobile.food.facility.repository.ApplicationRepository;
import com.mobile.food.facility.repository.FoodTruckRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodTruckDAOImpl implements FoodTruckDAO{
	
	@Autowired
	FoodTruckRepository foodTruckRepository;
	
	@Autowired
	ApplicationRepository applicationRepository;

	@Override
	public GetFoodTruck getFoodTruck(String id) {
		log.info("Get food truck by id - "+id);
		return foodTruckRepository.findFoodTruck(id);
	}

	@Override
	public String getApplicationId(String id) {
		log.info("Get application by id - "+id);
		return applicationRepository.findByAppOrLocationId(id);
	}

	@Override
	public void removeFoodTruck(String id) {
		log.info("Remove food truck by id - "+id);
	    applicationRepository.deleteById(id);
	}

	@Override
	public List<GetFoodTruck> searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired, Pageable pageable) {
		log.info("Search food truck");
		return foodTruckRepository.searchFoodTruck(applicant, facilityType, food, received, isExpired, pageable);
	}

	@Override
	public long searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired) {
		log.info("Get count of food trucks");
		return foodTruckRepository.countOfSearchFoodTruck(applicant, facilityType, food, received, isExpired);
	}

}
