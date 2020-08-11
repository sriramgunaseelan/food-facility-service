package com.mobile.food.facility.util;

import java.util.UUID;

import com.mobile.food.facility.constants.MessageCodes;
import com.mobile.food.facility.dao.FoodTruckDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodTruckUtil {
	
	private FoodTruckDAO foodTruckDAO;
	
	public FoodTruckUtil(FoodTruckDAO foodTruckDAO) {
		this.foodTruckDAO = foodTruckDAO;
	}
	
	public String validateId(String id) {
		String appId = foodTruckDAO.getApplicationId(id);
		if(appId == null) {
			log.error("Invalid id passed - "+id);
			throw new IllegalArgumentException(MessageCodes.INVALID_ID);
		}
		log.info("Given id exist - "+id);
		return appId;
	}
	
	public String validUpsertId(String id) {
		String appId = foodTruckDAO.getApplicationId(id);
		if(appId != null) {
			log.info("Given id exist - "+id);
			return appId;
		}
		log.info("Given id does not exist - "+id);
		return null;
	}

	public static String generateDigitUniqueId() {
		return UUID.randomUUID().toString().toUpperCase();
	}
	
	public static boolean checkPaginationParameters(int currentpage, int perPage) {
		boolean emptyCheck = true;
		if (currentpage <= 0 || perPage <= 0) {
			log.error("Invalid PageNo or perPage size");
			throw new IllegalArgumentException(MessageCodes.INVALID_PAGENO_OR_PERPAGESIZE);
		}
		return emptyCheck;
	}
}
