package com.mobile.food.facility.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.constants.MessageCodes;
import com.mobile.food.facility.dao.ApplicationDAO;
import com.mobile.food.facility.dao.FacilityDAO;
import com.mobile.food.facility.dao.FacilityTypeDAO;
import com.mobile.food.facility.dao.FoodDAO;
import com.mobile.food.facility.dao.FoodTruckDAO;
import com.mobile.food.facility.dao.LocationDAO;
import com.mobile.food.facility.dao.PermitStatusDAO;
import com.mobile.food.facility.dto.request.BestFoodTruckRequest;
import com.mobile.food.facility.dto.request.FoodTruckRequest;
import com.mobile.food.facility.dto.response.BaseResponse;
import com.mobile.food.facility.dto.response.BestFoodTruckResponse;
import com.mobile.food.facility.dto.response.FoodTruckResponse;
import com.mobile.food.facility.dto.response.Meta;
import com.mobile.food.facility.dto.response.SearchFoodTruckResponse;
import com.mobile.food.facility.dto.response.StatusMessage;
import com.mobile.food.facility.mapper.RequestMapper;
import com.mobile.food.facility.mapper.ResponseMapper;
import com.mobile.food.facility.model.Application;
import com.mobile.food.facility.model.Location;
import com.mobile.food.facility.model.response.GetFoodTruck;
import com.mobile.food.facility.service.FoodTruckService;
import com.mobile.food.facility.util.FoodTruckUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodTruckServiceImpl implements FoodTruckService {

	@Autowired
	public FoodTruckServiceImpl(FoodTruckDAO foodTruckDAO, FacilityTypeDAO typeDAO, PermitStatusDAO permitDAO,
			ApplicationDAO appDAO, LocationDAO locationDAO, FoodDAO foodDAO, FacilityDAO facilityDAO) {
		this.foodTruckDAO = foodTruckDAO;
		this.appDAO = appDAO;
		this.locationDAO = locationDAO;
		foodTruckUtil = new FoodTruckUtil(foodTruckDAO);
		requestMapper = new RequestMapper(foodTruckDAO, permitDAO, typeDAO, locationDAO, foodDAO, facilityDAO);
		responseMapper = new ResponseMapper();
		statusMessage = new StatusMessage();
	}

	private FoodTruckDAO foodTruckDAO;
	private ApplicationDAO appDAO;
	private LocationDAO locationDAO;
	private FoodTruckUtil foodTruckUtil;
	private RequestMapper requestMapper;
	private ResponseMapper responseMapper;
	private StatusMessage statusMessage;

	/*
	 * This method is used to get the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@Override
	public FoodTruckResponse getFoodTruckById(String id) {
		log.info("Get food truck by application or location id - " + id);
		String appId = foodTruckUtil.validateId(id);
		GetFoodTruck foodTruck = foodTruckDAO.getFoodTruck(appId);
		FoodTruckResponse response = responseMapper.convert(new FoodTruckResponse(), foodTruck);
		response.setStatus(MessageCodes.SUCCESS);
		statusMessage.setCode(MessageCodes.SUCCESS_MSG);
		statusMessage.setDescription(MessageCodes.SUCCESS_DESC);
		response.setStatusMessage(statusMessage);
		log.info("Get food truck by application or location id - " + id + " completed :: " + response.toString());
		return response;
	}

	/*
	 * This method is used to delete  the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@Override
	public BaseResponse removeFoodTruckById(String id) {
		log.info("Remove food truck by application or location id - " + id);
		String appId = foodTruckUtil.validateId(id);
		foodTruckDAO.removeFoodTruck(appId);
		BaseResponse response = new BaseResponse();
		response.setStatus(MessageCodes.SUCCESS);
		statusMessage.setCode(MessageCodes.SUCCESS_MSG);
		statusMessage.setDescription(MessageCodes.DELETE_DESC);
		response.setStatusMessage(statusMessage);
		log.info("Food truck removed by id - " + id);
		return response;
	}
	
	/*
	 * This method is used to update/insert  the food truck record by application id or location id
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@Override
	public BaseResponse upsertFoodTruckById(String id, FoodTruckRequest request) {
		log.info("Upsert food truck by application or location id - " + id);
		String appId = foodTruckUtil.validUpsertId(id);
		BaseResponse response = new BaseResponse();
		Application app = null;
		GetFoodTruck foodTruck = null;
		if (appId != null) {
			log.info("Record already exists for id - " + id);
			foodTruck = foodTruckDAO.getFoodTruck(appId);
			app = requestMapper.convert(new Application(), request, foodTruck);

		} else {
			log.info("Record already not exists for id - " + id);
			app = requestMapper.convert(new Application(), request, foodTruck);
		}
		appDAO.save(app);
		response.setId(app.getId());
		response.setStatus(MessageCodes.SUCCESS);
		statusMessage.setCode(MessageCodes.SUCCESS_MSG);
		statusMessage.setDescription(MessageCodes.SUCCESS_DESC);
		response.setStatusMessage(statusMessage);
		return response;
	}

	/*
	 * This method is used to search  the food truck records applicant, facility type, food items, received
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@Override
	public SearchFoodTruckResponse searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired, int currentPage, int perPage) {
		log.info("Search food truck by applicant - " + applicant + " facility type - " + facilityType + " food items - "
				+ food + " received - " + received + " expired - " + isExpired);
		SearchFoodTruckResponse response = new SearchFoodTruckResponse();
		FoodTruckUtil.checkPaginationParameters(currentPage, perPage);
		Pageable pageable = PageRequest.of(currentPage - 1, perPage);
		long noOfRecords = 0;
		List<GetFoodTruck> foodTrucks = foodTruckDAO.searchFoodTruck(applicant, facilityType, food, received, isExpired,
				pageable);
		if (foodTrucks != null && foodTrucks.size() > 0) {
			foodTrucks.forEach(foodTruck -> {
				response.addFoodTruck(responseMapper.convert(new FoodTruckResponse(), foodTruck));
			});
			noOfRecords = foodTruckDAO.searchFoodTruck(applicant, facilityType, food, received, isExpired);
			statusMessage.setDescription(MessageCodes.SUCCESS_DESC);
		} else {
			statusMessage.setDescription(MessageCodes.NO_INFO_AVAILABLE);
		}
		Meta meta = responseMapper.createPaginationResponse(currentPage, perPage, noOfRecords);
		response.setMeta(meta);
		response.setStatus(MessageCodes.SUCCESS);
		statusMessage.setCode(MessageCodes.SUCCESS_MSG);
		response.setStatusMessage(statusMessage);
		return response;
	}

	/*
	 * This method is used to get the best food truck by location
	 * 
	 * @return Object
	 * 
	 * @throws Exception
	 */
	@Override
	public BestFoodTruckResponse getBestFoodTruck(BestFoodTruckRequest request) {
		BestFoodTruckResponse response = new BestFoodTruckResponse();
		if(request.getLocation() != null) {
			List<Location> locations = locationDAO.getBestLocation(request.getLocation());
			if(locations != null && locations.size() > 0) {
				response = requestMapper.convert(new BestFoodTruckResponse(), locations.get(0));
			}
		}
		response.setStatus(MessageCodes.SUCCESS);
		statusMessage.setCode(MessageCodes.SUCCESS_MSG);
		statusMessage.setDescription(MessageCodes.SUCCESS_DESC);
		response.setStatusMessage(statusMessage);
		return response;

	}

}
