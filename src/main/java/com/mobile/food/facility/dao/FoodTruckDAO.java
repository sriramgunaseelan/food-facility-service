package com.mobile.food.facility.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.mobile.food.facility.model.response.GetFoodTruck;

public interface FoodTruckDAO {
	
	GetFoodTruck getFoodTruck(String id);
	
	String getApplicationId(String id);
	
	void removeFoodTruck(String id);
	
	List<GetFoodTruck> searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired, Pageable pageable);
	
	long searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired);
	
	

}
