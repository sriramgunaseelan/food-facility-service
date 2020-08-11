package com.mobile.food.facility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.FacilityTypeDAO;
import com.mobile.food.facility.model.lookup.FacilityType;
import com.mobile.food.facility.repository.FacilityTypeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacilityTypeDAOImpl implements FacilityTypeDAO{
	
	@Autowired
	FacilityTypeRepository typeRepository;
	
	@Override
	public FacilityType getFacilityTypeByName(String name) {
		log.info("Get type by name - "+name);
		return typeRepository.findByName(name);
	}

	@Override
	public FacilityType getFacilityTypeById(String id) {
		log.info("Get type by id - "+id);
		return typeRepository.findFacilityType(id);
	}

}
