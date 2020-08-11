package com.mobile.food.facility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.PermitStatusDAO;
import com.mobile.food.facility.model.lookup.PermitStatus;
import com.mobile.food.facility.repository.PermitStatusRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PermitStatsDAOImpl implements PermitStatusDAO{
	
	@Autowired
	PermitStatusRepository permitRepository;
	
	@Override
	public PermitStatus getPermitStatusByName(String name) {
		log.info("Get permit status by name - "+name);
		return permitRepository.findByName(name);
	}

	@Override
	public PermitStatus getPermitStatusById(String id) {
		log.info("Get permit status by id - "+id);
		return permitRepository.findPermitStatus(id);
	}

}
