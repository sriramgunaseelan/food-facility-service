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
@Table(name = "LOCATION")
public class Location implements Serializable {

	private static final long serialVersionUID = -4422099106689464716L;

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "LOCATION_DESCRIPTION")
	private String locationDescription;

	@Column(name = "LATITUDE")
	private String latitude;

	@Column(name = "LONGITUDE")
	private String longitude;

	@Column(name = "ADDRESS")
	private String adddress;

	@Column(name = "X")
	private String x;

	@Column(name = "Y")
	private String y;

	@Column(name = "CNN")
	private String cnn;

}
