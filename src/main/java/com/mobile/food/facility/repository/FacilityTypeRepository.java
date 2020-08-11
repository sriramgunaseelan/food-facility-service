package com.mobile.food.facility.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mobile.food.facility.model.lookup.FacilityType;

public interface FacilityTypeRepository extends CrudRepository<FacilityType, String> {
	
	@Query("select ft from FacilityType ft where lower(ft.type) = lower(:name)")
	FacilityType findByName(@Param("name") String name);
	
	@Query("select ft from FacilityType ft where ft.id = :id")
	FacilityType findFacilityType(@Param("id") String id);

}
