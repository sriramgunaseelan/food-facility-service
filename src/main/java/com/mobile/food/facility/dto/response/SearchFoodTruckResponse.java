package com.mobile.food.facility.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.mobile.food.facility.dto.FoodTruckDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SearchFoodTruckResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String status;
	private StatusMessage statusMessage;
	private List<FoodTruckDTO> foodTrucks;
	private Meta meta;
	
	public void addFoodTruck(FoodTruckDTO foodTruckDTO) {
		if (this.foodTrucks == null)
			this.foodTrucks = new ArrayList<>();
		this.foodTrucks.add(foodTruckDTO);
	}


}
