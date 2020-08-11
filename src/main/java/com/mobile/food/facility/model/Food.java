package com.mobile.food.facility.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FOOD")
public class Food {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "FOOD_ITEMS")
	private String foodItems;
	
	@Column(name = "BLOCK_LOT")
	private String blockLot;
	
	@Column(name = "BLOCK")
	private String block;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(String foodItems) {
		this.foodItems = foodItems;
	}

	public String getBlockLot() {
		return blockLot;
	}

	public void setBlockLot(String blockLot) {
		this.blockLot = blockLot;
	}

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	@Column(name = "LOT")
	private String lot;

}
