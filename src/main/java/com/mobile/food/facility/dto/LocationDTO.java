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
public class LocationDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String locationDescription;
	private String latitude;
	private String longitude;
	private String adddress;
	private String x;
	private String y;
	private String cnn;

}
