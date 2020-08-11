package com.mobile.food.facility.dao;

import com.mobile.food.facility.model.lookup.PermitStatus;

public interface PermitStatusDAO {
	
	PermitStatus getPermitStatusByName(String name);
	
	PermitStatus getPermitStatusById(String id);

}
