package com.mobile.food.facility.mapper;

import com.mobile.food.facility.dto.FacilityDTO;
import com.mobile.food.facility.dto.FacilityTypeDTO;
import com.mobile.food.facility.dto.FoodDTO;
import com.mobile.food.facility.dto.PermitStatusDTO;
import com.mobile.food.facility.dto.response.FoodTruckResponse;
import com.mobile.food.facility.dto.response.Meta;
import com.mobile.food.facility.dto.response.Pagination;
import com.mobile.food.facility.model.response.GetFoodTruck;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class ResponseMapper {
	
	public FoodTruckResponse convert(FoodTruckResponse response, GetFoodTruck foodTruck) {
		log.info("Convert food truck into food truck response");
		
		response.setApproved(foodTruck.getApproved());
		response.setExpirationDate(foodTruck.getExpirationDate());
		response.setId(foodTruck.getId());
		response.setName(foodTruck.getName());
		response.setNoiSent(foodTruck.getNoiSent());
		response.setPriorPermit(foodTruck.getPriorPermit());
		response.setReceived(foodTruck.getReceived());
		
		FacilityTypeDTO facilityType = new FacilityTypeDTO();
		facilityType.setId(foodTruck.getFacilityTypeId());
		facilityType.setType(foodTruck.getType());
		facilityType.setIsActive(foodTruck.getTypeIsActive());
		
		FacilityDTO facility = new FacilityDTO();
		facility.setId(foodTruck.getFacilityId());
		facility.setSchedule(foodTruck.getSchedule());
		facility.setType(facilityType);
		response.setFacility(facility);
		
		FoodDTO food = new FoodDTO();
		food.setId(foodTruck.getFoodId());
		food.setFoodItems(foodTruck.getFoodItems());
		food.setBlock(foodTruck.getBlock());
		food.setLot(foodTruck.getLot());
		food.setBlockLot(foodTruck.getBlockLot());
		response.setFood(food);
		
		PermitStatusDTO permit = new PermitStatusDTO();
		permit.setId(foodTruck.getPermitStatusId());
		permit.setPermitStatus(foodTruck.getPermitStatus());
		permit.setIsActive(foodTruck.getPermitIsActive());
		response.setPermit(permit);
		
		log.info("Convert food truck into food truck response completed - "+response.toString());
		return response;
	}
	
	public Meta createPaginationResponse(int currentPage, int perPage, long noOfRecords) {
		log.info("create Meta by populating all attributes");
		Meta meta = new Meta();
		int noOfpages = (int) Math.ceil((double) noOfRecords / perPage);
		meta.setPagination(Pagination.builder().currentPage(currentPage).perPage(perPage).totalObjects(noOfRecords)
				.totalpages(noOfpages).build());
		return meta;
	}

}
