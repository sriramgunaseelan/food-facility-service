package com.mobile.food.facility.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private StatusMessage statusMessage;
	private String id;

}
