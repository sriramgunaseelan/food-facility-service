package com.mobile.food.facility.dao;

import com.mobile.food.facility.model.lookup.FacilityType;

public interface FacilityTypeDAO {
	
	FacilityType getFacilityTypeByName(String name);
	
	FacilityType getFacilityTypeById(String id);

}
