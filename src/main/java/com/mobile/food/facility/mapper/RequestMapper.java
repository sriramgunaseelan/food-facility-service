package com.mobile.food.facility.mapper;

import com.mobile.food.facility.constants.SequencePrefix;
import com.mobile.food.facility.dao.FacilityDAO;
import com.mobile.food.facility.dao.FacilityTypeDAO;
import com.mobile.food.facility.dao.FoodDAO;
import com.mobile.food.facility.dao.FoodTruckDAO;
import com.mobile.food.facility.dao.LocationDAO;
import com.mobile.food.facility.dao.PermitStatusDAO;
import com.mobile.food.facility.dto.request.FoodTruckRequest;
import com.mobile.food.facility.dto.response.BestFoodTruckResponse;
import com.mobile.food.facility.model.Application;
import com.mobile.food.facility.model.Facility;
import com.mobile.food.facility.model.Food;
import com.mobile.food.facility.model.Location;
import com.mobile.food.facility.model.lookup.FacilityType;
import com.mobile.food.facility.model.lookup.PermitStatus;
import com.mobile.food.facility.model.response.GetFoodTruck;
import com.mobile.food.facility.util.FoodTruckUtil;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class RequestMapper {

	public RequestMapper(FoodTruckDAO foodTruckDAO, PermitStatusDAO permitDAO, FacilityTypeDAO typeDAO,
			LocationDAO locationDAO, FoodDAO foodDAO, FacilityDAO facilityDAO) {
		this.permitDAO = permitDAO;
		this.typeDAO = typeDAO;
		this.locationDAO = locationDAO;
		this.facilityDAO = facilityDAO;
		this.foodDAO = foodDAO;
	}

	private FacilityTypeDAO typeDAO;
	private PermitStatusDAO permitDAO;
	private LocationDAO locationDAO;
	private FoodDAO foodDAO;
	private FacilityDAO facilityDAO;

	public Application convert(Application app, FoodTruckRequest request, GetFoodTruck foodTruck) {

		log.info("Food truck request convert to application in mapper");

		app.setApproved(request.getApproved());
		app.setExpirationDate(request.getExpirationDate());
		app.setName(request.getName());
		app.setNoiSent(request.getNoiSent());
		app.setPriorPermit(request.getPriorPermit());
		app.setReceived(request.getReceived());

		Location location = new Location();
		location.setAdddress(request.getAddress());
		location.setCnn(request.getCnn());
		location.setLatitude(request.getLatitude());
		location.setLocationDescription(request.getLocationDescription());
		location.setName(request.getLocation());
		location.setX(request.getX());
		location.setY(request.getY());

		Food food = new Food();
		food.setBlock(request.getBlock());
		food.setBlockLot(request.getBlockLot());
		food.setFoodItems(request.getFoodItems());
		food.setLot(request.getLot());

		if (request.getPermit() != null) {
			PermitStatus permit = permitDAO.getPermitStatusByName(request.getPermit());
			if (permit != null) {
				app.setPermitStatus(permit);
			} else {
				log.error("Invalid permit passed");
			}
		}

		Facility facility = new Facility();
		if (request.getFacilityType() != null) {
			FacilityType type = typeDAO.getFacilityTypeByName(request.getFacilityType());
			if (type != null) {
				facility.setSchedule(request.getSchedule());
				facility.setDayhours(request.getDayhours());
				facility.setType(type);
			}

		}

		if (foodTruck != null) {
			app.setId(foodTruck.getId());
			location.setId(foodTruck.getLocationId());
			food.setId(foodTruck.getFoodId());
			facility.setId(foodTruck.getFacilityId());
		} else {
			app.setId(SequencePrefix.APPLICATION_PREFIX.concat(FoodTruckUtil.generateDigitUniqueId()));
			location.setId(SequencePrefix.LOCATION_PREFIX.concat(FoodTruckUtil.generateDigitUniqueId()));
			food.setId(SequencePrefix.FOOD_PREFIX.concat(FoodTruckUtil.generateDigitUniqueId()));
			facility.setId(SequencePrefix.FACILITY_PREFIX.concat(FoodTruckUtil.generateDigitUniqueId()));
		}

		app.setLocation(location);
		app.setFood(food);
		app.setFacility(facility);

		locationDAO.save(location);
		foodDAO.save(food);
		facilityDAO.save(facility);

		log.info("Food truck request converted to application in mapper - " + app.toString());
		return app;
	}
	
	public BestFoodTruckResponse convert(BestFoodTruckResponse response, Location location) {
		response.setLatitude(location.getLatitude());
		response.setLocationDescription(location.getLocationDescription());
		response.setId(location.getId());
		return response;
	}

}
