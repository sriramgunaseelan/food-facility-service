package com.mobile.food.facility.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mobile.food.facility.model.Application;

public interface ApplicationRepository extends CrudRepository<Application, String>{
	
	@Query("select ap.id from Application ap where (ap.id=:id) or (ap.location.id=:id)")
	String findByAppOrLocationId(@Param("id") String id);

}
