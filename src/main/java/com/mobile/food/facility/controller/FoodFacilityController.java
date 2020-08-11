package com.mobile.food.facility.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mobile.food.facility.constants.NameSpaceConstants;
import com.mobile.food.facility.dto.request.BestFoodTruckRequest;
import com.mobile.food.facility.dto.request.FoodTruckRequest;
import com.mobile.food.facility.dto.response.BaseResponse;
import com.mobile.food.facility.dto.response.BestFoodTruckResponse;
import com.mobile.food.facility.dto.response.FoodTruckResponse;
import com.mobile.food.facility.dto.response.SearchFoodTruckResponse;
import com.mobile.food.facility.service.FoodTruckService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class FoodFacilityController {
	
	@Autowired
	private FoodTruckService foodTruckService;
	
	/*
	 * This method is used to get the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@GetMapping(value = NameSpaceConstants.FOOD_TRUCK)
	public ResponseEntity<FoodTruckResponse> getFoodTruck(@PathVariable("id") String id) {
		log.info("Get Food Truck by id :: "+id);
		FoodTruckResponse response = foodTruckService.getFoodTruckById(id);
		log.info("Get Food Truck response - "+response.toString()+" for id :: "+id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * This method is used to delete  the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@DeleteMapping(value = NameSpaceConstants.FOOD_TRUCK)
	public ResponseEntity<BaseResponse> removeFoodTruck(@PathVariable("id") String id) {
		log.info("Remove Food Truck by id :: "+id);
		BaseResponse response = foodTruckService.removeFoodTruckById(id);
		log.info("Removed food truck response - "+response.toString()+" for id :: "+id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * This method is used to update/insert  the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@PutMapping(value = NameSpaceConstants.FOOD_TRUCK)
	public ResponseEntity<BaseResponse> upsertFoodTruck(@PathVariable("id") String id,
			@RequestBody FoodTruckRequest foodTruckRequest) {
		log.info("Upsert Food Truck by id :: "+id);
		BaseResponse response = foodTruckService.upsertFoodTruckById(id, foodTruckRequest);
		log.info("Upsert food truck response - "+response.toString()+" for id :: "+id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/*
	 * This method is used to search  the food truck records applicant, facility type, food items, received
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@GetMapping(value = NameSpaceConstants.SEARCH_FOOD_TRUCK)
	public ResponseEntity<SearchFoodTruckResponse> searchFoodTruck(
			@RequestParam(required = false, name = "aplicant", defaultValue = "") String applicant,
			@RequestParam(required = false, name = "facilityType", defaultValue = "Truck") String facilityType,
			@RequestParam(required = false, name = "food", defaultValue = "") String food,
			@RequestParam(required = false, name = "received", defaultValue = "") String received,
			@RequestParam(required = false, name = "isExpired", defaultValue = "false") Boolean isExpired,
			@RequestParam(name = "currentPage", defaultValue = "1", required = false) int currentPage,
			@RequestParam(name = "perPage", defaultValue = "10", required = false) int perPage
			) {
		log.info("Search Food Truck started");
		SearchFoodTruckResponse response = foodTruckService.searchFoodTruck(applicant, facilityType, food, received, isExpired, currentPage, perPage);
		log.info("Search Food Truck completed - "+response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	/*
	 * This method is used to get the best food truck by location
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@PostMapping(value = NameSpaceConstants.BEST_FOOD_TRUCK)
	public ResponseEntity<BestFoodTruckResponse> bestFoodTruck(@RequestBody BestFoodTruckRequest request){
		log.info("Get best food truck by request :: "+request.toString());
		BestFoodTruckResponse response = foodTruckService.getBestFoodTruck(request);
		log.info("Get best food truck response - "+response.toString());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
