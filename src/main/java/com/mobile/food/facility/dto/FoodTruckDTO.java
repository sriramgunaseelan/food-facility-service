package com.mobile.food.facility.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FoodTruckDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String received;
	private String priorPermit;
	private String noiSent;
	private String approved;
	private String expirationDate;
	private LocationDTO location;
	private FoodDTO food;
	private FacilityDTO facility;
	private PermitStatusDTO permit;


}
