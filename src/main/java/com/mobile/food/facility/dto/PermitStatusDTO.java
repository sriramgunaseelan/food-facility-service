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
public class PermitStatusDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String permitStatus;
	private Boolean isActive;


}
