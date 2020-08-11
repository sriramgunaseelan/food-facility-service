package com.mobile.food.facility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.FacilityDAO;
import com.mobile.food.facility.model.Facility;
import com.mobile.food.facility.repository.FacilityRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FacilityDAOImpl implements FacilityDAO{
	
	@Autowired
	FacilityRepository facilityRepository;
	
	@Override
	public void save(Facility facility) {
		log.info("Save facility - "+facility.toString());
		facilityRepository.save(facility);
	}

}
