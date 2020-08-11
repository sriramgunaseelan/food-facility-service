package com.mobile.food.facility.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mobile.food.facility.model.lookup.PermitStatus;

public interface PermitStatusRepository extends CrudRepository<PermitStatus, String> {

	@Query("select ps from PermitStatus ps where lower(ps.status) = lower(:name)")
	PermitStatus findByName(@Param("name") String name);
	
	@Query("select ps from PermitStatus ps where ps.id = :id")
	PermitStatus findPermitStatus(@Param("id") String id);

}
