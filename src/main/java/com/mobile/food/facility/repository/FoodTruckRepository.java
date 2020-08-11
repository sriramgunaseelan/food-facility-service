package com.mobile.food.facility.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.mobile.food.facility.model.response.GetFoodTruck;

public interface FoodTruckRepository {

	GetFoodTruck findFoodTruck(String id);

	List<GetFoodTruck> searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired, Pageable pageable);

	long countOfSearchFoodTruck(String applicant, String facilityType, String food, String received, Boolean isExpired);
}
