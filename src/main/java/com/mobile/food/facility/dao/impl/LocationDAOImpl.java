package com.mobile.food.facility.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.dao.LocationDAO;
import com.mobile.food.facility.model.Location;
import com.mobile.food.facility.repository.LocationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationDAOImpl implements LocationDAO{
	
	@Autowired
	LocationRepository locationRepository;

	@Override
	public void save(Location location) {
		log.info("save location - "+location.toString());
		locationRepository.save(location);
	}

	@Override
	public List<Location> getBestLocation(String location) {
		log.info("Get best truck for location - "+location);
		return locationRepository.getBestTruck("%" + location + "%");
	}

}
