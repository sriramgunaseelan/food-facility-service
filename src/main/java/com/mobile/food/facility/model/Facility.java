package com.mobile.food.facility.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.mobile.food.facility.model.lookup.FacilityType;

@Entity
@Table(name = "FACILITY")
public class Facility {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "SCHEDULE")
	private String schedule;

	@Column(name = "DAYHOURS")
	private String dayhours;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getDayhours() {
		return dayhours;
	}

	public void setDayhours(String dayhours) {
		this.dayhours = dayhours;
	}

	public FacilityType getType() {
		return type;
	}

	public void setType(FacilityType type) {
		this.type = type;
	}

	@OneToOne
	@JoinColumn(name = "TYPE_ID", referencedColumnName = "ID")
	private FacilityType type;

}
