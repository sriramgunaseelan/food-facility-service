package com.mobile.food.facility.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mobile.food.facility.model.Location;

public interface LocationRepository extends CrudRepository<Location, String>{
	
	@Query("select lc from Location lc where lower(lc.locationDescription) like lower(:name)")
	List<Location> getBestTruck(@Param("name") String name);

}
