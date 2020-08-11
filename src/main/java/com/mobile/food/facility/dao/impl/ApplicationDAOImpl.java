package com.mobile.food.facility.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.ApplicationDAO;
import com.mobile.food.facility.model.Application;
import com.mobile.food.facility.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationDAOImpl implements ApplicationDAO{
	
	@Autowired
	ApplicationRepository appRepository;
	
	@Override
	public void save(Application app) {
		log.info("Save application - "+app.toString());
		appRepository.save(app);
	}

}
