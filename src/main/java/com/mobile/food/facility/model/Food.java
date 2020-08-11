package com.mobile.food.facility.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "FOOD")
public class Food implements Serializable {

	private static final long serialVersionUID = -4422099106689464716L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "FOOD_ITEMS")
	private String foodItems;

	@Column(name = "BLOCK_LOT")
	private String blockLot;

	@Column(name = "BLOCK")
	private String block;

	@Column(name = "LOT")
	private String lot;

}
