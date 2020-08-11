package com.mobile.food.facility.dto.response;

import com.mobile.food.facility.dto.FacilityDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FacilityResponse extends FacilityDTO {

	private static final long serialVersionUID = 1L;

	private String status;
	private StatusMessage statusMessage;

}
