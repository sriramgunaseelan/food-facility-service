package com.mobile.food.facility.dao;

import java.util.List;

import com.mobile.food.facility.model.Location;

public interface LocationDAO {
	
	void save(Location location);
	
	List<Location> getBestLocation(String location);

}
