package com.mobile.food.facility.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodTruckRequest {
	
	private String name;
	private String received;
	private String priorPermit;
	private String noiSent;
	private String approved;
	private String expirationDate;
	private String location;
	private String locationDescription;
	private String latitude;
	private String longitude;
	private String address;
	private String x;
	private String y;
	private String cnn;
	private String foodItems;
	private String blockLot;
	private String block;
	private String lot;
	private String schedule;
	private String dayhours;
	private String permit;
	private String facilityType;


}
