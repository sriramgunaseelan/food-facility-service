package com.mobile.food.facility.dto.response;

import com.mobile.food.facility.dto.LocationDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse extends LocationDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	private StatusMessage statusMessage;

}
