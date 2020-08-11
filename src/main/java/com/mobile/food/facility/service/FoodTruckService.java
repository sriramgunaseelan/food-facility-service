package com.mobile.food.facility.service;

import com.mobile.food.facility.dto.request.BestFoodTruckRequest;
import com.mobile.food.facility.dto.request.FoodTruckRequest;
import com.mobile.food.facility.dto.response.BaseResponse;
import com.mobile.food.facility.dto.response.BestFoodTruckResponse;
import com.mobile.food.facility.dto.response.FoodTruckResponse;
import com.mobile.food.facility.dto.response.SearchFoodTruckResponse;

public interface FoodTruckService {
	
	FoodTruckResponse getFoodTruckById(String id);
	
	BaseResponse removeFoodTruckById(String id);
	
	BaseResponse upsertFoodTruckById(String id, FoodTruckRequest request);
	
	SearchFoodTruckResponse searchFoodTruck(String applicant, String facilityType, String food, String received, Boolean isExpired, int currentPage, int perPage);

	BestFoodTruckResponse getBestFoodTruck(BestFoodTruckRequest request);
}
