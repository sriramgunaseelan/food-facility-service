package com.mobile.food.facility.model.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class GetFoodTruck implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
	private String received;
	private String priorPermit;
	private String noiSent;
	private String approved;
	private String expirationDate;
	private String locationId;
	private String locationName;
	private String locationDescription;
	private String latitude;
	private String longitude;
	private String address;
	private String x;
	private String y;
	private String cnn;
	private String foodId;
	private String foodItems;
	private String blockLot;
	private String block;
	private String lot;
	private String facilityId;
	private String schedule;
	private String dayhours;
	private String facilityTypeId;
	private String type;
	private Boolean typeIsActive;
	private String permitStatusId;
	private String permitStatus;
	private Boolean permitIsActive;
}
