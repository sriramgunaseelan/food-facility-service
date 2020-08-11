package com.mobile.food.facility.repository.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.mobile.food.facility.model.response.GetFoodTruck;
import com.mobile.food.facility.repository.FoodTruckRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FoodTruckRepositoryImpl implements FoodTruckRepository{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public Boolean and = false;
	
	private StringBuilder validateQuery(StringBuilder query, Boolean and) {
		if (and) {
			query.append(" and ");
		} else {
			query.append(" where ");
			this.and = true;
		}
		return query;
	}

	
	@Override
	@Query(nativeQuery = true)
	public GetFoodTruck findFoodTruck(String id) {
		GetFoodTruck foodTruck = null;
		log.info("Get food truck by id - "+id);
		Map<String, Object> paramMap = new HashMap<>();
		String selectQuery = "select ap.id as id,ap.name as name,ap.received as received,ap.prior_permit as prior_permit,ap.noi_sent as noi_sent,ap.approved as approved,ap.expiration_date as expiration_date,lc.id as location_id,lc.name as location_name,lc.location_description as location_description,lc.latitude as latitude,lc.longitude as longitude,lc.address as address,lc.x as x,lc.y as y,lc.cnn as cnn,\n" + 
				"fd.id as food_id,fd.food_items as food_items,fd.block_lot as block_lot,fd.block as block,fd.lot as lot,fc.id as facility_id,fc.schedule as schedule,fc.dayhours as dayhours,ft.id as facility_type_id,ft.type as type,ft.is_active as type_is_active,ps.id as permit_status_id,ps.status as permit_status,ps.is_active as permit_is_active from\n" + 
				"application ap left outer join location lc on (ap.location_id=lc.id) left outer join food fd on (ap.food_id=fd.id) left outer join facility \n" + 
				"fc on (ap.facility_id=fc.id) left outer join facility_type ft on (fc.type_id=ft.id) left outer join permit_status ps on (ap.permit_status_id=ps.id)\n" + 
				"where (ap.id=:id)";
		paramMap.put("id", id);
		javax.persistence.Query result = entityManager.createNativeQuery(selectQuery,
				GetFoodTruck.class);
		for (Entry<String, Object> entry : paramMap.entrySet()) {
			result.setParameter(entry.getKey(), entry.getValue());
		}
		foodTruck = (GetFoodTruck) result.getSingleResult();
		return foodTruck;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GetFoodTruck> searchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired, Pageable pageable) {
		List<GetFoodTruck> foodTrucks = null;
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder query = whereQuery(applicant, facilityType, food, received, isExpired, paramMap);
		String selectQuery = "select ap.id as id,ap.name as name,ap.received as received,ap.prior_permit as prior_permit,ap.noi_sent as noi_sent,ap.approved as approved,ap.expiration_date as expiration_date,lc.id as location_id,lc.name as location_name,lc.location_description as location_description,lc.latitude as latitude,lc.longitude as longitude,lc.address as address,lc.x as x,lc.y as y,lc.cnn as cnn,\n" + 
				"fd.id as food_id,fd.food_items as food_items,fd.block_lot as block_lot,fd.block as block,fd.lot as lot,fc.id as facility_id,fc.schedule as schedule,fc.dayhours as dayhours,ft.id as facility_type_id,ft.type as type,ft.is_active as type_is_active,ps.id as permit_status_id,ps.status as permit_status,ps.is_active as permit_is_active from\n" + 
				"application ap left outer join location lc on (ap.location_id=lc.id) left outer join food fd on (ap.food_id=fd.id) left outer join facility \n" + 
				"fc on (ap.facility_id=fc.id) left outer join facility_type ft on (fc.type_id=ft.id) left outer join permit_status ps on (ap.permit_status_id=ps.id)";
		javax.persistence.Query result = entityManager.createNativeQuery(selectQuery + query,
				GetFoodTruck.class);
		for (Entry<String, Object> entry : paramMap.entrySet()) {
			result.setParameter(entry.getKey(), entry.getValue());
		}
		foodTrucks =  result.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize()).getResultList();
		return foodTrucks;
	}

	@Override
	public long countOfSearchFoodTruck(String applicant, String facilityType, String food, String received,
			Boolean isExpired) {
		Map<String, Object> paramMap = new HashMap<>();
		StringBuilder query = whereQuery(applicant, facilityType, food, received, isExpired, paramMap);
		String selectQuery = "select count(ap.id) from\n" + 
				"application ap left outer join location lc on (ap.location_id=lc.id) left outer join food fd on (ap.food_id=fd.id) left outer join facility \n" + 
				"fc on (ap.facility_id=fc.id) left outer join facility_type ft on (fc.type_id=ft.id) left outer join permit_status ps on (ap.permit_status_id=ps.id)";
		javax.persistence.Query result = entityManager.createNativeQuery(selectQuery + query);
		for (Entry<String, Object> entry : paramMap.entrySet()) {
			result.setParameter(entry.getKey(), entry.getValue());
		}
		BigInteger count = (BigInteger) result.getSingleResult();
		return count.longValue();
	}
	
	public StringBuilder whereQuery(String applicant, String facilityType, String food, String received,
			Boolean isExpired, Map<String, Object> paramMap) {
		and = false;
		StringBuilder query = new StringBuilder();
		//query = validateQuery(query, and);
		if (applicant != null) {
			query = validateQuery(query, and);
			query.append("(lower(ap.name) like lower(:applicant))");
			paramMap.put("applicant", "%" + applicant + "%");
		}
		if (facilityType != null) {
			query = validateQuery(query, and);
			query.append("(lower(ft.type) like lower(:facilityType))");
			paramMap.put("facilityType", "%" + facilityType + "%");
		}
		if (food != null) {
			query = validateQuery(query, and);
			query.append("lower(fd.food_items) like lower(:food)");
			paramMap.put("food", "%" + food + "%");
		}
		if (received != null) {
			query = validateQuery(query, and);
			query.append("(lower(ap.received) like lower(:received))");
			paramMap.put("received", "%" + received + "%");
		}
		if (isExpired) {
			query = validateQuery(query, and);
			query.append("(lower(ps.status) = lower(:expired))");
			paramMap.put("expired", "EXPIRED");
		}
		return query;
	}


}
