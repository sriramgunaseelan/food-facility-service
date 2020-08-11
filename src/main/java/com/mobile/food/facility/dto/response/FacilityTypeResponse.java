package com.mobile.food.facility.dto.response;

import com.mobile.food.facility.dto.FacilityTypeDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FacilityTypeResponse extends FacilityTypeDTO {

	private static final long serialVersionUID = 1L;

	private String status;
	private StatusMessage statusMessage;

}
